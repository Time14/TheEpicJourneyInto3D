package com.idek.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.util.vector.Matrix4f;

import com.idek.gfx.light.Light;
import com.idek.gfx.vertex.Vertex3D;

public final class Util {
	
	public static final FloatBuffer toFloatBuffer(float[] data) {
		FloatBuffer buffer = createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public static final FloatBuffer toFloatBuffer(Vertex3D[] data) {
		FloatBuffer buffer = createFloatBuffer(data.length * Vertex3D.LENGTH);
		for(Vertex3D v : data) {
			buffer.put(v.getData());
		}
		buffer.flip();
		return buffer;
	}
	
	public static final FloatBuffer toFloatBuffer(Matrix4f matrix) {
		FloatBuffer buffer = createFloatBuffer(16);
		matrix.store(buffer);
		buffer.flip();
		return buffer;
	}
	
	public static final FloatBuffer toFloatBuffer(Light[] lights) {
		FloatBuffer buffer = createFloatBuffer(lights.length * Light.SIZE);
		for(Light l : lights) {
			buffer.put(l.getData());
		}
		buffer.flip();
		return buffer;
	}
	
	public static final IntBuffer toIntBuffer(int[] data) {
		IntBuffer buffer = createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public static final FloatBuffer createFloatBuffer(int size) {
		return createByteBuffer(size << 2).asFloatBuffer();
	}
	
	public static final IntBuffer createIntBuffer(int size) {
		return createByteBuffer(size << 2).asIntBuffer();
	}
	
	public static final ByteBuffer createByteBuffer(int size) {
		return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
	}
}