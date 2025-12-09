package de.klassenserver7b.AVoC.A07;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {

	private T data;
	TreeNode<T> parent;
	List<TreeNode<T>> children;

	public TreeNode(T data) {
		this.data = data;
		this.children = new LinkedList<TreeNode<T>>();
	}

	public TreeNode<T> addChild(T child) {
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}

	public TreeNode<T> search(T query) {
		return searchAll(this, query);
	}

	private TreeNode<T> searchAll(TreeNode<T> node, T query) {

		for (TreeNode<T> n : node.children) {
			if (query.equals(n.data)) {
				return n;
			} else {
				searchAll(n, query);
			}
		}

		return null;
	}

	public T getData() {
		return data;
	}

	public TreeNode<T> getRoot() {

		TreeNode<T> cu = this;

		while (cu.parent != null) {
			cu = cu.parent;
		}

		return cu;
	}

}