package com.idek.gfx.light;

import java.util.ArrayList;
import java.util.HashMap;

import com.idek.gfx.RenderManager;
import com.idek.gfx.Texture;
import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;

public class LightManager {
	
	private ArrayList<Light> lights;
	
	private RenderManager rm;
	
	public LightManager(RenderManager rm) {
		lights = new ArrayList<>();
		this.rm = rm;
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
		rm.getShaderProgram().bind();
		Light[] data = new Light[]{};
		rm.getShaderProgram().sendStructArray(ShaderProgram3D.UNIFORM_LIGHTS, lights.toArray(data));
		rm.getShaderProgram().sendInt(ShaderProgram3D.UNIFORM_NUM_LIGHTS, lights.size());
		
		
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
