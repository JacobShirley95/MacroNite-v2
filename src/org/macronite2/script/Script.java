package org.macronite2.script;

import java.awt.Graphics;

import org.macronite2.hooks.SoftwareRenderer;
import org.macronite2.script.components.RSBackpack;
import org.macronite2.script.listeners.RSPaintListener;
import org.macronite2.script.menus.RSOptionsMenu;

public abstract class Script implements RSPaintListener {
	public static final int EXIT_CODE = -1;

	protected ScriptContext context;

	public Script(ScriptContext sC) {
		this.context = sC;
	}

	public abstract int run();

	@Override
	public void paint(Graphics g) {
	}

	protected final void sleep(long time) {
		context.sleep(time);
	}
	
	protected boolean isLoggedIn() {
		return context.runescape.getMyPlayer() != null && context.runescape.getMyPlayer().getComposite() != null;
	}
	
	protected boolean moving() {
		return !context.runescape.getMyPlayer().isStationary();
	}
}
