package org.macronite2.script.components;

public interface RSItemArea {
	
	public int itemCount();
	
	public RSItemSlot findItem(String name);
	
	public RSItemSlot findItem(int id);
	
	public RSItemSlot getItemSlot(int slot);
}
