package org.macronite2.script.entities;

import org.macronite2.hooks.NPC;
import org.macronite2.script.ScriptContext;

public class RSNPC extends RSCharacter {
	private NPC npc;
	
	public RSNPC(ScriptContext context, NPC npc) {
		super(context, npc);
		this.npc = npc;
	}

	@Override
	public String getName() {
		if (!isLoaded())
			return null;
		return npc.getNPCComposite().getName();
	}

	@Override
	public int getCombatLevel() {
		if (!isLoaded())
			return -1;
		return npc.getNPCComposite().getCombatLevel();
	}

	public int getID() {
		if (!isLoaded())
			return -1;
		return npc.getNPCComposite().getModelID();
	}
	
	public String[] getActions() {
		if (!isLoaded())
			return null;
		return npc.getNPCComposite().getActions();
	}
	
	public boolean isLoaded() {
		return npc.getNPCComposite() != null;
	}
	
	public boolean exists() {
		return npc != null;
	}
	
	public boolean isOnScreen() {
		return getCentrePoint().x > 0;
	}
}
