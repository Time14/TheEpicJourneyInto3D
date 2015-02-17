package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import java.security.InvalidParameterException;

import com.idek.util.Util;

public class RenderableTexture extends Texture {
	
	public static final int DEFAULT_WIDTH = 128;
	public static final int DEFAULT_HEIGHT = 128;
	
	public static final int COMPONENT_DEPTH32 = GL_DEPTH_COMPONENT32;
	public static final int COMPONENT_DEPTH24 = GL_DEPTH_COMPONENT24;
	public static final int COMPONENT_DEPTH16 = GL_DEPTH_COMPONENT16;
	public static final int COMPONENT_DEPTH = GL_DEPTH_COMPONENT;
	public static final int ATTACHEMENT_DEPTH = GL_DEPTH_ATTACHMENT;
	public static final int FORMAT_RGBA = GL_RGBA;
	public static final int FORMAT_BGRA = GL_BGRA;
	public static final int FORMAT_RGB = GL_RGB;
	
	private int id = -2;
	private int fbo = -2;
	private int rbo = -2;
	
	public RenderableTexture(int width, int height, int format, boolean useDepthBuffer) {
		
	}
	
	public RenderableTexture genTexture(int width, int height, int[] internalFormat, int[] attachments, boolean useDepthBuffer) {
		this.width = width;
		this.height = height;
		
		id = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		
		int type;
		
		switch(internalFormat[0]) {
		default: type = GL_UNSIGNED_BYTE; break;
		case GL_DEPTH_COMPONENT: type = GL_FLOAT; break;
		case GL_DEPTH_COMPONENT16: type = GL_FLOAT; break;
		}
		
		if(internalFormat[1] == 0)
			internalFormat[1] = GL_RGBA;
		
		glTexImage2D(GL_TEXTURE_2D, 0, internalFormat[0], width, height, 0, internalFormat[1], type, 0);
		
		fbo = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, fbo);
		if(useDepthBuffer) {
			rbo = glGenRenderbuffers();
			glBindRenderbuffer(GL_RENDERBUFFER, rbo);
			glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
			glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, rbo);
		}
		
		glFramebufferTexture2D(GL_FRAMEBUFFER, attachments[0], GL_TEXTURE_2D, id, 0);
		
		int[] drawBuffers = new int[attachments.length];
		
		for(int i = 0; i < attachments.length; i++)
			if(attachments[i] == GL_DEPTH_ATTACHMENT)
				drawBuffers[i] = GL_NONE;
			else
				drawBuffers[i] = attachments[i];
		
		glDrawBuffer(drawBuffers);
		
		return this;
	}
	
	public RenderableTexture bindAsRenderTarget() {
		
		
		
		return this;
	}
	
	public static final int GL_COLOR_ATTACHMENT(int attachment) {
		if(attachment > 15)
			throw new InvalidParameterException("Attachment ID must be in range of 0 - 15. INVALID: " + attachment);
		return GL_COLOR_ATTACHMENT0 + attachment;
	}
	
}