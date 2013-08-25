package org.macronite2.script.util.node;

import org.macronite2.hooks.Node;
import org.macronite2.hooks.NodeArrayList;

public class NodeList {
	private Node[] list;
	private Node current;
	private Node next;

	public NodeList(NodeArrayList list) {
		this.list = list.getNodeArray();
	}

	public Node getNode(long index) {
		Node var3 = this.list[(int) (index & list.length - 1)];

		for (this.current = var3.getBase(); var3 != this.current; this.current = this.current
				.getBase()) {
			if (index == this.current.getID()) {
				Node var4 = this.current;
				this.current = this.current.getBase();
				return var4;
			}
		}

		this.current = null;
		return null;
	}
	
	public int size() {
		return this.list.length;
	}
}
