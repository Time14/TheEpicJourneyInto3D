package com.idek.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import java.nio.ByteBuffer;
import java.security.InvalidParameterException;

import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;
import com.idek.util.Util;

public class RenderableTexture extends Texture {
	
	public static final int DEFAULT_INTERNAL_FORMAT = GL_RGBA;
	public static final int DEFAULT_ATTACHMENT = GL_COLOR_ATTACHMENT0;
	
	public static final int DEFAULT_WIDTH = 128;
	public static final int DEFAULT_HEIGHT = 128;
	
	private boolean hasDepthBuffer;
	
	private int id = -2;
	private int fbo = -2;
	private int rbo = -2;
	
	public RenderableTexture() {}
	
	public RenderableTexture(int width, int height) {
		this(width, height, DEFAULT_INTERNAL_FORMAT);
	}
	
	public RenderableTexture(int width, int height, int internalFormat) {
		this(width, height, internalFormat, new int[]{DEFAULT_ATTACHMENT});
	}
	
	public RenderableTexture(int width, int height, int internalFormat, int[] attachments) {
		this(width, height, internalFormat, attachments, false);
	}
	
	public RenderableTexture(int width, int height, int internalFormat, int[] attachments, boolean useDepthBuffer) {
		initialize(width, height, internalFormat, attachments, useDepthBuffer);
	}
	
	public RenderableTexture initialize(int width, int height, int internalFormat, int[] attachments, boolean useDepthBuffer) {
		this.width = width;
		this.height = height;
		hasDepthBuffer = useDepthBuffer;
		
		id = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		
		int type;
		
		switch(internalFormat) {
		default: type = GL_UNSIGNED_BYTE; break;
		case GL_DEPTH_COMPONENT: type = GL_FLOAT; break;
		case GL_DEPTH_COMPONENT16: type = GL_FLOAT; break;
		case GL_DEPTH_COMPONENT24: type = GL_FLOAT; break;
		case GL_DEPTH_COMPONENT32: type = GL_FLOAT; break;
		}
		
		glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, program.getOutputFormat(), type, (ByteBuffer) null);
		
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
		
		glDrawBuffers(Util.toIntBuffer(drawBuffers));
		
//		glBindFramebuffer(GL_FRAMEBUFFER, 0);
//		glBindRenderbuffer(GL_RENDERBUFFER, 0);
		
		return this;
	}
	
	public RenderableTexture releaseRenderTarget() {
		glBindFramebuffer(GL_DRAW_FRAMEBUFFER, 0);
		return this;
	}
	
	public RenderableTexture bindAsRenderTarget(boolean clear) {
		glBindFramebuffer(GL_DRAW_FRAMEBUFFER, fbo);
		glClearColor(1, 1, 0, 1);
		if(clear)
			glClear(GL_COLOR_BUFFER_BIT | (hasDepthBuffer ? GL_DEPTH_BUFFER_BIT : 0));
		return this;
	}
	
	public boolean hasDepthBuffer() {
		return hasDepthBuffer;
	}
}