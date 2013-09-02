package org.macronite2.hooks;

public interface SModel extends Model{
	public int getVertexCount();
	public int[] getVerticesX();
	public int[] getVerticesY();
	public int[] getVerticesZ();
	
	public float getRenderX();
	public float getRenderY();
	
	public float getScaleX();
	public float getScaleY();
	public RenderData getRenderData();
}
