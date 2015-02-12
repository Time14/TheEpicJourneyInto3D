package com.idek.gfx.light;

import java.util.ArrayList;
import java.util.HashMap;

public class LightManager {
	
	private ArrayList<Light> lights;
	
	private HashMap<Light, float[]> shadowMaps;
	
	public LightManager() {
		lights = new ArrayList<>();
	}
	
	public LightManager addLight(Light light) {
		lights.add(light);
		addShadowMap();
		return this;
	}
	
	private LightManager addShadowMap() {
		
	}
	
}