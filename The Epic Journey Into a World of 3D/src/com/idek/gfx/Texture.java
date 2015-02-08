package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;

import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;

import com.idek.util.Loader;
import com.idek.util.Util;

public class Texture {
	
	private int id  	= -2;
	private int width  	= -2;
	private int height  = -2;
	
	public Texture() {}
	
	public Texture(String path) {
		this(path, true);
	}
	
	public Texture(String path, boolean repeat) {
		loadTexture(path, repeat);
	}
	
	public Texture loadTexture(String path, boolean repeat) {
		try {
			BufferedImage image = Loader.loadImage(path);
			
			width = image.getWidth();
			height = image.getHeight();
			
			int[] pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			
			id = glGenTextures();
			
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
			if(repeat) {
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
			} else {
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			}
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_BGRA, GL_UNSIGNED_BYTE, Util.toIntBuffer(pixels));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public Texture bind(int target) {
		
		if(target > 31 || target < 0) {
			throw new InvalidParameterException("Target must be in range 0 - 31");
		}
		
		glActiveTexture(GL_TEXTURE0 + target);
		glBindTexture(GL_TEXTURE_2D, id);
		return this;
	}
	
	public static final Texture DEFAULT_TEXTURE = new Texture("res/texture/UV_mapper.png");
}