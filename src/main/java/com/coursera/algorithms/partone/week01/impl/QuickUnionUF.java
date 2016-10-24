package com.coursera.algorithms.partone.week01.impl;

import com.coursera.algorithms.partone.week01.UnionFind;

/**
 * Quick Union
 * This is the second implementation for UnionFind Problem of Dynamic Connectivity.
 * It is a lazy approach to the problem, which addresses the previous problem that many entries needs to be changed in array for union operation.
 * For connected and union operations, p and q both are index in the array.
 * Because we presume that the index is our object and it's value is the position.
 * Also, with each item is a Root element associated with it.
 * 
 * Problem:
 * - Trees can get Tall.(Need to Address this now)
 * - connected operation is too expensive, if tree is tall.
 * 
 * @author shabhushan
 *
 */
public class QuickUnionUF implements UnionFind{

	/**
	 * Interpretation of Data Structure is that
	 * - Each index number of the array will act as the Object.
	 * - Unlike QuickFind, For QuickUnion the value at each position is parent of the Object.
	 * - id[i] is parent of i
	 * 
	 * We presume that the index is our Object and it's value is the position of the parent.
	 * Initially, each object will act as it's parent as well. So, each will have it's own separate component.
	 * After a Union, the value of one object will change to position of another object.
	 * Thus, signifying that second object is the parent of first object.
	 * 
	 * To find root Object, go to id[id[id[...id[i]...]]]
	 */
	private int[] id;
	
	QuickUnionUF(int N) {
		id = new int[N];
		
		for(int index = 0; index < id.length; index++) {
			id[index] = index;
		}
	}

	/**
	 * @see UnionFind#union(int, int)
	 * 
	 * To merge the components containing p and q, set the id of p's root to id of q's root.
	 * Name is QuickUnion, because union operation requires only one value to be changed.
	 */
	public void union(int p, int q) {
		int pRoot = root(p);
		
		int qRoot = root(q);
		
		id[pRoot] = qRoot;
	}

	/**
	 * @see UnionFind#connected(int, int)
	 * 
	 * For checking if two object p and q are within the same component, we simply need to check if the root element of both p and q is equal.
	 */
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
