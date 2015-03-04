package com.idek.gfx.entity;

import org.lwjgl.util.vector.Vector3f;

import com.idek.gfx.Material;
import com.idek.gfx.Mesh;
import com.idek.gfx.RenderManager;
import com.idek.gfx.Transform;
import com.idek.gfx.camera.Camera;
import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;
import com.idek.gfx.vertex.Vertex3D;
import com.idek.main.Core;

public class Entity {
	
	private Mesh mesh;
	
	private Material material;
	
	private Transform transform;
	
	private RenderManager rm;
	
	public Entity(RenderManager rm) {
		transform = new Transform();
	}
	
	public Entity(RenderManager rm, Mesh mesh) {
		this(rm, mesh, new Transform());
	}

	public Entity(RenderManager rm, Mesh mesh, Transform transform) {
		this(rm, mesh, transform, new Material());
	}
	
	public Entity(RenderManager rm, Mesh mesh, Transform transform, Material material) {
		this.mesh = mesh;
		this.transform = transform;
		this.material = material;
		this.rm = rm;
	}
	
	public Entity sendMesh(Mesh mesh) {
		this.mesh = mesh;
		return this;
	}
	
	public Entity sendTransform(Transform transform) {
		this.transform = transform;
		return this;
	}
	
	public Entity sendMaterial(Material material) {
		this.material = material;
		return this;
	}
	
	public Entity initMesh(Vertex3D... vertices) {
		
		this.mesh = new Mesh(vertices);
		return this;
	}
	
	public Entity initMesh(Vertex3D[] vertices, int[] indices) {
		
		this.mesh = new Mesh(vertices, indices);
		return this;
	}
	
	public void update() {
		//TODO:
		//Transform sh!t : - D
	}
	
	public void draw() {
		rm.getShaderProgram().bind();
		rm.getShaderProgram().sendMatrix(ShaderProgram3D.UNIFORM_TRANSFORM_MATRIX, transform.getMatrix());
		if(!rm.getCurrentCamera().isUpdated())
			rm.getShaderProgram().sendMatrix(ShaderProgram3D.UNIFORM_VIEW_MATRIX, rm.getCurrentCamera().getViewMatrix());
		material.bind();
		mesh.draw();
	}
	
	public Transform getTransform() {
		return transform;
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public Entity setRX(float rx) {
		transform.setRX(rx);
		return this;
	}
	
	public Entity setRY(float ry) {
		transform.setRY(ry);
		return this;
	}
	
	public Entity setRZ(float rz) {
		transform.setRZ(rz);
		return this;
	}
	
	public Entity setRotation(float x, float y, float z) {
		return setRotation(new Vector3f(x, y, z));
	}
	
	public Entity setRotation(Vector3f rotation) {
		transform.setRotation(rotation);
		return this;
	}
	
	public Entity rotateX(float rotation) {
		transform.rotateX(rotation);
		return this;
	}
	
	public Entity rotateY(float rotation) {
		transform.rotateY(rotation);
		return this;
	}
	
	public Entity rotateZ(float rotation) {
		transform.rotateZ(rotation);
		return this;
	}
	
	public Entity rotate(float x, float y, float z) {
		return rotate(new Vector3f(x, y, z));
	}
	
	public Entity rotate(Vector3f rotation) {
		transform.rotate(rotation);
		return this;
	}
	
	public Entity setScale(float scale) {
		transform.setScale(scale);
		return this;
	}
	
	public Entity setScaleX(float scale) {
		transform.setScaleX(scale);
		return this;
	}
	
	public Entity setScaleY(float scale) {
		transform.setScaleY(scale);
		return this;
	}
	
	public Entity setScaleZ(float scale) {
		transform.setScaleZ(scale);
		return this;
	}
	
	public Entity setScale(float x, float y, float z) {
		return setScale(new Vector3f(x, y, z));
	}
	
	public Entity setScale(Vector3f scale) {
		transform.setScale(scale);
		return this;
	}
	
	public Entity addScale(float scalar) {
		transform.addScale(scalar);
		return this;
	}
	
	public Entity addScaleX(float scale) {
		transform.addScaleX(scale);
		return this;
	}
	
	public Entity addScaleY(float scale) {
		transform.addScaleY(scale);
		return this;
	}
	
	public Entity addScaleZ(float scale) {
		transform.addScaleZ(scale);
		return this;
	}
	
	public Entity addScale(float x, float y, float z) {
		return addScale(new Vector3f(x, y, z));
	}
	
	public Entity addScale(Vector3f scale) {
		transform.addScale(scale);
		return this;
	}
	
	public Entity scale(float scalar) {
		transform.scale(scalar);
		return this;
	}
	
	public Entity scaleX(float scale) {
		transform.scaleX(scale);
		return this;
	}
	
	public Entity scaleY(float scale) {
		transform.scaleY(scale);
		return this;
	}
	
	public Entity scaleZ(float scale) {
		transform.scaleZ(scale);
		return this;
	}
	
	public Entity scale(float x, float y, float z) {
		return scale(new Vector3f(x, y, z));
	}
	
	public Entity scale(Vector3f scale) {
		transform.scale(scale);
		return this;
	}
	
	public Entity setX(float x) {
		transform.setX(x);
		return this;
	}
	
	public Entity setY(float y) {
		transform.setY(y);
		return this;
	}
	
	public Entity setZ(float z) {
		transform.setZ(z);
		return this;
	}
	
	public Entity setPosition(float x, float y, float z) {
		return setPosition(new Vector3f(x, y, z));
	}
	
	public Entity setPosition(Vector3f position) {
		transform.setPosition(position);
		return this;
	}
	
	public Entity translateX(float x) {
		transform.translateX(x);
		return this;
	}
	
	public Entity translateY(float y) {
		transform.translateY(y);
		return this;
	}

	public Entity translateZ(float z) {
		transform.translateZ(z);
		return this;
	}
	
	public Entity translate(float x, float y, float z) {
		return translate(new Vector3f(x, y, z));
	}
	
	public Entity translate(Vector3f offset) {
		transform.translate(offset);
		return this;
	}
	
	public RenderManager getRenderManager() {
		return rm;
	}
	
	public void cleanUp() {
		mesh.cleanUp();
		material.cleanUp();
	}
}