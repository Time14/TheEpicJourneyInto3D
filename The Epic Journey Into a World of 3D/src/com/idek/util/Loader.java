package com.idek.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.idek.gfx.Mesh;
import com.idek.gfx.Texture;
import com.idek.gfx.vertex.Vertex3D;

public final class Loader {
	
	public static final String loadSource(String path) {
		try {
			File file = new File(path);
			StringBuilder sb = new StringBuilder();
			
			if(file.exists()) {
				
				Scanner s = new Scanner(file);
			
				while(s.hasNextLine()) {
					
					sb.append(s.nextLine() + "\n");
				}
				
				s.close();
				
				return sb.toString();
			}
			
			System.err.println("\"404 Error\", no file found at \"" + path + "\"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "How the hell did you even get here?!";
	}
	
	public static final Mesh loadOBJMesh(String path) {
		try {
			File file = new File(path);
			if(file.exists()) {
				
				Scanner s = new Scanner(file);
				
				ArrayList<Vector3f> positions = new ArrayList<>();
				ArrayList<Vector2f> texCoords = new ArrayList<>();
				ArrayList<Vector3f> normals = new ArrayList<>();
				
				ArrayList<Vertex3D> vertices = new ArrayList<>();
				
				while(s.hasNextLine()) {
					
					String[] data = s.nextLine().split(" ");
					switch(data[0]) {
					case "v":
						positions.add(new Vector3f(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
						break;
					case "vt":
						texCoords.add(new Vector2f(Float.parseFloat(data[1]), Float.parseFloat(data[2])));
						break;
					case "vn":
						normals.add(new Vector3f(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
						break;
					}
					
					if(data[0].equalsIgnoreCase("f")) {
						for(int i = 0; i < 3; i++) {
							String[] indices = data[i + 1].split("/");
							vertices.add(
									new Vertex3D(positions.get(Integer.parseInt(indices[0]) - 1), 
									texCoords.get(Integer.parseInt(indices[1]) - 1), 
									normals.get(Integer.parseInt(indices[2]) - 1)));
						}
					}
				}
				
				s.close();
				
				Vertex3D[] out = new Vertex3D[vertices.size()];
				
				return new Mesh(vertices.toArray(out));
			}
			
			System.err.println("\"404 Error\", no file found at \"" + path + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static final BufferedImage loadImage(String path) throws IOException {
		return ImageIO.read(new File(path));
	}
}


/*
String[] data = s.nextLine().split(" ");

Vector3f scale = new Vector3f(1, 1, 1);
Vector3f offset = new Vector3f();

//v, vn, vt, f

switch(data[0]) {
case "v":
	verts.add(new Vertex3D(Float.parseFloat(data[1])*scale.x+offset.x, Float.parseFloat(data[2])*scale.y+offset.y, Float.parseFloat(data[3])*scale.z+offset.z));
	break;
case "f":
	for(int i = 1; i < data.length; i++) {
		ints.add(Integer.parseInt(data[i]) - 1);
	}
	break;
case "#Auto":
	switch(data[1]) {
	case "scale":
		scale = new Vector3f(Float.parseFloat(data[2].substring(2)), Float.parseFloat(data[3].substring(2)), Float.parseFloat(data[4].substring(2)));
		break;
	case "offset":
		offset = new Vector3f(Float.parseFloat(data[2].substring(2)), Float.parseFloat(data[3].substring(2)), Float.parseFloat(data[4].substring(2)));
		break;
	}
	break;
}
*/