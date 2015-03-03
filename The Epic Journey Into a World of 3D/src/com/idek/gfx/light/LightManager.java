package com.idek.gfx.light;

import java.util.ArrayList;
import java.util.HashMap;

import com.idek.gfx.Texture;
import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;

public class LightManager {
	
	private ShaderProgram program = ShaderProgram3D.INSTANCE;
	
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
		program.sendStructArray(ShaderProgram3D.UNIFORM_LIGHTS, lights.toArray(data));
		program.sendInt(ShaderProgram3D.UNIFORM_NUM_LIGHTS, lights.size());
		
		
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
