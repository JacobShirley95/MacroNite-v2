package org.macronite2.script.menus;

import java.awt.Point;
import java.util.List;

import org.macronite2.hooks.OptionNode;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSInterfaceObject;
import org.macronite2.script.screen.RSScreenObject;
import org.macronite2.script.util.node.Node;

public class RSOption extends Node implements RSInterfaceObject, RSScreenObject {

	private OptionNode option;
	private RSOptionsMenu menu;
	private int index;
	private ScriptContext context;
	
	public RSOption(ScriptContext context, OptionNode node, RSOptionsMenu menu, int index) {
		super(node);
		this.option = node;
		this.menu = menu;
		this.index = index;
		this.context = context;
	}
	
	@Override
	public Point getCentrePoint() {
		return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
	}

	@Override
	public void mouse(int button) {
		if (button == RSInput.MOUSE_RIGHT)
			throw new UnsupportedOperationException("Do not right-click here.");
		context.input.mouse(getCentrePoint(), button);
	}
	
	public void click() {
		mouse(RSInput.MOUSE_LEFT);
	}

	@Override
	public int getX() {
		return menu.getX()+5;
	}

	@Override
	public int getY() {
		return 20+menu.getY()+(index*16);
	}

	@Override
	public int getWidth() {
		return menu.getWidth()-5;
	}

	@Override
	public int getHeight() {
		return 20;
	}

	@Override
	public List<? extends RSInterfaceObject> getChildren() {
		throw new UnsupportedOperationException("This should not be used here.");
	}
	
	public String getOption() {
		return option.getOption();
	}
	
	public String getOptionName() {
		return option.getOptionName();
	}
	
	public int getAction() {
		return option.getAction();
	}
	
	public long getTarget() {
		return option.getTarget();
	}
	
	@Override
	public String toString() {
		return getOption()+" "+getOptionName();
	}

	@Override
	public Node getNext() {
		return new RSOption(context, (OptionNode)option.getNext(), menu, index+1);
	}

	@Override
	public Node getBase() {
		return new RSOption(context, (OptionNode)option.getBase(), menu, index-1);
	}
}
