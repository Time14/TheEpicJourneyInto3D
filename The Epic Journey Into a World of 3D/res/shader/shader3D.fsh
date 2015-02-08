#version 400

uniform mat4 matrix;
uniform sampler2D texture;

out vec4 out_color;

void main() {
	out_color = vec4(1, 0, 0, 1);
}