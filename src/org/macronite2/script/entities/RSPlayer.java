package org.macronite2.script.entities;

import org.macronite2.hooks.Player;
import org.macronite2.script.ScriptContext;

public class RSPlayer extends RSCharacter{

	private Player player;

	public RSPlayer(ScriptContext context, Player player) {
		super(context, player);
		this.player = player;
	}

	@Override
	public String getName() {
		return player.getName();
	}

	@Override
	public int getCombatLevel() {
		return player.getCombatLevel();
	}
	
	public int[] getEquipmentIDs() {
		return player.getComposite().getEquipment();
	}
	
	public long getModelID() {
		return player.getComposite().getModelID();
	}

	public boolean isAnimating() {
		return getAnimation() > -1;
	}
}
