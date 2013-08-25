package org.macronite2.script;

import java.awt.Graphics;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.SoftwareRenderer;
import org.macronite2.script.components.RSBackpack;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.listeners.RSPaintListener;
import org.macronite2.script.menus.RSOptionsMenu;

public abstract class Script implements RSPaintListener {
	public static final int EXIT_CODE = -1;

	protected Client runescape;
	private SoftwareRenderer renderer;
	protected RSOptionsMenu menu;
	protected RSPlayer myPlayer;

	protected RSBackpack backpack;

	public Script(Client runescape) {
		this.runescape = runescape;
		this.renderer = (SoftwareRenderer) runescape.getCurrentRenderer();
		this.menu = new RSOptionsMenu(runescape);
		this.myPlayer = new RSPlayer(runescape.getMyPlayer());
		//this.backpack = new RSBackpack();
	}

	public abstract int run();

	@Override
	public void paint(Graphics g) {
	}

	protected final void sleep(long time) {
		try {
			Thread.currentThread().sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected boolean isLoggedIn() {
		return runescape.getMyPlayer() != null && runescape.getMyPlayer().getComposite() != null;
	}
	
	protected boolean moving() {
		return !runescape.getMyPlayer().isStationary();
	}
}
