package com.idek.main;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.idek.gfx.Camera;
import com.idek.gfx.RenderManager;
import com.idek.input.InputManager;
import com.idek.time.Time;


/**
 * 
 * @author Alfred Sporre & Edvard Thörnros
 *
 */
public class Core {
	
	public static final boolean VSYNC_ENABLED = true;
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	public static final String DEFAULT_TITLE = "Independent Developing Economic Kompany (TEJIAWO3D)";
	
	public static boolean running = true;
	
	private InputManager im;
	private RenderManager rm;
	
	public Core() {
				
		initDisplay();
		
		im = new InputManager(this);
		rm = new RenderManager(this).setBGColor(0, 0, 0);
		
		while(!Display.isCloseRequested() && running) {
			gameLoop();
		}
		
		cleanUp();
	}
	
	private void initDisplay() {
		try {
		
			Display.setTitle(DEFAULT_TITLE);
			Display.setDisplayMode(new DisplayMode(Core.DEFAULT_WIDTH, Core.DEFAULT_HEIGHT));
			Display.setVSyncEnabled(VSYNC_ENABLED);
			Display.create();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void gameLoop() {
		Time.update();
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
