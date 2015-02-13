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
import com.idek.gfx.entity.EntityManager;
import com.idek.gfx.light.DirectionalLight;
import com.idek.gfx.light.Light;
import com.idek.gfx.light.LightManager;
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
	
	private EntityManager em;
	private LightManager lm;
	
	public RenderManager(Core core) {
		this.core = core;
		
		em = new EntityManager();
		lm = new LightManager();
		
		initGL();
		
		em.createEntities();
		lm.createLights();
	}
	
	
	
	public RenderManager update() {
		Display.setTitle(Core.DEFAULT_TITLE + " FPS: " + Time.getFPS());
		
		em.update();
		
		return this;
	}
	
	public RenderManager draw() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		lm.update();
		em.draw();
		
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
	
	public RenderManager setBGColor(float r, float g, float b) {
		return setBGColor(r, g, b, 1);
	}
	
	public RenderManager setBGColor(float r, float g, float b, float a) {
		return setBGColor(new Vector4f(r, g, b, a));
	}
	
	public RenderManager setBGColor(Vector4f color) {
		bg = color;

		glClearColor(bg.x, bg.y, bg.z, bg.w);
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
	
	public RenderManager cleanUp() {
		em.cleanUp();
		lm.cleanUp();
		
		ShaderProgram3D.INSTANCE.cleanUp();
		
		return this;
	}
}