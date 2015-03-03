package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;

import java.nio.FloatBuffer;

import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;
import com.idek.gfx.vertex.Vertex3D;
import com.idek.util.Util;

public class Mesh {
	
	private static int currentVAO = -2;
	
	private int vao = -2;
	private int vbo = -2;
	private int ibo = -2;
	
	private int mode = GL_TRIANGLES;
	
	private Vertex3D[] vertices;
	private int[] indices;
	
	private ShaderProgram program = ShaderProgram3D.INSTANCE;
	
	public Mesh() {}
	
	public Mesh(ShaderProgram program) {
		this.program = program;
	}
	
	public Mesh(ShaderProgram program, Vertex3D[] vertices) {
		this.program = program;
		createVAO(vertices);
	}
	
	public Mesh(Vertex3D[] vertices) {
		createVAO(vertices);
	}
	
	public Mesh(ShaderProgram program, Vertex3D[] vertices, int[] indices) {
		this.program = program;
		createVAO(vertices, indices);
	}
	
	public Mesh(Vertex3D[] vertices, int[] indices) {
		createVAO(vertices, indices);
	}
	
	public void createVAO(Vertex3D[] vertices) {
		this.vertices = vertices;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.toFloatBuffer(vertices), GL_STATIC_DRAW);
		
		program.initAttributes();
	}
	
	public void createVAO(Vertex3D[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		
		glBufferData(GL_ARRAY_BUFFER, Util.toFloatBuffer(vertices), GL_STATIC_DRAW);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.toIntBuffer(indices), GL_STATIC_DRAW);
		
		program.initAttributes();
	}
	
	public void draw() {
		if(currentVAO != vao) {
			glBindVertexArray(vao);
			currentVAO = vao;
		}
		
		if(indices == null) {
			glDrawArrays(mode, 0, vertices.length);
		} else {
			
			glDrawElements(mode, indices.length, GL_UNSIGNED_INT, 0);
		}
	}
	
	public Mesh setMode(int mode) {
		this.mode = mode;
		return this;
	}
	
	public void cleanUp() {
		glDeleteVertexArrays(vao);
		glDeleteBuffers(vbo);
		
		if(indices == null) {
			glDeleteBuffers(ibo);
		}
	}
}