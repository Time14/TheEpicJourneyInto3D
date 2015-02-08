package com.idek.gfx.shader;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.util.Util;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram3D extends ShaderProgram {
	
	public static final String UNIFORM_NAME_MATRIX = "matrix";
	
	public ShaderProgram3D() {
		super("res/shader/shader3D.vsh", "res/shader/shader3D.fsh");
	}
	
	protected void registerUniformLocations() {
		registerUniformLocation(UNIFORM_NAME_MATRIX);
	}
	
	public final void sendMatrix(Matrix4f matrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_MATRIX), false, Util.toFloatBuffer(matrix));
	}
	
	public static final ShaderProgram3D INSTANCE = new ShaderProgram3D();
}