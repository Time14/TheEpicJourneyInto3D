#version 400

const int MAX_NUM_LIGHTS = 10;

uniform mat4 projectionMatrix;
uniform mat4 transformMatrix;
uniform mat4 viewMatrix;

uniform bool enableLights = true;

struct Light {
	bool isDirectional;
	float fallOff;
	vec3 position;
	vec3 color;
};

uniform int numLights;
uniform Light[MAX_NUM_LIGHTS] lights;

layout(location = 0) in vec3 in_position;
layout(location = 1) in vec2 in_texCoord;
layout(location = 2) in vec3 in_normal;

out vec2 pass_texCoord;
out vec3 pass_normal;
out vec3 pass_toLight[MAX_NUM_LIGHTS];

void main() {
	//vec3 lightPos = inverse(viewMatrix * vec4(0, 0, 0, 1)).xyz;

	vec4 worldPosition = viewMatrix * transformMatrix * vec4(in_position, 1);
	pass_texCoord = in_texCoord;

	if(enableLights) {
		pass_normal  = (viewMatrix * transformMatrix * vec4(in_normal, 0)).xyz;

		for(int i = 0; i < numLights; i++) {
			if(lights[i].isDirectional){
				pass_toLight[i] = (viewMatrix * vec4(-lights[i].position, 0)).xyz;
			} else {
				pass_toLight[i] = (viewMatrix * vec4(lights[i].position, 1)).xyz - worldPosition.xyz;
			}
		}
	}

	gl_Position = projectionMatrix * worldPosition;
}


/*
	vec3 lightPosition = (viewMatrix * vec4(-5, 0, -5, 1)).xyz;

	vec4 worldPosition = viewMatrix * matrix * vec4(in_position, 1.0);
	pass_texCoord = in_texCoord;
	pass_normal = in_normal;
	pass_toLight = lightPosition - worldPosition.xyz;
*/