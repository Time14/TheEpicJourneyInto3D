package com.idek.gfx.camera;

import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.idek.gfx.RenderManager;
import com.idek.gfx.Transform;
import com.idek.gfx.entity.EntityManager;
import com.idek.gfx.light.LightManager;
import com.idek.gfx.shader.ShaderProgram;

public abstract class Camera {
	
	public static final float DEFAULT_SENSITIVITY = .5f;
	
	public static final ShaderProgram DEFAULT_SHADER_PROGRAM = CameraPerspectiveProjection.SHADER_PROGRAM;
	public static final Vector4f DEFAULT_BACKGROUND_COLOR = new Vector4f(0, 0, 0, 1);
	
	protected float sensitivity = DEFAULT_SENSITIVITY;
	
	protected Transform transform;
	
	protected boolean isUpdated;
	
	protected boolean idle;
	
	protected ShaderProgram program = DEFAULT_SHADER_PROGRAM;
	
	protected Vector4f bgc = DEFAULT_BACKGROUND_COLOR;
	
	protected RenderManager rm;
	protected EntityManager em;
	protected LightManager lm;
	
	public Camera(RenderManager rm) {
		isUpdated = false;
		idle = false;
		transform = new Transform();
		
		this.rm = rm;
		em = rm.getEntityManager();
		lm = rm.getLightManager();
	}
	
	public abstract Camera drawScene();
	
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
	
	public ShaderProgram getShaderProgram() {
		return program;
	}
	
	public float getSensitivity() {
		return sensitivity;
	}
	
	public boolean isUpdated() {
		return isUpdated;
	}
	
	public Camera setUpdated(boolean updated) {
		isUpdated = updated;
		return this;
	}
	
	public Matrix4f getViewMatrix() {
		isUpdated = true;
		if(idle)
			return (Matrix4f)new Matrix4f().setIdentity();
		else
			return (Matrix4f)transform.getMatrix().invert();
	}
	
	public Camera setBGColor(float r, float g, float b) {
		return setBGColor(r, g, b, 1);
	}
	
	public Camera setBGColor(float r, float g, float b, float a) {
		return setBGColor(new Vector4f(r, g, b, a));
	}
	
	public Vector4f getBackgroundColor() {
		return bgc;
	}
	
	public Camera setBGColor(Vector4f color) {
		bgc = color;
		return this;
	}
	
	public Camera setIdle(boolean idle) {
		isUpdated = false;
		this.idle = idle;
		return this;
	}
	
	public boolean isIdle() {
		return idle;
	}
	
	public void cleanUp() {
		program.cleanUp();
	}
}