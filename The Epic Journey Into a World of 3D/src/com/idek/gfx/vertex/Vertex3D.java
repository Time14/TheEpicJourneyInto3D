package com.idek.gfx.vertex;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vertex3D extends Vertex{
	
	public static final int LENGTH = 8;
	public static final int LENGTH_POSITION = 3;
	public static final int LENGTH_NORMAL = 3;
	
	private Vector3f position;
	private Vector2f texCoord;
	private Vector3f normal;
	
	public Vertex3D(Vector3f position, Vector2f tPosition, Vector3f normal) {
		this.position = position;
		this.texCoord = tPosition;
		this.normal = normal;
	}
	
	public Vertex3D(float x, float y, float z, float s, float t, float nx, float ny, float nz) {
		this(new Vector3f(x, y, z), new Vector2f(s, t), new Vector3f(nx, ny, nz));
	}
	
	public float[] getData() {
		return new float[]{
			position.x, position.y, position.z,
			texCoord.x, texCoord.y,
			normal.x, normal.y, normal.z
		};
	}
	
	public int getLength() {
		return LENGTH;
	}
	
	public String toString() {
		return "X: " + position.x + "\tY:" + position.y + "\tZ: " + position.z + "\nNormal: " + normal.x + ", " + normal.y + ", " + normal.z;
	}
}