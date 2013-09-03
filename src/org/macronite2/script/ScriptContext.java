package org.macronite2.script;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.MapBase;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.location.RSLocator;
import org.macronite2.script.location.RSUniLocator;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.math.RSMath;
import org.macronite2.script.screen.RSInput;

public class ScriptContext {
	/*
	 * TODO Add login name, add locators, add toolkits, 
	 * and other context-related objects. 
	 */
	public Client runescape;
	
	public RSLocator locator;
	public String accountName;
	public RSCompass compass;
	public RSInput input;
	public RSMath math;
	
	public ScriptContext(Client runescape, String accountName) {
		this.runescape = runescape;
		this.accountName = accountName;
		this.locator = new RSUniLocator(this);
		this.compass = new RSCompass(this);
		this.input = new RSInput(this);
		this.math = new RSMath(this);
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
