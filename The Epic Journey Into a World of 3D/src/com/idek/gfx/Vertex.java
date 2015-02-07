package com.idek.gfx;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vertex {
	
	public static final int LENGTH = 6;
	public static final int LENGTH_POSITION = 3;
	public static final int LENGTH_NORMAL = 3;
	
	private Vector3f position;
	private Vector3f normal;
	
	public Vertex(float x, float y, float z) {
		this(new Vector3f(x, y, z), new Vector3f(0, 0, 1));
	}
	
	public Vertex(float x, float y, float z, Vector3f normal) {
		this(new Vector3f(x, y, z), normal);
	}
	
	public Vertex(Vector3f position) {
		this(position, new Vector3f(0, 0, 1));
	}
	
	public Vertex(Vector3f position, Vector3f normal) {
		this.position 	= position;
		this.normal 	= normal;
	}
	
	public float[] getData() {
		return new float[]{
			position.x, position.y, position.z,
			normal.x, normal.y, normal.z
		};
	}
	
	public String toString() {
		return "X: " + position.x + "\tY:" + position.y + "\tZ: " + position.z + "\nNormal: " + normal.x + ", " + normal.y + ", " + normal.z;
	}
}