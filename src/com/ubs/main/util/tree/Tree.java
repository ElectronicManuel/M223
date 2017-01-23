package com.ubs.main.util.tree;

import java.util.HashMap;
import java.util.Map;

public class Tree<T extends Comparable<T>> {
	
	/* left - kleiner | right - grösser */
	
	private Node<T> root = null;
	
	private Map<Integer, String> printStrings;
	
	private void link(Node<T> base, Node<T> left, Node<T> right) {
		if(left != null) {
			left.setBase(base);
			base.setLeft(left);
		}
		if(right != null) {
			right.setBase(base);
			base.setRight(right);
		}
	}
	
	public void add(T val) {
		// Erstes Element
		if(root == null) {
			root = new Node<T>(val);
		}
		else {
			Node<T> current = root;
			while(true) {
				if(val.compareTo(current.getData()) >= 0) {
					// Rechts
					if(current.hasRight()) {
						current = current.getRight();
					}
					else {
						link(current, null, new Node<T>(val));
						break;
					}
				}
				if(val.compareTo(current.getData()) < 0) {
					// Links
					if(current.hasLeft()) {
						current = current.getLeft();
					}
					else {
						link(current, new Node<T>(val), null);
						break;
					}
				}
			}
		}
	}
	
	public boolean remove(int val) {
		
		return true;
	}
	
	private void printNode(Node<T> toPrint, int depth) {
		if(!printStrings.containsKey(depth)) {
			printStrings.put(depth, "");
		}
		if(toPrint.hasLeft()) {
			String currentStep = printStrings.get(depth) + " L:" + toPrint.getLeft().getData().toString() + " ";
			printStrings.put(depth, currentStep);
			printNode(toPrint.getLeft(), depth+1);
		}
		else {
			String currentStep = printStrings.get(depth) + " E ";
			printStrings.put(depth, currentStep);
		}
		if(toPrint.hasRight()) {
			String currentStep = printStrings.get(depth) + " R:" + toPrint.getRight().getData().toString() + " ";
			printStrings.put(depth, currentStep);
			printNode(toPrint.getRight(), depth+1);
		}
		else {
			String currentStep = printStrings.get(depth) + " E ";
			printStrings.put(depth, currentStep);
		}
	}
	
	public void print() {
		printStrings = new HashMap<Integer, String>();
		if(root != null) {
			printStrings.put(0, " " + root.getData() + " ");
			printNode(root, 1);
			int count = 0;
			while(printStrings.containsKey(count)) {
				System.out.println(printStrings.get(count));
				count++;
			}
		}
		else {
			System.out.println("Leer");
		}
	}
	
}
