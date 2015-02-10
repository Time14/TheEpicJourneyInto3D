package com.idek.gfx.shader;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.gfx.vertex.Vertex3D;
import com.idek.util.Util;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram3D extends ShaderProgram {
	
	public static final String UNIFORM_NAME_TRANSFORM_MATRIX = "transformMatrix";
	public static final String UNIFORM_NAME_VIEW_MATRIX = "viewMatrix";
	public static final String UNIFORM_NAME_PROJECTION_MATRIX = "projectionMatrix";
	public static final String UNIFORM_NAME_TEXTURES = "textures";
	
	public ShaderProgram3D() {
		super("res/shader/shader3D.vsh", "res/shader/shader3D.fsh");
		initSamplers();
	}
	
	protected void registerUniformLocations() {
		registerUniformLocation(UNIFORM_NAME_TRANSFORM_MATRIX);
		registerUniformLocation(UNIFORM_NAME_VIEW_MATRIX);
		registerUniformLocation(UNIFORM_NAME_PROJECTION_MATRIX);
		registerUniformLocation(UNIFORM_NAME_TEXTURES);
	}
	
	public void initAttributes() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 3 * Float.SIZE/8);
		glVertexAttribPointer(2, 3, GL_FLOAT, true, Vertex3D.LENGTH * Float.SIZE/8, 5 * Float.SIZE/8);
	}
	
	public final void sendViewMatrix(Matrix4f viewMatrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_VIEW_MATRIX), false, Util.toFloatBuffer(viewMatrix));
	}
	
	public final void sendTransformMatrix(Matrix4f transformMatrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_TRANSFORM_MATRIX), false, Util.toFloatBuffer(transformMatrix));
	}
	
	public final void sendProjectionMatrix(Matrix4f projectionMatrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_PROJECTION_MATRIX), false, Util.toFloatBuffer(projectionMatrix));
	}
	
	public final void initSamplers() {
		bind();
		glUniform1(ul.get(UNIFORM_NAME_TEXTURES), Util.toIntBuffer(new int[]{0, 1}));
	}
	
	public static final ShaderProgram3D INSTANCE = new ShaderProgram3D();
}