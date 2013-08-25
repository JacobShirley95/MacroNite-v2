package org.macronite2.hooks;

public interface RSInterface {
	public int getID();
	public int getParentID();
	
	public String getTitle();
	public String[] getActions();
	
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public int getIndex();
	
	public int getType();
	
	public RSInterface[] getChildren();
	
	public int getItemID();
	
	public int getScrollX();
	public int getScrollY();
	public boolean isHidden();
	
	public int getRotation();
	public int getOutline();
	public int getItemAmount();
	public int getImageID();
	
	public int getHoverColour();
	public int getValidIndex();
	
}
