package com.idek.gfx.entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.idek.gfx.RenderManager;
import com.idek.gfx.RenderableTexture;
import com.idek.gfx.Transform;
import com.idek.gfx.shader.ShaderProgram;
import com.idek.gfx.shader.ShaderProgram3D;
import com.idek.library.MeshLibrary;
import com.idek.util.Loader;

public class EntityManager {
	
	private RenderManager rm;
	
	private HashMap<String, Entity> entities;
	private HashMap<String, Group> groups;
	
	public EntityManager(RenderManager rm) {
		entities = new HashMap<>();
		groups = new HashMap<>();
		this.rm = rm;
	}
	
	public EntityManager createEntities() {
		
		addGroup("Funky Toruses");
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				addToGroup("Funky Toruses", new Entity(rm, MeshLibrary.FUNKY_TORUS, new Transform(i*2, j*2, 0)));
			}
		}
		
		return this;
	}
	
	public void update() {
		for(Entity e : entities.values()) {
			e.update();
		}
		
		for(Group g : groups.values()) {
			g.update();
			for(Entity e : g.entities)
				e.rotate(1, 1, 1);
		}
	}
	
	public void draw() {
		for(Entity e : entities.values()) {
			e.draw();
		}
		
		for(Group g : groups.values()) {
			g.draw();
		}
	}
	
	public EntityManager addEntity(String name, Entity entity) {
		entities.put(name, entity);
		return this;
	}
	
	public Entity getEntity(String name) {
		return entities.get(name);
	}
	
	public EntityManager addGroup(String... names) {
		for(String name : names) {
			groups.put(name, new Group(name));
		}
		
		return this;
	}
	
	public ArrayList<Entity> getEntitiesFromGroup(String name) {
		return groups.get(name).entities;
	}
	
	public EntityManager addToGroup(String group, Entity... entities) {
		for(Entity e : entities)
			groups.get(group).entities.add(e);
		return this;
	}
	
	public void cleanUp() {
		for(Entity e : entities.values())
			e.cleanUp();
		for(Group g : groups.values())
			g.cleanUp();
		groups.clear();
	}
	
	private class Group {
		final String NAME;
		
		ArrayList<Entity> entities;
		boolean active = true;
		boolean visible = true;
		
		Group(String name) {
			this.NAME = name;
			entities = new ArrayList<>();
		}
		
		void update() {
			if(active) {
				for(Entity e : entities)
					e.update();
			}
		}
		
		void draw() {
			if(visible) {
				for(Entity e : entities)
					e.draw();
			}
		}
		
		void cleanUp() {
			for(Entity e : entities)
				e.cleanUp();
		}
	}
}