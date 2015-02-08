#version 400

uniform mat4 matrix;

layout(location = 0) in vec3 in_position;
layout(location = 1) in vec3 in_normal;
void main() {
	gl_Position = matrix * vec4(in_position, 1);
}