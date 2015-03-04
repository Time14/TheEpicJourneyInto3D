package com.idek.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.idek.gfx.RenderManager;
import com.idek.gfx.camera.Camera;
import com.idek.gfx.entity.Entity;
import com.idek.main.Core;

import static org.lwjgl.input.Keyboard.*;

public class InputManager {
	
	public static final int LEFT_MOUSE_BUTTON = 0;
	public static final int RIGHT_MOUSE_BUTTON = 1;
	public static final int MOUSE_WHEEL_BUTTON = 2;
	
	private Core core;
	
	private RenderManager rm;
	
	public InputManager(RenderManager rm, Core core) {
		this.core = core;
		this.rm = rm;
	}
	
	public void update() {
		checkMouse();
		checkKeyboard();
	}
	
	private void checkMouse() {
		
		Camera c = rm.getCurrentCamera();
		
		c.rotate(-Mouse.getDY() * c.getSensitivity(), Mouse.getDX() * c.getSensitivity(), 0);
		float rx = c.getTransform().rotation.x;
		
		if(rx > 90 && rx < 270) {
			if(rx < 270 - rx)
				c.setRX(90);
			else
				c.setRX(270);
		}
		
		while(Mouse.next()) {
			if(Mouse.getEventButtonState()) {
				//Button Pressed
				
				switch(Mouse.getEventButton()) {
				
				}
			} else {
				//Button Released
				
				switch(Mouse.getEventButton()) {
				
				}
			}
		}
	}
	
	private void checkKeyboard() {
		float speed = 0.1f;
		
		Camera c = rm.getCurrentCamera();
		
		if(isKeyDown(KEY_W))
			c.moveForward(speed);
		if(isKeyDown(KEY_A))
			c.moveRight(-speed);
		if(isKeyDown(KEY_S))
			c.moveForward(-speed);
		if(isKeyDown(KEY_D))
			c.moveRight(speed);
		if(isKeyDown(KEY_LSHIFT))
			c.translateY(-speed);
		if(isKeyDown(KEY_SPACE))
			c.translateY(speed);
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				//Key Pressed
				
				switch(Keyboard.getEventKey()) {
				case KEY_ESCAPE:
					Core.exit();
					break;
				case KEY_F11:
					core.getRenderManager().setFullsreen(!Display.isFullscreen());
					break;
				case KEY_LMENU:
					Mouse.setGrabbed(!Mouse.isGrabbed());
					break;
				}
			} else {
				//Key Released
				
				switch(Keyboard.getEventKey()) {
				
				}
			}
		}
	}
	
	public void cleanUp() {
		
	}
}