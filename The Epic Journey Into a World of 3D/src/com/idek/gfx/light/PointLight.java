package com.idek.gfx.light;

import org.lwjgl.util.vector.Vector3f;

public class PointLight extends Light {
	
	public static final float DEFAULT_FALLOFF = 10;
	public static final Vector3f DEFAULT_POSITION = new Vector3f();
	public static final Vector3f DEFAULT_COLOR = new Vector3f(1, 1, 1);
	
	public float fallOff = DEFAULT_FALLOFF;
	public Vector3f position = DEFAULT_POSITION;
	public Vector3f color = DEFAULT_COLOR;
	
	protected float[] getData() {
		return new float[]{
				0,
				fallOff,
				position.x, position.y, position.z,
				color.x, color.y, color.z
		};
	}
	
}