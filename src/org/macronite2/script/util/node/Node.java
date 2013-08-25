package org.macronite2.script.util.node;

public class Node {
	private org.macronite2.hooks.Node node;
	public Node(org.macronite2.hooks.Node node) {
		this.node = node;
	}
	
	public Node getNext() {
		return new Node(node.getNext());
	}
	
	public Node getBase() {
		return new Node(node.getBase());
	}
}
