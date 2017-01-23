package com.ubs.main.util.tree;

public class Node<T extends Comparable<T>> {
	
	private Node<T> base;
	private Node<T> left;
	private Node<T> right;
	private T data;
	
	public Node(T data) {
		this.data = data;
	}
	
	public boolean hasLeft() {
		return left != null;
	}
	
	public boolean hasRight() {
		return right != null;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getBase() {
		return base;
	}

	public void setBase(Node<T> base) {
		this.base = base;
	}
	
}
