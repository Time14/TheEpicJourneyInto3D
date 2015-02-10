#version 400

uniform mat4 projectionMatrix;
uniform mat4 transformMatrix;
uniform mat4 viewMatrix;

layout(location = 0) in vec3 in_position;
layout(location = 1) in vec2 in_texCoord;
layout(location = 2) in vec3 in_normal;

out vec2 pass_texCoord;
out vec3 pass_normal;

void main() {

	pass_texCoord = in_texCoord;
	pass_normal = in_normal;

	vec4 worldPosition = projectionMatrix * viewMatrix * transformMatrix * vec4(in_position, 1);

	gl_Position = worldPosition;
}


/*
	vec3 lightPosition = (viewMatrix * vec4(-5, 0, -5, 1)).xyz;

	vec4 worldPosition = viewMatrix * matrix * vec4(in_position, 1.0);
	pass_texCoord = in_texCoord;
	pass_normal = in_normal;
	pass_toLight = lightPosition - worldPosition.xyz;
*/