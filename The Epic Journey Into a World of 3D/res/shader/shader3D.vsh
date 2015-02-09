#version 400

uniform mat4 matrix;

layout(location = 0) in vec3 in_position;
layout(location = 1) in vec2 in_texCoord;
layout(location = 2) in vec3 in_normal;

out vec3 pass_position;
out vec2 pass_texCoord;
out vec3 pass_normal;

void main() {

	pass_position = in_position;
	pass_texCoord = in_texCoord;
	pass_normal = in_normal;

	gl_Position = matrix * vec4(in_position, 1);
}