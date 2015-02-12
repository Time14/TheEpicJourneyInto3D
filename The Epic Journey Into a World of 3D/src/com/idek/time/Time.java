package com.idek.time;

public final class Time {
	
	private static long previousTime = System.nanoTime();
	
	private static int fps = 0;
	
	private static int fpsCount = 0;
	
	private static long timeSinceLastSecond = 0;
	
	public static final void update() {
		fpsCount++;
		
		long currentTime = System.nanoTime();
		
		timeSinceLastSecond += currentTime - previousTime;
		
		if(timeSinceLastSecond > 1000000000) {
			fps = fpsCount;
			fpsCount = 0;
			timeSinceLastSecond = 0;
		}
		
		previousTime = currentTime;
	}
	
	public static final int getFPS() {
		return fps;
	}
}