package com.idek.gfx.entity;

import com.idek.gfx.Material;
import com.idek.gfx.Mesh;
import com.idek.gfx.Transform;
import com.idek.gfx.vertex.Vertex3D;

import static org.lwjgl.opengl.GL11.*;

public class EntityQuad extends Entity {
	
	public EntityQuad(float x, float y, float z, float width, float height) {
		super(new Mesh(new Vertex3D[]{
				new Vertex3D(0, height, 0, 0, 0, 0, 0, -1),
				new Vertex3D(width, height, 0, 1, 0, 0, 0, -1),
				new Vertex3D(width, 0, 0, 1, 1, 0, 0, -1),
				new Vertex3D(0, 0, 0, 0, 1, 0, 0, -1)
		}, new int[]{0, 1, 3, 1, 2, 3}), new Transform(x, y, z));
	}
	
	public EntityQuad(float x, float y, float z, float width, float height, Material material) {
		this(x, y, z, width, height);
		sendMaterial(material);
	}
}