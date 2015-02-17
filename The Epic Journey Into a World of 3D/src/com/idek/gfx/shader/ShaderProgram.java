package com.idek.gfx.shader;

import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.util.Loader;
import com.idek.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;

public abstract class ShaderProgram {
	
	protected static int currentProgram;
	
	private int id = -2;
	
	protected HashMap<String, Integer> ul;
	
	public ShaderProgram(String vshp, String fshp) {
		
		int vsh = createShader(vshp, GL_VERTEX_SHADER);
		int fsh = createShader(fshp, GL_FRAGMENT_SHADER);
		
		id = glCreateProgram();
		
		glAttachShader(id, vsh);
		glAttachShader(id, fsh);
		
		glLinkProgram(id);
		
		if(glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("Error linking shader program \"" + id + "\"");
			System.err.println("-------------------------------- START --------------------------------");
			System.err.println(glGetProgramInfoLog(id, glGetProgrami(id, GL_INFO_LOG_LENGTH)));
			System.err.println("--------------------------------  END  --------------------------------");
		}
		
		glDetachShader(id, vsh);
		glDetachShader(id, fsh);
		glDeleteShader(vsh);
		glDeleteShader(fsh);
		
		ul = new HashMap<>();
		registerUniformLocations();
	}
	
	private int createShader(String path, int type) {
		
		int shader = glCreateShader(type);
		glShaderSource(shader, Loader.loadSource(path));
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Error in " + (type == GL_VERTEX_SHADER ? "vertex" : "fragment") + " shader at \"" + path + "\"");
			System.err.println("-------------------------------- START --------------------------------");
			System.err.println(glGetShaderInfoLog(shader, glGetShaderi(shader, GL_INFO_LOG_LENGTH)));
			System.err.println("--------------------------------  END  --------------------------------");
		}
	
		return shader;
	}
	
	public void bind() {
		if(id == currentProgram)
			return;
		glUseProgram(id);
		currentProgram = id;
	}
	
	public int getID() {
		return id;
	}
	
	protected abstract void registerUniformLocations();
	public abstract void initAttributes();
	public abstract int getOutputFormat();
	
	protected void registerUniformLocation(String uniform) {
		ul.put(uniform, getUniformLocation(uniform));
	}
	
	private int getUniformLocation(String uniform) {
		return glGetUniformLocation(id, uniform);
	}
	
	public void cleanUp() {
		glDeleteProgram(id);
	}
	
	public static final int currentlyBoundID() {
		return currentProgram;
	}
}