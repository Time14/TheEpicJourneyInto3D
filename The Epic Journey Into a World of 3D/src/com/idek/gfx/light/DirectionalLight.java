package com.idek.gfx.light;

import org.lwjgl.util.vector.Vector3f;

public class DirectionalLight extends Light {
	
	public static final int LOCAL_SIZE = 6;
	public static final int TOTAL_SIZE = Light.SIZE;
	
	public static final Vector3f DEFAULT_DIRECTION = (Vector3f)new Vector3f(1, -1, 0).normalise();
	public static final Vector3f DEFAULT_COLOR = new Vector3f(1, 1, 1);
	
	public Vector3f direction = DEFAULT_DIRECTION;
	public Vector3f color = DEFAULT_COLOR;
	
	
	public DirectionalLight() {
		
	}
	
	public float[] getData() {
		return new float[]{
				1,
				0,
				direction.x, direction.y, direction.z,
				color.x, color.y, color.z
		};
	}
	
	public int getSize() {
		return LOCAL_SIZE;
	}
}