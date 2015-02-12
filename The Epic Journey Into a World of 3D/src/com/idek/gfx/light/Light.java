package com.idek.gfx.light;

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
	
	protected ShaderProgram3D shader = ShaderProgram3D.INSTANCE;
	
	protected boolean isUpdated = false;
	
	public float[] getLightData() {
		isUpdated = true;
		return getData();
	}
	
	protected abstract float[] getData();
	
	public boolean isUpdated() {
		return isUpdated;
	}
	
}