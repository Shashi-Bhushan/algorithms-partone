package com.coursera.algorithms.partone.week01;

/**
 * Union Find
 * Implementation goal is to find an efficient Data Structure for Union-Find Problem.
 * Union Find means to be able to answer whether two objects are within the same component or not
 * 
 * Problems:
 * - Number of objects N can be huge
 * - Number of operations M can be huge
 * - Find queries and Union commands can be inter-mixed.
 * 
 * @author shabhushan
 */

public interface UnionFind {
	
	/**
	 * Add connections between two objects p and q
	 * @param p
	 * 		Object p
	 * @param q
	 * 		Object q
	 */
	void union(int p, int q);
	
	/**
	 * Are p and q connected, i.e. p and q are within the same component ?
	 * @param p 
	 * 		Object p
	 * @param q
	 * 		Object q
	 * @return
	 * 		whether the objects <code>p</code> and <code>q</code> are within the same component
	 */
	boolean connected(int p, int q);
}
