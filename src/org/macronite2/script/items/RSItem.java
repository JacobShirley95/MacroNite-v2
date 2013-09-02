package org.macronite2.script.items;

import java.util.Collections;

import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSLocatable;
import org.macronite2.script.screen.RSScreenObject;

public abstract class RSItem implements RSScreenObject {
	public int id;
	public int noteID;
	public String name;
	public String[] screenActions;
	public String[] inventoryActions;
	
	public RSItem(org.macronite2.hooks.RSObject rsObj) {
		this.id = rsObj.getID();
		this.name = rsObj.getName();
		this.screenActions = rsObj.getActions();
		this.inventoryActions = rsObj.getInvOptions();
		this.noteID = rsObj.getNoteID();
	}
	
	@Override
	public void mouse(int button) {
		RSInput.mouse(getCentrePoint(), button);
	}
}
