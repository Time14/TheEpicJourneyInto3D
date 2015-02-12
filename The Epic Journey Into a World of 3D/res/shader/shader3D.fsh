
#version 400

uniform mat4 projectionMatrix;
uniform mat4 transformMatrix;
uniform mat4 viewMatrix;

uniform sampler2D[] textures;

struct Light {
	bool isDirectional;
	float fallOff;
	vec3 position;
	vec3 color;
}

uniform Light[] lights;

in vec2 pass_texCoord;
in vec3 pass_normal;
in vec3 pass_toLight;

out vec4 out_color;
void main() {

	vec3 lightColor = vec3(1, 1, 1);

	vec3 finalNormal = normalize(pass_normal + (viewMatrix * texture2D(textures[1], pass_texCoord)).xyz);

	vec3 diffuse = dot(normalize(pass_toLight), finalNormal) * lightColor;

	out_color = vec4(diffuse, 1) * texture2D(textures[0], pass_texCoord);
}

/*

	vec3 textureColor = (texture2D(textures[0], pass_texCoord)).xyz;
	vec3 fragNormal = normalize(matrix * viewMatrix * (vec4(pass_normal, 0) + texture2D(textures[1], pass_texCoord))).xyz;
	vec3 normalToLight = normalize(pass_toLight);

	vec3 finalColor = textureColor * dot(fragNormal, normalToLight);
*/