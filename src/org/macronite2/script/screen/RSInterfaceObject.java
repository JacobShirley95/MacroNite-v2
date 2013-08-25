package org.macronite2.script.screen;

import java.util.List;

public interface RSInterfaceObject {
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	
	public List<? extends RSInterfaceObject> getChildren();
}
