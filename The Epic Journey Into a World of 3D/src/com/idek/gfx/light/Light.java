package com.idek.gfx.light;

import com.idek.gfx.Texture;
import com.idek.gfx.shader.ShaderProgram3D;

/*
 * 
 * Light struct in shader3D.vsh;
 * 
 * struct Light {
 * 	bool isDirectional;
 * 	float fallOff;
 * 	vec3 position;
 * 	vec3 color;
 * }
 * 
 * 
 * 
*/

public abstract class Light {
	
	public static final int SIZE = 8;
	public static final int NUM_COMPONENTS = 4;
	
	protected ShaderProgram3D shader = ShaderProgram3D.INSTANCE;
	
	protected boolean updated = false;
	
	protected Texture shadowMap;
	
	public abstract float[] getData();
	
	public Light setUpdated(boolean updated) {
		this.updated = updated;
		return this;
	}
	
	public boolean isUpdated() {
		return updated;
	}
	
	public void initFrameBuffer() {
		
	}
	
	public abstract int getSize();
	public abstract void createShadowMap();
	
	public void cleanUp() {
		if(shadowMap != null)
			shadowMap.cleanUp();
	}
}