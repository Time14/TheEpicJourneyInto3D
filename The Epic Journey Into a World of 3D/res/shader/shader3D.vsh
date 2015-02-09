#version 400

uniform mat4 matrix;

layout(location = 0) in vec3 in_position;
layout(location = 1) in vec2 in_texCoord;
layout(location = 2) in vec3 in_normal;

out vec2 pass_texCoord;
out vec3 pass_normal;
out vec3 pass_toLight;

void main() {

	vec3 lightPosition = vec3(-5, 0, -5);

	vec4 worldPosition = matrix * vec4(in_position, 1.0);
	pass_texCoord = in_texCoord;
	pass_normal = normalize(((matrix * vec4(in_normal, 0)).xyz));
	pass_toLight = lightPosition - worldPosition.xyz;

	gl_Position = worldPosition;
}