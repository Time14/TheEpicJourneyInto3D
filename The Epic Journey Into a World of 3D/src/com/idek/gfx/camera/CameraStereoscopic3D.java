package com.idek.gfx.camera;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;

import org.lwjgl.opengl.Display;

import com.idek.gfx.RenderManager;

public class CameraStereoscopic3D extends Camera {
	
	public static final float DEFAULT_IPD_MEN = 64f / 1000f;
	public static final float DEFAULT_IPD_WOMEN = 62f / 1000f;
	
	public static final float DEFAULT_IPD_GENERAL = (DEFAULT_IPD_MEN + DEFAULT_IPD_WOMEN) / 2;
	
	
	public CameraStereoscopic3D(RenderManager rm) {
		super(rm);
	}

	
	public Camera drawScene() {
		glClearColor(bgc.x, bgc.y, bgc.z, bgc.w);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		lm.update();
		
		moveRight(DEFAULT_IPD_GENERAL / 2);
		glViewport(0, 0, (int)(((float)Display.getWidth())/2), Display.getHeight());
		em.draw();
		
		moveRight(-DEFAULT_IPD_GENERAL);
		glViewport((int)(((float)Display.getWidth())/2), 0, (int)(((float)Display.getWidth())/2), Display.getHeight());
		em.draw();
		
		moveRight(DEFAULT_IPD_GENERAL / 2);
		
		return null;
	}
}