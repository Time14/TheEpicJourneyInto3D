package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.idek.gfx.entity.Entity;
import com.idek.main.Core;
import com.idek.util.Loader;

public class RenderManager {
	
	private Core core;
	
	private Vector4f bg = new Vector4f(0, 0, 0, 1);
	
	private ArrayList<Entity> entities;
	
	public RenderManager(Core core) {
		this.core = core;
		
		initGL();
		
		createEntities();
	}
	
	public void update() {
		for(Entity e : entities) {
			e.update();
		}
	}
	
	public void draw() {
		glClearColor(bg.x, bg.y, bg.z, bg.w);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		ShaderProgram.SHADER_3D.bind();
		for(Entity e : entities) {
			e.draw();
		}
	}
	
	private void initGL() {
		try {
			System.out.println("OpenGL v." + glGetString(GL_VERSION));
			
			glEnable(GL_DEPTH_TEST);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createEntities() {
		entities = new ArrayList<>();
		
		entities.add(new Entity().sendMesh(Loader.loadOBJMesh("res/obj/monkeyHead.obj")).sendTransform(new Transform().translate(new Vector3f(0, 0, 100f))));
		entities.get(0).getTransform().scale = new Vector3f(10, 10, -10);
	}
	
	public void setBGColor(float r, float g, float b) {
		setBGColor(r, g, b, 1);
	}
	
	public void setBGColor(float r, float g, float b, float a) {
		setBGColor(new Vector4f(r, g, b, a));
	}
	
	public void setBGColor(Vector4f color) {
		bg = color;
	}
	
	public Entity getEntity(int index) {
		return entities.get(index);
	}
	
	public void cleanUp() {
		for(Entity e : entities) {
			e.cleanUp();
		}
	}
}