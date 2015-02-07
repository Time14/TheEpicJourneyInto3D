package com.idek.gfx;

public class Material {
	
	private Texture texture;
	
	public Material() {}
	
	public Material sendTexture(Texture texture) {
		
		this.texture = texture;
		return this;
	}
	
}