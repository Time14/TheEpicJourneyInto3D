package com.idek.gfx.shader;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.gfx.vertex.Vertex3D;
import com.idek.util.Util;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram3D extends ShaderProgram {
	
	public static final String UNIFORM_NAME_MATRIX = "matrix";
	public static final String UNIFORM_NAME_TEXTURE = "texture";
	
	public ShaderProgram3D() {
		super("res/shader/shader3D.vsh", "res/shader/shader3D.fsh");
	}
	
	protected void registerUniformLocations() {
		registerUniformLocation(UNIFORM_NAME_MATRIX);
		registerUniformLocation(UNIFORM_NAME_TEXTURE);
	}
	
	public void initAttributes() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 3 * Float.SIZE/8);
		glVertexAttribPointer(2, 3, GL_FLOAT, true, Vertex3D.LENGTH * Float.SIZE/8, 5 * Float.SIZE/8);
	}
	
	public final void sendMatrix(Matrix4f matrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_MATRIX), false, Util.toFloatBuffer(matrix));
	}
	
	public final void sendActiveTexture(int at) {
		bind();
		glUniform1i(ul.get(UNIFORM_NAME_TEXTURE), at);
	}
	
	public static final ShaderProgram3D INSTANCE = new ShaderProgram3D();
}