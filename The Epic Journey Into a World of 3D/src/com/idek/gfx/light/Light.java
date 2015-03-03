package com.idek.gfx.light;

import com.idek.gfx.Texture;
import com.idek.gfx.shader.ShaderProgram3D;
import com.idek.gfx.shader.StructDefiner;
import com.idek.gfx.shader.StructDefiner.GLSLType;

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

public abstract class Light implements StructDefiner {
	
	public static final String[] STRUCT_COMPONENTS = new String[]{"isDirectional", "fallOff", "position", "color"};
	public static final int NUM_COMPONENTS = STRUCT_COMPONENTS.length;
	
	public static final int SIZE = 8;
	
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

	public GLSLType[] getStructure() {
		return new GLSLType[]{GLSLType.INT, GLSLType.FLOAT, GLSLType.VEC3, GLSLType.VEC3};
	}
	
	public String[] getComponentNames() {
		return STRUCT_COMPONENTS;
	}
	
	public StructDefiner getCoreInstance() {
		return new DirectionalLight();
	}
	
	public void cleanUp() {
		if(shadowMap != null)
			shadowMap.cleanUp();
	}
}