package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;

import java.nio.FloatBuffer;

import com.idek.util.Util;

public class Mesh {
	
	private int vao = -2;
	private int vbo = -2;
	private int ibo = -2;
	
	private Vertex[] vertices;
	private int[] indices;
	
	public Mesh() {}
	
	public Mesh(Vertex[] vertices) {
		createVAO(vertices);
	}
	
	public Mesh(Vertex[] vertices, int[] indices) {
		createVAO(vertices, indices);
	}
	
	public void createVAO(Vertex[] vertices) {
		this.vertices = vertices;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.toFloatBuffer(vertices), GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.SIZE/8, 0);
		glVertexAttribPointer(1, 3, GL_FLOAT, true, 6 * Float.SIZE/8, 3 * Float.SIZE/8);
	}
	
	public void createVAO(Vertex[] vertices, int[] indices) {
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
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.SIZE/8, 0);
		glVertexAttribPointer(1, 3, GL_FLOAT, true, 6 * Float.SIZE/8, 3 * Float.SIZE/8);
	}
	
	public void draw() {
		glBindVertexArray(vao);
		
		if(indices == null) {
			glDrawArrays(GL_TRIANGLES, 0, vertices.length);
		} else {
			
			glDrawElements(GL_LINES, indices.length, GL_UNSIGNED_INT, 0);
		}
	}
	
	public void cleanUp() {
		glDeleteVertexArrays(vao);
		glDeleteBuffers(vbo);
		
		if(indices == null) {
			glDeleteBuffers(ibo);
		}
	}
}