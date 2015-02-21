package com.idek.gfx;

import org.lwjgl.util.vector.Vector3f;

public class Material {
	
	public static final int DEFAULT_SHINE_DAMPER = 1;
	public static final int DEFAULT_REFLECTIVITY = 1;
	public static final Vector3f DEFAULT_BASE_COLOR = new Vector3f(1, 1, 1);
	
	private int shineDamper = DEFAULT_SHINE_DAMPER;
	private int reflectivity = DEFAULT_REFLECTIVITY;
	
	private Vector3f baseColor = DEFAULT_BASE_COLOR;
	
	private Texture texture = TextureLibrary.DEFAULT_TEXTURE;
	private Texture normalMap = TextureLibrary.DEFAULT_NORMAL_MAP;
	
	public Material() {}
	
	public Material sendTexture(Texture texture) {
		this.texture = texture;
		return this;
	}
	
	public Material sendTextures(Texture texture, Texture normalMap) {
		sendTexture(texture);
		return sendNormapMap(normalMap);
	}
	
	public Material sendNormapMap(Texture normalMap) {
		this.normalMap = normalMap;
		return this;
	}
	
	public Material initTextures(String texPath, String normalPath) {
		return initTextures(texPath, normalPath, true);
	}
	
	public Material initTextures(String texPath, String normalPath, boolean repeat) {
		initTexture(texPath, repeat);
		return initNormalMap(normalPath);
	}
	
	public Material initTexture(String path) {
		return initTexture(path, true);
	}
	
	public Material initTexture(String path, boolean repeat) {
		texture = new Texture(path, repeat);
		return this;
	}
	
	public Material initNormalMap(String path) {
		normalMap = new Texture(path, true);
		return this;
	}
	
	public Material bind() {
		texture.bind(0);
		normalMap.bind(1);
		return this;
	}
	
	public void cleanUp() {
		texture.cleanUp();
		normalMap.cleanUp();
	}
}