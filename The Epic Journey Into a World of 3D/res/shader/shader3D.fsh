
#version 400

const int MAX_NUM_LIGHTS = 10;

uniform mat4 projectionMatrix;
uniform mat4 transformMatrix;
uniform mat4 viewMatrix;

uniform sampler2D[] textures;

struct Light {
	bool isDirectional;
	float fallOff;
	vec3 position;
	vec3 color;
};

uniform int numLights;
uniform Light[MAX_NUM_LIGHTS] lights;

in vec2 pass_texCoord;
in vec3 pass_normal;
in vec3 pass_toLight[MAX_NUM_LIGHTS];

out vec4 out_color;
void main() {

	vec3 diffuse = vec3(0, 0, 0);

	vec3 finalNormal = normalize(pass_normal + (viewMatrix * texture2D(textures[1], pass_texCoord)).xyz);

	for(int i = 0; i < numLights; i++) {
		if(lights[i].isDirectional) {
			diffuse += max(dot(normalize(pass_toLight[i]), finalNormal), 0) * lights[i].color;
		} else {
			diffuse += max(dot(normalize(pass_toLight[i]), finalNormal), 0) * lights[i].color * min(lights[i].fallOff/pow(length(pass_toLight[i]), 2), 1);
		}

	}

	out_color = vec4(diffuse, 1) * texture2D(textures[0], pass_texCoord);
}

/*

	vec3 textureColor = (texture2D(textures[0], pass_texCoord)).xyz;
	vec3 fragNormal = normalize(matrix * viewMatrix * (vec4(pass_normal, 0) + texture2D(textures[1], pass_texCoord))).xyz;
	vec3 normalToLight = normalize(pass_toLight);

	vec3 finalColor = textureColor * dot(fragNormal, normalToLight);
*/