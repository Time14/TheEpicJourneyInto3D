package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
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
import com.idek.gfx.light.DirectionalLight;
import com.idek.gfx.light.Light;
import com.idek.gfx.light.PointLight;
import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;
import com.idek.main.Core;
import com.idek.time.Time;
import com.idek.util.Loader;
import com.idek.util.Util;

public class RenderManager {
	
	private Core core;
	
	private Vector4f bg = new Vector4f(0, 0, 0, 1);
	
	private HashMap<String, Entity> entities;
	
	private HashMap<String, Light> lights;
	
//	public ArrayList<Entity> funkyToruses;
	
	public RenderManager(Core core) {
		this.core = core;
		entities = new HashMap<>();
		lights = new HashMap<>();
		
		initGL();
		
		createEntities();
		createLights();
	}
	
	public RenderManager update() {
		
		Display.setTitle(Core.DEFAULT_TITLE + " FPS: " + Time.getFPS());
		
		for(Entity e : entities.values()) {
			e.update();
		}
		
		getEntity("i0").rotateY(1);
		getEntity("i1").rotateX(1);
		getEntity("i2").rotateZ(1);
		getEntity("i3").rotate(1, 1, 1);
		
		return this;
	}
	
	public RenderManager draw() {
		glClearColor(bg.x, bg.y, bg.z, bg.w);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		ShaderProgram3D.INSTANCE.bind();
		
		boolean lightsNeedUpdate = false;
		for(Light l : lights.values()) {
			if(!l.isUpdated()) {
				lightsNeedUpdate = true;
				break;
			}
		}
		
		if(lightsNeedUpdate) {
			Light[] data = new Light[lights.size()];
			data = lights.values().toArray(data);
			
			for(Light l : data)
				l.setUpdated(true);
			
			ShaderProgram3D.INSTANCE.sendLights(data);
		}
		
		for(Entity e : entities.values()) {
			e.draw();
		}
		
		return this;
	}
	
	private RenderManager initGL() {
		try {
			System.out.println("OpenGL v." + glGetString(GL_VERSION));
			
//			glEnable(GL_MULTISAMPLE);
//			glEnable(GL_LINE_SMOOTH);
			
			glEnable(GL_DEPTH_TEST);
			glDepthMask(true);
			
			glEnable(GL_DEPTH_CLAMP);
			
			glEnable(GL_CULL_FACE);
			glCullFace(GL_BACK);
			glFrontFace(GL_CW);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public RenderManager createLights() {
		
//		addLight("Light1", new PointLight());
//		
//		((PointLight)getLight("Light1")).color = new Vector3f(1, 1, 1);
		
		addLight("Light2", new DirectionalLight());
		((DirectionalLight)getLight("Light2")).direction = new Vector3f(0, 1, 0);
		
		return this;
	}
	
	public RenderManager createEntities() {
		addEntity("i0", new Entity(Loader.loadOBJMesh("res/obj/icoSphere/funkyTorus.obj")));
		getEntity("i0").setPosition(3, 0, 3);
		addEntity("i1", new Entity(Loader.loadOBJMesh("res/obj/icoSphere/funkyTorus.obj")));
		getEntity("i1").setPosition(-3, 0, 3);
		addEntity("i2", new Entity(Loader.loadOBJMesh("res/obj/icoSphere/funkyTorus.obj")));
		getEntity("i2").setPosition(0, 0, -4);
		addEntity("i3", new Entity(Loader.loadOBJMesh("res/obj/icoSphere/funkyTorus.obj")));
		getEntity("i3").setPosition(0, 0, 0);
		
		return this;
	}
	
	public RenderManager setBGColor(float r, float g, float b) {
		setBGColor(r, g, b, 1);
		return this;
	}
	
	public RenderManager setBGColor(float r, float g, float b, float a) {
		setBGColor(new Vector4f(r, g, b, a));
		return this;
	}
	
	public RenderManager setBGColor(Vector4f color) {
		bg = color;
		return this;
	}
	
	public RenderManager setFullsreen(boolean b) {
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
		
		return this;
	}
	
	public RenderManager adjustViewportToDisplay() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	
		return this;
	}
	
	public RenderManager addEntity(String key, Entity e) {
		entities.put(key, e);
	
		return this;
	}
	
	public RenderManager addLight(String key, Light l) {
		lights.put(key, l);
	
		return this;
	}
	
	public Entity getEntity(String key) {
		return entities.get(key);
	}
	
	public Light getLight(String key) {
		return lights.get(key).setUpdated(false);
	}
	
	public RenderManager cleanUp() {
		for(Entity e : entities.values()) {
			e.cleanUp();
		}
		
		ShaderProgram3D.INSTANCE.cleanUp();
		
		return this;
	}
}