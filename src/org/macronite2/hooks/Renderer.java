package org.macronite2.hooks;

public interface Renderer {
	public int getRendererID();
	public void toScreen(float x, float y, float z, float[] out);
}
