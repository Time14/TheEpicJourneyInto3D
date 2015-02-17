package com.idek.gfx.light;

import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class PointLight extends Light {
	
	public static final int LOCAL_SIZE = 7;
	public static final int TOTAL_SIZE = Light.SIZE;
	
	public static final float DEFAULT_FALLOFF = 5;
	public static final Vector3f DEFAULT_POSITION = new Vector3f();
	public static final Vector3f DEFAULT_COLOR = new Vector3f(1, 1, 1);
	
	public float fallOff = DEFAULT_FALLOFF;
	public Vector3f position = DEFAULT_POSITION;
	public Vector3f color = DEFAULT_COLOR;
	
	public float[] getData() {
		return new float[]{
				0,
				fallOff,
				position.x, position.y, position.z,
				color.x, color.y, color.z
		};
	}
	
	public void createShadowMap() {
		
	}
	
	public int getSize() {
		return LOCAL_SIZE;
	}
}