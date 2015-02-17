package com.idek.gfx;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	public static final float SENSITIVITY = .5f;
	public static final float DEFAULT_FOV = 90;
	public static final float DEFAULT_ZNEAR = 0.0001f;
	public static final float DEFAULT_ZFAR = 100;
	
	private static float fov = DEFAULT_FOV;
	private static float zNear = DEFAULT_ZNEAR;
	private static float zFar = DEFAULT_ZFAR;
	
	
	private Transform transform;
	
	private boolean isUpdated;
	
	private boolean idle;
	
	public Camera() {
		isUpdated = false;
		idle = false;
		transform = new Transform();
	}
	
	public Transform getTransform() {
		return transform;
	}
	
	public Camera moveForward(float distance) {
		
		transform.translateZ((float)Math.cos(Math.toRadians(transform.rotation.y)) * distance);
		transform.translateX((float)Math.sin(Math.toRadians(transform.rotation.y)) * distance);
		
		isUpdated = false;
		
		return this;
	}
	
	public Camera moveRight(float distance) {
		
		transform.translateX((float)Math.cos(Math.toRadians(transform.rotation.y)) * distance);
		transform.translateZ((float)Math.sin(-Math.toRadians(transform.rotation.y)) * distance);
		
		isUpdated = false;
		
		return this;
	}
	
	public Camera setRX(float rx) {
		transform.setRX(rx);
		isUpdated = false;
		return this;
	}
	
	public Camera setRY(float ry) {
		transform.setRY(ry);
		isUpdated = false;
		return this;
	}
	
	public Camera setRZ(float rz) {
		transform.setRZ(rz);
		isUpdated = false;
		return this;
	}
	
	public Camera setRotation(float rx, float ry, float rz) {
		transform.setRotation(rx, ry, rz);
		isUpdated = false;
		return this;
	}
	
	public Camera setRotation(Vector3f rotation) {
		transform.setRotation(rotation);
		isUpdated = false;
		return this;
	}
	
	public Camera rotateX(float rx) {
		transform.rotateX(rx);
		isUpdated = false;
		return this;
	}
	
	public Camera rotateY(float ry) {
		transform.rotateY(ry);
		isUpdated = false;
		return this;
	}
	
	public Camera rotateZ(float rz) {
		transform.rotateZ(rz);
		isUpdated = false;
		return this;
	}
	
	public Camera rotate(float rx, float ry, float rz) {
		transform.rotate(rx, ry, rz);
		isUpdated = false;
		return this;
	}
	
	public Camera rotate(Vector3f rotation) {
		transform.rotate(rotation);
		isUpdated = false;
		return this;
	}
	
	public Camera setX(float x) {
		transform.setX(x);
		isUpdated = false;
		return this;
	}
	
	public Camera setY(float y) {
		transform.setY(y);
		isUpdated = false;
		return this;
	}
	
	public Camera setZ(float z) {
		transform.setZ(z);
		isUpdated = false;
		return this;
	}
	
	public Camera setPosition(float x, float y, float z) {
		transform.setPosition(x, y, z);
		isUpdated = false;
		return this;
	}
	
	public Camera setPosition(Vector3f postition) {
		transform.setPosition(postition);
		isUpdated = false;
		return this;
	}
	
	public Camera translateX(float x) {
		transform.translateX(x);
		isUpdated = false;
		return this;
	}
	
	public Camera translateY(float y) {
		transform.translateY(y);
		isUpdated = false;
		return this;
	}
	
	public Camera translateZ(float z) {
		transform.translateZ(z);
		isUpdated = false;
		return this;
	}
	
	public Camera translate(float x, float y, float z) {
		transform.translate(x, y, z);
		isUpdated = false;
		return this;
	}
	
	public Camera translate(Vector3f offset) {
		transform.translate(offset);
		isUpdated = false;
		return this;
	}
	
	public boolean isUpdated() {
		return isUpdated;
	}
	
	public Camera setUpdated(boolean updated) {
		isUpdated = updated;
		return this;
	}
	
	public Matrix4f getViewMatrix() {
		if(idle)
			return (Matrix4f)new Matrix4f().setIdentity();
		else
			return (Matrix4f)transform.getMatrix().invert();
	}
	
	public static final Matrix4f getProjectionMatrix() {
		Matrix4f matrix = new Matrix4f();
		
		float tanHalfFOV = (float)Math.tan(Math.toRadians(fov/2));
		float zRange = zNear - zFar;
		float ar = ((float)Display.getWidth()) / Display.getHeight();
		
		matrix.m00 = 1f/(tanHalfFOV*ar); matrix.m10 = 0;			 matrix.m20 = 0;					matrix.m30 = 0;
		matrix.m01 = 0;					 matrix.m11 = 1f/tanHalfFOV; matrix.m21 = 0;					matrix.m31 = 0;
		matrix.m02 = 0;					 matrix.m12 = 0;			 matrix.m22 = (-zNear-zFar)/zRange;	matrix.m32 = (2*zNear*zFar)/zRange;
		matrix.m03 = 0;					 matrix.m13 = 0;			 matrix.m23 = 1;					matrix.m33 = 0;
		
		return matrix;
	}
	
	public static final void setPerspectiveProjection(float fov, float zNear, float zFar) {
		Camera.fov = fov;
		Camera.zNear = zNear;
		Camera.zFar = zFar;
	}
	
	public Camera setIdle(boolean idle) {
		isUpdated = false;
		this.idle = idle;
		return this;
	}
	
	public boolean isIdle() {
		return idle;
	}
	
	public static final Camera INSTANCE = new Camera();
}