package com.idek.gfx;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Transform transform;
	
	private boolean isUpdated;
	
	public Camera() {
		isUpdated = false;
		transform = new Transform();
	}
	
	public Transform getTransform() {
		return transform;
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
		return (Matrix4f)transform.getMatrix().invert();
	}
	
	public static final Camera INSTANCE = new Camera();
}