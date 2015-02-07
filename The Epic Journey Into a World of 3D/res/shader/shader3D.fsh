#version 400

uniform mat4 matrix;

out vec4 color;

in vec3 pass_color;

void main() {

	float lerp = pass_color.z;

	color = mix(vec4(1, 0, 0, 1), vec4(0, 1, 0, 1), lerp / 1);
}