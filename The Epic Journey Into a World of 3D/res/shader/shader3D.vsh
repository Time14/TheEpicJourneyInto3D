#version 400

uniform mat4 matrix;

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 normal;

out vec3 pass_color;
void main() {

	pass_color = position;

	//gl_Position = vec4(vec2(position.x, position.y) / position.z, position.z , 2);
	gl_Position = matrix * vec4(position, 1);
}


