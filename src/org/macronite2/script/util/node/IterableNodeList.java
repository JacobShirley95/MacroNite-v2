package org.macronite2.script.util.node;

import org.macronite2.hooks.Node;

public class IterableNodeList {
	private org.macronite2.hooks.IterableNodeList nl;
	private Node nextNode;
	private Node rootNode;

	public IterableNodeList(org.macronite2.hooks.IterableNodeList inl) {
		this.nl = inl;
		rootNode = inl.getCurrent();
	}

	public Node start(Node var1) {
		Node var3;
		if (null == var1) {
			var3 = this.rootNode.getNext();
		} else {
			var3 = var1;
		}

		if (var3 == this.rootNode) {
			this.nextNode = null;
			return null;
		} else {
			this.nextNode = var3.getNext();
			return var3;
		}
	}

	public Node start() {
		return start(null);
	}

	public Node next() {
		Node var2 = this.nextNode;
		if (var2 == this.rootNode) {
			this.nextNode = null;
			return null;
		} else {
			this.nextNode = var2.getNext();
			return var2;
		}
	}
}
