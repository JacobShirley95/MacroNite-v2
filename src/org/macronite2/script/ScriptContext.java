package org.macronite2.script;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.MapBase;
import org.macronite2.script.components.RSBackpack;
import org.macronite2.script.components.RSBank;
import org.macronite2.script.components.abilitybar.RSAbilityBar;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.location.RSLocator;
import org.macronite2.script.location.RSUniLocator;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.math.RSMath;
import org.macronite2.script.menus.RSOptionsMenu;
import org.macronite2.script.screen.RSInput;

public class ScriptContext {
	/*
	 * TODO Add login name, add locators, add toolkits, 
	 * and other context-related objects. 
	 */
	public Client runescape;
	
	public String accountName;
	
	public RSLocator locator;
	public RSInput input;
	public RSMath math;
	
	public RSCompass compass;
	public RSBackpack backpack;
	public RSAbilityBar abilityBar;
	public RSBank bank;
	public RSOptionsMenu optionsMenu;
	
	public ScriptContext(Client runescape, String accountName) {
		this.runescape = runescape;
		this.accountName = accountName;
		
		this.locator = new RSUniLocator(this);
		this.compass = new RSCompass(this);
		this.input = new RSInput(this);
		this.math = new RSMath(this);
		this.backpack = new RSBackpack(this);
		this.abilityBar = new RSAbilityBar(this);
		this.bank = new RSBank(this);
		this.optionsMenu = new RSOptionsMenu(this);
	}
	
	public RSPlayer getMyPlayer() {
		return new RSPlayer(this, runescape.getMyPlayer());
	}
	
	public int getPlane() {
		return getMyPlayer().getPlane();
	}
	
	public MapBase getMapBase() {
		return runescape.getMapBase();
	}
	
	public boolean isLoggedIn() {
		return runescape.getMyPlayer() != null && runescape.getMyPlayer().getComposite() != null;
	}
	
	public boolean moving() {
		return !runescape.getMyPlayer().isStationary();
	}
	
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {}
	}
	
	public void login() {
		if (isLoggedIn())
			return;
		
		//TODO Create this method.
	}
}
