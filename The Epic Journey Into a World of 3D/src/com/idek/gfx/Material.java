package com.idek.gfx;

public class Material {
	
	private Texture texture = Texture.DEFAULT_TEXTURE;
	
	public Material() {}
	
	public Material sendTexture(Texture texture) {
		
		this.texture = texture;
		return this;
	}
	
	public Material bind() {
		texture.bind(0);
		return this;
	}
}