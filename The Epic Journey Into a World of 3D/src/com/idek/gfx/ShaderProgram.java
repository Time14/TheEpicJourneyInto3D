package com.idek.gfx;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.util.Loader;
import com.idek.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;

public class ShaderProgram {
	
	private int id = -2;
	
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
		glUseProgram(id);
	}
	
	public int getID() {
		return id;
	}
	
	public void sendMatrix(Matrix4f matrix) {
		bind();
		int location = glGetUniformLocation(id, "matrix");
		FloatBuffer buffer = Util.createFloatBuffer(16);
		matrix.store(buffer);
		buffer.flip();
		glUniformMatrix4(location, false, buffer);
	}
	
	
	//Shader Programs
	
	public static final ShaderProgram SHADER_3D = new ShaderProgram("res/shader/shader3D.vsh", "res/shader/shader3D.fsh");
}