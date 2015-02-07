package com.idek.gfx.entity;

import com.idek.gfx.Mesh;
import com.idek.gfx.ShaderProgram;
import com.idek.gfx.Transform;
import com.idek.gfx.Vertex;

public class Entity {
	
	private Mesh mesh;
	
	private Transform transform;
	
	public Entity() {
		
	}
	
	public Entity(Mesh mesh) {
		this(mesh, new Transform());
	}

	public Entity(Mesh mesh, Transform transform) {
		this.mesh = mesh;
		this.transform = transform;
	}
	
	public Entity sendMesh(Mesh mesh) {
		
		this.mesh = mesh;
		return this;
	}
	
	public Entity sendTransform(Transform transform) {
		
		this.transform = transform;
		return this;
	}
	
	public Entity initMesh(Vertex... vertices) {
		
		this.mesh = new Mesh(vertices);
		return this;
	}
	
	public Entity initMesh(Vertex[] vertices, int[] indices) {
		
		this.mesh = new Mesh(vertices, indices);
		return this;
	}
	
	public void update() {
		//TODO:
		//Transform sh!t : - D
	}
	
	public void draw() {
		ShaderProgram.SHADER_3D.sendMatrix(transform.getMatrix(true));
		mesh.draw();
	}
	
	public Transform getTransform() {
		return transform;
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public void cleanUp() {
		mesh.cleanUp();
	}
}