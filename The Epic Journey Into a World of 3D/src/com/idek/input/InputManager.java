package com.idek.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.idek.gfx.Camera;
import com.idek.gfx.entity.Entity;
import com.idek.main.Core;

import static org.lwjgl.input.Keyboard.*;

public class InputManager {
	
	public static final int LEFT_MOUSE_BUTTON = 0;
	public static final int RIGHT_MOUSE_BUTTON = 1;
	public static final int MOUSE_WHEEL_BUTTON = 2;
	
	private Core core;
	
	private Camera camera = Camera.INSTANCE;
	
	public InputManager(Core core) {
		this.core = core;
	}
	
	public void update() {
		checkMouse();
		checkKeyboard();
	}
	
	private void checkMouse() {
				
		camera.rotate(-Mouse.getDY() * Camera.SENSITIVITY, Mouse.getDX() * Camera.SENSITIVITY, 0);
		float rx = camera.getTransform().rotation.x;
		
		if(rx > 90 && rx < 270) {
			if(rx < 270 - rx)
				camera.setRX(90);
			else
				camera.setRX(270);
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
		
		if(isKeyDown(KEY_W))
			camera.moveForward(speed);
		if(isKeyDown(KEY_A))
			camera.moveRight(-speed);
		if(isKeyDown(KEY_S))
			camera.moveForward(-speed);
		if(isKeyDown(KEY_D))
			camera.moveRight(speed);
		if(isKeyDown(KEY_LSHIFT))
			camera.translateY(-speed);
		if(isKeyDown(KEY_SPACE))
			camera.translateY(speed);
		
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