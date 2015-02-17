package com.idek.gfx.shader;

import java.nio.FloatBuffer;
import java.security.InvalidParameterException;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.gfx.Camera;
import com.idek.gfx.Transform;
import com.idek.gfx.light.Light;
import com.idek.gfx.vertex.Vertex3D;
import com.idek.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram3D extends ShaderProgram {
	
	public static final int OUTPUT_FORMAT = GL_RGBA;
	public static final int MAXIMUM_AMOUNT_OF_LIGHTS = 10;
	
	public static final String[] STRUCT_VAR_NAMES_LIGHT = new String[]{"isDirectional", "fallOff", "position", "color"};
	
	public static final String UNIFORM_NAME_TRANSFORM_MATRIX = "transformMatrix";
	public static final String UNIFORM_NAME_VIEW_MATRIX = "viewMatrix";
	public static final String UNIFORM_NAME_PROJECTION_MATRIX = "projectionMatrix";
	public static final String UNIFORM_NAME_TEXTURES = "textures";
	public static final String UNIFORM_NAME_NUM_LIGHTS = "numLights";
	public static final String UNIFORM_NAME_LIGHTS = "lights";
	
	public ShaderProgram3D() {
		super("res/shader/shader3D.vsh", "res/shader/shader3D.fsh");
		initSamplers();
		
		sendProjectionMatrix(Camera.getProjectionMatrix());
	}
	
	protected void registerUniformLocations() {
		registerUniformLocation(UNIFORM_NAME_TRANSFORM_MATRIX);
		registerUniformLocation(UNIFORM_NAME_VIEW_MATRIX);
		registerUniformLocation(UNIFORM_NAME_PROJECTION_MATRIX);
		registerUniformLocation(UNIFORM_NAME_TEXTURES);
		registerUniformLocation(UNIFORM_NAME_NUM_LIGHTS);
		for(int i = 0; i < MAXIMUM_AMOUNT_OF_LIGHTS; i++) {
			for(int j = 0; j < Light.NUM_COMPONENTS; j++) {
				registerUniformLocation(getStructArrayUniformName(UNIFORM_NAME_LIGHTS, i, STRUCT_VAR_NAMES_LIGHT[j]));
			}
		}
	}
	
	public void initAttributes() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex3D.LENGTH * Float.SIZE/8, 3 * Float.SIZE/8);
		glVertexAttribPointer(2, 3, GL_FLOAT, true, Vertex3D.LENGTH * Float.SIZE/8, 5 * Float.SIZE/8);
	}
	
	public final void sendViewMatrix(Matrix4f viewMatrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_VIEW_MATRIX), false, Util.toFloatBuffer(viewMatrix));
	}
	
	public final void sendTransformMatrix(Matrix4f transformMatrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_TRANSFORM_MATRIX), false, Util.toFloatBuffer(transformMatrix));
	}
	
	public final void sendProjectionMatrix(Matrix4f projectionMatrix) {
		bind();
		glUniformMatrix4(ul.get(UNIFORM_NAME_PROJECTION_MATRIX), false, Util.toFloatBuffer(projectionMatrix));
	}
	
	public final void sendLights(Light[] lights) {
		bind();
		glUniform1i(ul.get(UNIFORM_NAME_NUM_LIGHTS), lights.length);
		for(int i = 0; i < lights.length; i++) {
			
			if(lights.length > MAXIMUM_AMOUNT_OF_LIGHTS)
				throw new InvalidParameterException("Can only have " + MAXIMUM_AMOUNT_OF_LIGHTS +
						" lights in total (" + lights.length + " lights found)");
			
			glUniform1i(getStructLocation(UNIFORM_NAME_LIGHTS, i, STRUCT_VAR_NAMES_LIGHT[0]),
					(int)lights[i].getData()[0]);
			glUniform1f(getStructLocation(UNIFORM_NAME_LIGHTS, i, STRUCT_VAR_NAMES_LIGHT[1]),
					lights[i].getData()[1]);
			glUniform3f(getStructLocation(UNIFORM_NAME_LIGHTS, i, STRUCT_VAR_NAMES_LIGHT[2]),
					lights[i].getData()[2], lights[i].getData()[3], lights[i].getData()[4]);
			glUniform3f(getStructLocation(UNIFORM_NAME_LIGHTS, i, STRUCT_VAR_NAMES_LIGHT[3]),
					lights[i].getData()[5], lights[i].getData()[6], lights[i].getData()[7]);
		}
	}
	
	public final void initSamplers() {
		bind();
		glUniform1(ul.get(UNIFORM_NAME_TEXTURES), Util.toIntBuffer(new int[]{0, 1}));
	}
	
	public String getStructArrayUniformName(String structName, int index, String structVar) {
		return structName + "[" + index + "]." + structVar;
	}
	
	public int getStructLocation(String structName, int index, String structVar) {
		return ul.get(structName + "[" + index + "]." + structVar);
	}
	
	public int getOutputFormat() {
		return OUTPUT_FORMAT;
	}
	
	public static final ShaderProgram3D INSTANCE = new ShaderProgram3D();
}