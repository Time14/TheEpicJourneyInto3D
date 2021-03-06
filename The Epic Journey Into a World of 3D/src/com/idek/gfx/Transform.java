package com.idek.gfx;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Transform {
	
	public Vector3f position;
	public Vector3f rotation;
	public Vector3f scale;
	
	public Transform() {
		this(new Vector3f());
	}
	
	public Transform(float x, float y, float z) {
		this(new Vector3f(x, y, z));	
	}
	
	public Transform(Vector3f position) {
		this(position, new Vector3f());	
	}
	
	public Transform(Vector3f position, Vector3f rotation) {
		this(position, rotation, new Vector3f(1, 1, 1));
	}
	
	public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Matrix4f getMatrix() {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		matrix.translate(position);
		matrix.rotate((float)Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
		matrix.rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
		matrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0));
		matrix.scale(scale);
		
		return matrix;
	}
	
	public Transform setRX(float rx) {
		rotation.x = rx % 360;
		if(rotation.x < 0)
			rotation.x += 360;
		return this;
	}
	
	public Transform setRY(float ry) {
		rotation.y = ry % 360;
		if(rotation.y < 0)
			rotation.y += 360;
		return this;
	}
	
	public Transform setRZ(float rz) {
		rotation.z = rz % 360;
		if(rotation.z < 0)
			rotation.z += 360;
		return this;
	}
	
	public Transform setRotation(float x, float y, float z) {
		return setRotation(new Vector3f(x, y, z));
	}
	
	public Transform setRotation(Vector3f rotation) {
		setRX(rotation.x);
		setRY(rotation.y);
		return setRZ(rotation.z);
	}
	
	public Transform rotateX(float rotation) {
		this.rotation.x += rotation;
		this.rotation.x %= 360;
		if(this.rotation.x < 0)
			this.rotation.x += 360;
		return this;
	}
	
	public Transform rotateY(float rotation) {
		this.rotation.y += rotation;
		this.rotation.y %= 360;
		if(this.rotation.y < 0)
			this.rotation.y += 360;
		return this;
	}
	
	public Transform rotateZ(float rotation) {
		this.rotation.z += rotation;
		this.rotation.z %= 360;
		if(this.rotation.z < 0)
			this.rotation.z += 360;
		return this;
	}
	
	public Transform rotate(float x, float y, float z) {
		return rotate(new Vector3f(x, y, z));
	}
	
	public Transform rotate(Vector3f rotation) {
		Vector3f.add(this.rotation, rotation, this.rotation);
		this.rotation.x %= 360;
		if(this.rotation.x < 0)
			this.rotation.x += 360;
		this.rotation.y %= 360;
		if(this.rotation.y < 0)
			this.rotation.y += 360;
		this.rotation.z %= 360;
		if(this.rotation.z < 0)
			this.rotation.z += 360;
		return this;
	}
	
	public Transform setScale(float scale) {
		setScaleX(scale);
		setScaleY(scale);
		return setScaleZ(scale);
	}
	
	public Transform setScaleX(float scale) {
		this.scale.x = scale;
		return this;
	}
	
	public Transform setScaleY(float scale) {
		this.scale.y = scale;
		return this;
	}
	
	public Transform setScaleZ(float scale) {
		this.scale.z = scale;
		return this;
	}
	
	public Transform setScale(float x, float y, float z) {
		return setScale(new Vector3f(x, y, z));
	}
	
	public Transform setScale(Vector3f scale) {
		this.scale.x = scale.x;
		this.scale.y = scale.y;
		this.scale.z = scale.z;
		return this;
	}
	
	public Transform addScale(float scalar) {
		addScaleX(scalar);
		addScaleY(scalar);
		return addScaleZ(scalar);
	}
	
	public Transform addScaleX(float scale) {
		this.scale.x += scale;
		return this;
	}
	
	public Transform addScaleY(float scale) {
		this.scale.y += scale;
		return this;
	}
	
	public Transform addScaleZ(float scale) {
		this.scale.z += scale;
		return this;
	}
	
	public Transform addScale(float x, float y, float z) {
		return addScale(new Vector3f(x, y, z));
	}
	
	public Transform addScale(Vector3f scale) {
		Vector3f.add(this.scale, scale, this.scale);
		return this;
	}
	
	public Transform scale(float scalar) {
		scale.scale(scalar);
		return this;
	}
	
	public Transform scaleX(float scale) {
		this.scale.x *= scale;
		return this;
	}
	
	public Transform scaleY(float scale) {
		this.scale.y *= scale;
		return this;
	}
	
	public Transform scaleZ(float scale) {
		this.scale.z *= scale;
		return this;
	}
	
	public Transform scale(float x, float y, float z) {
		return scale(new Vector3f(x, y, z));
	}
	
	public Transform scale(Vector3f scale) {
		this.scale.x *= scale.x;
		this.scale.y *= scale.y;
		this.scale.z *= scale.z;
		return this;
	}
	
	public Transform setX(float x) {
		position.x = x;
		return this;
	}
	
	public Transform setY(float y) {
		position.y = y;
		return this;
	}
	
	public Transform setZ(float z) {
		position.z = z;
		return this;
	}
	
	public Transform setPosition(float x, float y, float z) {
		return setPosition(new Vector3f(x, y, z));
	}
	
	public Transform setPosition(Vector3f position) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.position.z = position.z;
		return this;
	}
	
	public Transform translateX(float x) {
		position.x += x;
		return this;
	}
	
	public Transform translateY(float y) {
		position.y += y;
		return this;
	}

	public Transform translateZ(float z) {
		position.z += z;
		return this;
	}
	
	public Transform translate(float x, float y, float z) {
		return translate(new Vector3f(x, y, z));
	}
	
	public Transform translate(Vector3f offset) {
		position.translate(offset.x, offset.y, offset.z);
		return this;
	}
}