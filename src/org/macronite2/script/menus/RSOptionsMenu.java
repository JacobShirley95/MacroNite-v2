package org.macronite2.script.menus;

import java.awt.Point;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.IterableNodeList;
import org.macronite2.hooks.OptionNode;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSInterfaceObject;
import org.macronite2.script.util.node.Node;

public class RSOptionsMenu implements RSInterfaceObject{
	private Client runescape;
	public RSOptionsMenu(Client runescape) {
		this.runescape = runescape;
	}
	
	public int getX() {
		return runescape.getOptionsBoxX();
	}
	
	public int getY() {
		return runescape.getOptionsBoxY();
	}
	
	public int getWidth() {
		return runescape.getOptionsBoxWidth();
	}
	
	public int getHeight() {
		return runescape.getOptionsBoxHeight();
	}
	
	public int getOffset() {
		return runescape.getOptionsBoxOffset();
	}
	
	public int getOptionsCount() {
		return runescape.getOptionsCount();
	}
	
	public boolean optionsOpen() {
		return runescape.isOptionsBoxOpen();
	}

	private IterableNodeList getOptionsList() {
		return runescape.getOptions();
	}
	
	public RSOption[] getOptions() {
		List<RSOption> ops = getChildren();
		Collections.reverse(ops);
		RSOption[] ops2 = new RSOption[ops.size()];
		return ops.toArray(ops2);
	}
	
	public boolean openOptions(int x, int y) {
		RSInput.mouse(x, y, RSInput.MOUSE_RIGHT);
		return runescape.isOptionsBoxOpen();
	}
	
	public boolean clickOption(String option) {
		RSOption[] options = getOptions();
		for (RSOption option2 : options)
			if (option2.getOption().equals(option)) {
				option2.mouse(RSInput.MOUSE_LEFT);
				return true;
			}
		return false;
	}

	@Override
	public List<RSOption> getChildren() {
		List<RSOption> options = new LinkedList<>();
		IterableNodeList inl = getOptionsList();
		Iterator<?> it = inl.iterator();
		int c = getOptionsCount()-1;
		while (it.hasNext()) {
			options.add(new RSOption((OptionNode)it.next(), this, c--));
		}
		return options;
	}
}
