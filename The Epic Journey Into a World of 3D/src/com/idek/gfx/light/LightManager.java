package com.idek.gfx.light;

import java.util.ArrayList;
import java.util.HashMap;

import com.idek.gfx.Texture;
import com.idek.gfx.shader.ShaderProgram3D;

public class LightManager {
	
	private ShaderProgram3D program = ShaderProgram3D.INSTANCE;
	
	private ArrayList<Light> lights;
	
	public LightManager() {
		lights = new ArrayList<>();
	}
	
	public LightManager addLight(Light light) {
		lights.add(light);
		return this;
	}
	
	public LightManager createLights() {
		addLight(new DirectionalLight());
		
		return this;
	}
	
	public LightManager update() {
		program.bind();
		Light[] data = new Light[]{};
		program.sendLights(lights.toArray(data));
		
		
		
		return this;
	}
	
	public void cleanUp() {
		for(Light l : lights)
			l.cleanUp();
		lights.clear();
	}
	
	/*
	 * ShadowMap Wishlist
	 * FrameBuffer - Where we write the deapth data
	 * Texture - Where we store the depth data
	 * 
	 */
	
}
