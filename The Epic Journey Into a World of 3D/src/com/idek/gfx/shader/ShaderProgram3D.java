package com.idek.gfx.shader;

import java.nio.FloatBuffer;
import java.security.InvalidParameterException;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.gfx.Transform;
import com.idek.gfx.camera.Camera;
import com.idek.gfx.camera.CameraPerspectiveProjection;
import com.idek.gfx.light.Light;
import com.idek.gfx.vertex.Vertex3D;
import com.idek.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram3D extends ShaderProgram {
	
	public static final int OUTPUT_FORMAT = GL_RGBA;
	public static final int MAX_LIGHTS = 10;
	
	public static final String UNIFORM_TRANSFORM_MATRIX = "transformMatrix";
	public static final String UNIFORM_VIEW_MATRIX = "viewMatrix";
	public static final String UNIFORM_PROJECTION_MATRIX = "projectionMatrix";
	public static final String UNIFORM_TEXTURES = "textures";
	public static final String UNIFORM_NUM_LIGHTS = "numLights";
	public static final String UNIFORM_LIGHTS = "lights";
	public static final String UNIFORM_LIGHTS_ENABLED = "enableLights";
	
	public ShaderProgram3D() {
		super("res/shader/shader3D.vsh", "res/shader/shader3D.fsh");
		initSamplers();
		
		sendMatrix(UNIFORM_PROJECTION_MATRIX, CameraPerspectiveProjection.getProjectionMatrix());
	}
	
	protected void registerUniformLocations() {
		registerUniformLocation(UNIFORM_TRANSFORM_MATRIX);
		registerUniformLocation(UNIFORM_VIEW_MATRIX);
		registerUniformLocation(UNIFORM_PROJECTION_MATRIX);
		registerUniformLocation(UNIFORM_TEXTURES);
		registerUniformLocation(UNIFORM_NUM_LIGHTS);
		registerUniformLocation(UNIFORM_LIGHTS_ENABLED);
		
		registerUniformStructArrayLocations(UNIFORM_LIGHTS, Light.STRUCT_COMPONENTS, MAX_LIGHTS);
	}
	
	public void initAttributes() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 3 * Float.SIZE/8);
		glVertexAttribPointer(2, 3, GL_FLOAT, true, Vertex3D.LENGTH * Float.SIZE/8, 5 * Float.SIZE/8);
	}
	
	private final void initSamplers() {
		bind();
		glUniform1(ul.get(UNIFORM_TEXTURES), Util.toIntBuffer(new int[]{0, 1}));
	}
	
	public int getOutputFormat() {
		return OUTPUT_FORMAT;
	}
}