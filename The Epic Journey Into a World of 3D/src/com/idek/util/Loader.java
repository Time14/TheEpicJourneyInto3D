package com.idek.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.idek.gfx.Mesh;
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
					
					if(data[0].equalsIgnoreCase("v")) {
						verts.add(new Vertex(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
					} else if(data[0].equalsIgnoreCase("f")) {
						for(int i = 1; i < data.length; i++) {
							ints.add(Integer.parseInt(data[i]) - 1);
						}
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
	
}