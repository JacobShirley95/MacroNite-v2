package org.macronite2.script.menus;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.macronite2.hooks.IterableNodeList;
import org.macronite2.hooks.OptionNode;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSInterfaceObject;

public class RSOptionsMenu implements RSInterfaceObject{
	private ScriptContext context;
	public RSOptionsMenu(ScriptContext context) {
		this.context = context;
	}
	
	@Override
	public int getX() {
		return context.runescape.getOptionsBoxX();
	}
	
	@Override
	public int getY() {
		return context.runescape.getOptionsBoxY();
	}
	
	@Override
	public int getWidth() {
		return context.runescape.getOptionsBoxWidth();
	}
	
	@Override
	public int getHeight() {
		return context.runescape.getOptionsBoxHeight();
	}
	
	public int getOffset() {
		return context.runescape.getOptionsBoxOffset();
	}
	
	public int getOptionsCount() {
		return context.runescape.getOptionsCount();
	}
	
	public boolean optionsOpen() {
		return context.runescape.isOptionsBoxOpen();
	}

	private IterableNodeList getOptionsList() {
		return context.runescape.getOptions();
	}
	
	public RSOption[] getOptions() {
		List<RSOption> ops = getChildren();
		Collections.reverse(ops);
		RSOption[] ops2 = new RSOption[ops.size()];
		return ops.toArray(ops2);
	}
	
	public boolean openOptions(int x, int y) {
		context.input.mouse(x, y, RSInput.MOUSE_RIGHT);
		return context.runescape.isOptionsBoxOpen();
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
			options.add(new RSOption(context, (OptionNode)it.next(), this, c--));
		}
		return options;
	}
}
