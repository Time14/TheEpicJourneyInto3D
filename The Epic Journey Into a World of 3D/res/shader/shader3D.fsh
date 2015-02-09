
#version 400

uniform mat4 matrix;
uniform sampler2D texture;

in vec2 pass_texCoord;
in vec3 pass_normal;
in vec3 pass_toLight;

out vec4 out_color;
void main() {

	float strength = 5;

	vec3 diffuse = dot(normalize(pass_toLight), pass_normal) * vec3(0, 0.5, 0.5) * (strength - length(pass_toLight)/5);

	out_color = vec4(diffuse, 1) * texture2D(texture, pass_texCoord);
}