package org.macronite2.script.entities;

import org.macronite2.hooks.NPC;
import org.macronite2.hooks.NPCNode;
import org.macronite2.script.RuneScape;
import org.macronite2.script.util.node.NodeList;

public class RSNPC extends RSCharacter {
	private NPC npc;
	
	public RSNPC(NPC npc) {
		super(npc);
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
		return toScreen().x > 0;
	}
	
	public static final RSNPC findNPC(String name) {
		int npcsCount = RuneScape.getClient().getAvailableNPCS();
		int[] npcIDs = RuneScape.getClient().getNPCIndices();
		NodeList npcs = new NodeList(RuneScape.getClient().getNPCS());
		for (int i = 0; i < npcsCount; i++) {
			NPC npc = (NPC) ((NPCNode) npcs.getNode(npcIDs[i])).getObject();
			if (npc.getName().contains(name)) {
				return new RSNPC(npc);
			}
		}
		return null;
	}
}
