package com.idek.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.idek.main.Core;

import static org.lwjgl.input.Keyboard.*;

public class InputManager {
	
	public static final int LEFT_MOUSE_BUTTON = 0;
	public static final int RIGHT_MOUSE_BUTTON = 1;
	public static final int MOUSE_WHEEL_BUTTON = 2;
	
	private Core core;
	
	public InputManager(Core core) {
		this.core = core;
	}
	
	public void update() {
		checkMouse();
		checkKeyboard();
	}
	
	private void checkMouse() {
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
		
		if(isKeyDown(KEY_UP))
			core.getRenderManager().getEntity(0).getTransform().translate(new Vector3f(0, 0, speed));
		if(isKeyDown(KEY_DOWN))
			core.getRenderManager().getEntity(0).getTransform().translate(new Vector3f(0, 0, -speed));
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				//Key Pressed
				
				switch(Keyboard.getEventKey()) {
				case KEY_ESCAPE:
					Core.exit();
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