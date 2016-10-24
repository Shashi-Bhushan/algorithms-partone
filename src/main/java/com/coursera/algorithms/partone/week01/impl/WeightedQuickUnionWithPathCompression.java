package com.coursera.algorithms.partone.week01.impl;

import com.coursera.algorithms.partone.week01.UnionFind;

/**
 * WeightedQuickUnionWithPath Compression
 * 
 * Compares the path each time Root gets called.
 * 
 * Most Efficiently, while finding Root, each of the parent element can be changed to direct child of Root.
 * In order to do this, we first need to find the root, and then assign each parent a new value.
 * 
 * Instead, a good approach is HALVING.
 * Each parent i touch, go two level up instead of one.
 * @author shabhushan
 *
 */
public class WeightedQuickUnionWithPathCompression implements UnionFind {

	/**
	 * Interpretation of this array is same as in the QuickUnion algorithm
	 */
	private int[] id;
	
	/**
	 * Maintain size of the component
	 * Counts number of objects in the tree rooted at i
	 */
	private int[] size;
	
	public WeightedQuickUnionWithPathCompression(int N) {
		id = new int[N];
		size = new int[N];
		
		
		for(int index = 0; index < id.length; index++) {
			id[index] = index;
			size[index] = index;
		}
	}

	/**
	 * @see UnionFind#union(int, int)
	 * 
	 * Algorithm is
	 * - Find Root of p and q
	 * - Find which one of those is Smaller Tree
	 * - Add Smaller Tree's Root to Bigger Tree's Root
	 * - Increment the Size of Bigger Tree by Size of Smaller Tree
	 */
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		
		if(size[pRoot] < size[qRoot]) {
			// point p's Root to q's Root
			id[pRoot] = qRoot;
			// change size of bigger Tree Now
			size[qRoot] = size[qRoot] + size[pRoot];
		} else {
			id[qRoot] = pRoot;
			// Change size of bigger Tree Now
			size[pRoot] = size[pRoot] + size[qRoot];
		}
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	/**
	 * Finds the root associated with the Object p
	 * Also, make each Object point to it's grandparent instead of Parent in each Go.
	 * 
	 * @param p
	 * 		Object p, for which the root element needs to be found
	 * @return
	 * 		Root element associated with Object p
	 */
	private int root(final int p) {
		int element = p;

		while(id[element] != element) {
			// Halving the Tree Depth Each time Root is Called
			id[element] = id[id[element]];
			element = id[element];
		}
		
		return element;
	}
}
