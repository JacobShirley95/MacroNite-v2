package org.macronite2.script.components;

import org.macronite2.script.screen.RSScreenObject;

public abstract class RSSlot implements RSScreenObject{
	public int index;
	
	public RSSlot(int index) {
		this.index = index;
	}

	public abstract boolean isSelected();
	public abstract String[] getActions();
}
