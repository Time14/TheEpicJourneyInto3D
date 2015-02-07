package com.idek.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector3f;

import com.idek.gfx.Mesh;
import com.idek.gfx.Texture;
import com.idek.gfx.Vertex;

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
				
				ArrayList<Vertex> verts = new ArrayList<>();
				ArrayList<Integer> ints = new ArrayList<>();
				
				while(s.hasNextLine()) {
					String[] data = s.nextLine().split(" ");
					
					Vector3f scale = new Vector3f(1, 1, 1);
					Vector3f offset = new Vector3f();
					
					
					
					switch(data[0]) {
					case "v":
						verts.add(new Vertex(Float.parseFloat(data[1])*scale.x+offset.x, Float.parseFloat(data[2])*scale.y+offset.y, Float.parseFloat(data[3])*scale.z+offset.z));
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
				}
				
				
				
				s.close();
				
				Vertex[] vertices = new Vertex[verts.size()];
				for(int i = 0; i < vertices.length; i++) {
					vertices[i] = verts.get(i);
				}
				
				int[] indices = new int[ints.size()];
				for(int i = 0; i < indices.length; i++) {
					indices[i] = ints.get(i);
				}
				
				return new Mesh(vertices, indices);
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