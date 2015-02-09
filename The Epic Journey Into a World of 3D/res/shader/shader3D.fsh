
#version 400

uniform mat4 matrix;
uniform sampler2D texture;

in vec3 pass_position;
in vec2 pass_texCoord;
in vec3 pass_normal;

out vec4 out_color;
void main() {

	vec4 light = vec4(-5, 0, -5, 1);

	vec4 normalToLight = light - (matrix * vec4(pass_position, 1));

	out_color = texture2D(texture, pass_texCoord);

	out_color = out_color * dot(normalize(matrix * vec4(pass_normal, 1)), normalize(vec4(normalToLight, 1));
}