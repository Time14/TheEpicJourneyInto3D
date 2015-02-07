package com.idek.gfx;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Transform {
	
	private static float fov = 90;
	private static float zNear = 1;
	private static float zFar = 100;
	
	public Vector3f position;
	public Vector3f rotation;
	public Vector3f scale;
	
	public Transform() {
		this(new Vector3f());
	}
		
	public Transform(Vector3f position) {
		this(position, new Vector3f());	
	}
	
	public Transform(Vector3f position, Vector3f rotation) {
		this(position, rotation, new Vector3f(1, 1, 1));
	}
	
	public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Matrix4f getMatrix(boolean project3D) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		matrix.translate(position);
		matrix.rotate(rotation.x, new Vector3f(1, 0, 0));
		matrix.rotate(rotation.y, new Vector3f(0, 1, 0));
		matrix.rotate(rotation.z, new Vector3f(0, 0, 1));
		matrix.scale(scale);
		
		if(project3D) {
			return Matrix4f.mul(getPerspectiveMatrix(), matrix, matrix);
		} else {
			return matrix;
		}
	}
	
	public static final Matrix4f getPerspectiveMatrix() {
		Matrix4f matrix = new Matrix4f();
		
		float tanHalfFOV = (float)Math.tan(fov/2);
		float zRange = zNear - zFar;
		float ar = ((float)Display.getHeight()) / Display.getWidth();
		
		matrix.m00 = 1f/(tanHalfFOV*ar);	matrix.m10 = 0;	matrix.m20 = 0;	matrix.m30 = 0;
		matrix.m01 = 0;	matrix.m11 = 1f/tanHalfFOV;	matrix.m21 = 0;	matrix.m31 = 0;
		matrix.m02 = 0;	matrix.m12 = 0;	matrix.m22 = (-zNear-zFar)/zRange;	matrix.m32 = (2*zNear*zFar)/zRange;
		matrix.m03 = 0;	matrix.m13 = 0;	matrix.m23 = 1;	matrix.m33 = 0;
		
		return matrix;
	}
	
	public Transform translate(Vector3f vec3) {
		position.x += vec3.x;
		position.y += vec3.y;
		position.z += vec3.z;
		
		return this;
	}
	
	public static final void setPerspectiveProjection(float fov, float zNear, float zFar) {
		Transform.fov = fov;
		Transform.zNear = zNear;
		Transform.zFar = zFar;
	}
}