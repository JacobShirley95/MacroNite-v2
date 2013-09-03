package org.macronite2.script.items;

import org.macronite2.script.ScriptContext;
import org.macronite2.script.screen.RSScreenObject;

public abstract class RSItem implements RSScreenObject {
	public int id;
	public int noteID;
	public String name;
	public String[] screenActions;
	public String[] inventoryActions;
	private ScriptContext context;
	
	public RSItem(ScriptContext context, org.macronite2.hooks.RSObject rsObj) {
		this.id = rsObj.getID();
		this.name = rsObj.getName();
		this.screenActions = rsObj.getActions();
		this.inventoryActions = rsObj.getInvOptions();
		this.noteID = rsObj.getNoteID();
		this.context = context;
	}
	
	@Override
	public void mouse(int button) {
		context.input.mouse(getCentrePoint(), button);
	}
}
