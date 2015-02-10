
#version 400

uniform mat4 matrix;
uniform mat4 viewMatrix;
uniform sampler2D[] textures;

in vec2 pass_texCoord;
in vec3 pass_normal;

out vec4 out_color;
void main() {
	out_color = texture2D(textures[0], pass_texCoord);
}

/*

	vec3 textureColor = (texture2D(textures[0], pass_texCoord)).xyz;
	vec3 fragNormal = normalize(matrix * viewMatrix * (vec4(pass_normal, 0) + texture2D(textures[1], pass_texCoord))).xyz;
	vec3 normalToLight = normalize(pass_toLight);

	vec3 finalColor = textureColor * dot(fragNormal, normalToLight);
*/