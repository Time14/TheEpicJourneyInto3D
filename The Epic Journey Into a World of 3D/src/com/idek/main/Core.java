package com.idek.main;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.idek.gfx.RenderManager;
import com.idek.input.InputManager;


public class Core {
	
	public static final String DEFAULT_TITLE = "Independent Developing Economic Kompany";
	
	public static boolean running = true;
	
	private InputManager im;
	private RenderManager rm;
	
	public Core() {
		initDisplay();
		
		im = new InputManager(this);
		rm = new RenderManager(this);
		
		while(!Display.isCloseRequested() && running) {
			gameLoop();
		}
		
		cleanUp();
	}
	
	private void initDisplay() {
		try {
		
			Display.setTitle(DEFAULT_TITLE);
			Display.setDisplayMode(new DisplayMode(800, 600));
//			Display.setDisplayMode(Display.getDesktopDisplayMode());
//			Display.setFullscreen(true);
//			Display.setVSyncEnabled(true);
			Display.create();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void gameLoop() {
		im.update();
		rm.update();
		rm.draw();
		Display.update();
	}
	
	public RenderManager getRenderManager() {
		return rm;
	}
	
	public InputManager getInputManager() {
		return im;
	}
	
	private void cleanUp() {
		im.cleanUp();
		rm.cleanUp();
	}
	
	public static final void exit() {
		running = false;
	}
}
