package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.idek.gfx.entity.Entity;
import com.idek.main.Core;
import com.idek.util.Loader;

public class RenderManager {
	
	private Core core;
	
	private Vector4f bg = new Vector4f(0, 0, 0, 1);
	
	private HashMap<String, Entity> entities;
	
	public RenderManager(Core core) {
		this.core = core;
		entities = new HashMap<>();
		
		initGL();
		
		createEntities();
	}
	
	public void update() {
		for(Entity e : entities.values()) {
			e.update();
		}
		
		getEntity("Monkey Head").rotateY(1);
		
	}
	
	public void draw() {
		glClearColor(bg.x, bg.y, bg.z, bg.w);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		ShaderProgram.SHADER_3D.bind();
		for(Entity e : entities.values()) {
			e.draw();
		}
	}
	
	private void initGL() {
		try {
			System.out.println("OpenGL v." + glGetString(GL_VERSION));
			
			glEnable(GL_DEPTH_TEST);
			glDepthMask(true);
			
			glEnable(GL_DEPTH_CLAMP);
			
			glEnable(GL_CULL_FACE);
			glCullFace(GL_BACK);
			glFrontFace(GL_CW);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createEntities() {
		addEntity("Monkey Head", new Entity(Loader.loadOBJMesh("res/obj/monkeyHead.obj")));
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
	
	public void setFullsreen(boolean b) {
		try {
			if(b) {
				Display.setDisplayMode(Display.getDesktopDisplayMode());
				Display.setFullscreen(b);
				adjustViewportToDisplay();
			} else {
				Display.setDisplayMode(new DisplayMode(Core.DEFAULT_WIDTH, Core.DEFAULT_HEIGHT));
				Display.setFullscreen(b);
				adjustViewportToDisplay();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adjustViewportToDisplay() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public void addEntity(String key, Entity e) {
		entities.put(key, e);
	}
	
	public Entity getEntity(String key) {
		return entities.get(key);
	}
	
	public void cleanUp() {
		for(Entity e : entities.values()) {
			e.cleanUp();
		}
	}
}