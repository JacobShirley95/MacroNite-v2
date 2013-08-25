package org.macronite2.hooks;

public interface RSCharacter extends BlockEntity{
	public int[] getTileXArray();
	public int[] getTileYArray();
	
	public Model[] getModels();
	
	public RSInterfaceGroup getHeadInterfaces();
	
	public boolean isStationary();
	public int getIdleAnimation();
	public int getAnimation();
	public int getHeight();
}
