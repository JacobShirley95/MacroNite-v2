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
	
	public int getShowCount();
	public short[] getInds1();
	public short[] getInds2();
	public short[] getInds3();
}
