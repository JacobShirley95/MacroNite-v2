package org.macronite2.script.util.node;

public abstract class Node {
	private org.macronite2.hooks.Node node;
	public Node(org.macronite2.hooks.Node node) {
		this.node = node;
	}
	
	public abstract Node getNext();
	public abstract Node getBase();
}
