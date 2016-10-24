package com.coursera.algorithms.partone.week01.impl;

import com.coursera.algorithms.partone.week01.UnionFind;

/**
 * WeightedQuickUnion
 * It's an improvement over QuickUnion, which takes into consideration the depth of the sub tree as well.
 * Before adding an Object into a component, see that which subtree is smaller and add there.
 * 
 * This improvement prevents creation of tall trees.
 * Keep track of size of tree(number of objects in tree)
 * Balance by linking Root of smaller tree with the Root of larger tree.
 * Avoid putting large tree lower, if large tree is added in lower position, then it will further increase the height of the final tree.
 * @author shabhushan
 *
 */
public class WeightedQuickUnionUF implements UnionFind {

	/**
	 * Interpretation of this array is same as in the QuickUnion algorithm
	 */
	private int[] id;
	
	/**
	 * Maintain size of the component
	 * Counts number of objects in the tree rooted at i
	 */
	private int[] size;
	
	public WeightedQuickUnionUF(int N) {
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
	 * 
	 * @param p
	 * 		Object p, for which the root element needs to be found
	 * @return
	 * 		Root element associated with Object p
	 */
	private int root(final int p) {
		int element = p;
		
		while(id[element] != element) {
			element = id[element];
		}
		
		return element;
	}
}
