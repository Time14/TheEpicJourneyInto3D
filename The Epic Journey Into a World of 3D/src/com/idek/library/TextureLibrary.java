package com.idek.library;

import com.idek.gfx.Texture;

public final class TextureLibrary {
	
	
	public static final Texture DEFAULT_TEXTURE = new Texture("res/texture/UV_mapper.png");
	public static final Texture DEFAULT_NORMAL_MAP = new Texture().genTexture(new int[]{0x00888800}, false, 1, 1);
}