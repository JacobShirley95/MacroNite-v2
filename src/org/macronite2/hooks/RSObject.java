package org.macronite2.hooks;

public interface RSObject {
	public String getName();
	public String[] getActions();
	public String[] getInvOptions();
	public boolean isScenery();
	public boolean isInBank();
	public int getID();
	public int getTextColour();
	public int getNoteID();
	public int getItemAmount();
}
