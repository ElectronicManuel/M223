package com.ubs.test.util;

import org.junit.Test;

import com.ubs.main.util.tree.Tree;

public class TreeTest {
	
	@Test
	public void testAddChars() {
		System.out.println("#### Character testing ####");
		Tree<Character> tree = new Tree<Character>();
		tree.add('d');
		tree.add('b');
		tree.add('f');
		tree.add('a');
		tree.add('c');
		tree.add('e');
		tree.print();
	}
	
	@Test
	public void testAddStrings() {
		System.out.println("#### String testing ####");
		Tree<String> tree = new Tree<String>();
		tree.add("Minhkhanh");
		tree.add("Patric");
		tree.add("Emanuel");
		tree.add("Name1");
		tree.add("Name2");
		tree.add("Name3");
		tree.add("Name4");
		tree.print();
	}

}
