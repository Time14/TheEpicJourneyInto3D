package com.idek.gfx.camera;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import com.idek.gfx.RenderManager;
import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;

public class CameraPerspectiveProjection extends Camera {
	
	public static final float DEFAULT_FOV = 90;
	public static final float DEFAULT_ZNEAR = 0.0001f;
	public static final float DEFAULT_ZFAR = 100;
	
	private static float fov = DEFAULT_FOV;
	private static float zNear = DEFAULT_ZNEAR;
	private static float zFar = DEFAULT_ZFAR;
	
	public CameraPerspectiveProjection(RenderManager rm) {
		super(rm);
	}
	
	public Camera drawScene() {
		glClearColor(bgc.x, bgc.y, bgc.z, bgc.w);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		lm.update();
		em.draw();
		
		return this;
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
		CameraPerspectiveProjection.fov = fov;
		CameraPerspectiveProjection.zNear = zNear;
		CameraPerspectiveProjection.zFar = zFar;
	}
	
	public static final ShaderProgram SHADER_PROGRAM = new ShaderProgram3D();
}