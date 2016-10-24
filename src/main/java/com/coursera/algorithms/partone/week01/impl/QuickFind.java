package com.coursera.algorithms.partone.week01.impl;

import com.coursera.algorithms.partone.week01.UnionFind;

/**
 * Quick Find 
 * This is the first implementation for UnionFind Problem of Dynamic Connectivity.
 * Quick Find is one of the solutions for dynamic connectivity problem. 
 * It is an Eager approach to finding whether two objects are within the same component or not
 * For connected and union operations, p and q both are index in the array.
 * Because we presume that the index is our object and it's value is the position.
 * 
 * Problems :
 * - while implementing {@link UnionFind#connected(int, int)}, many entries might need to be changed in the array.
 * Need to address this problem.
 * 
 * @author shabhushan
 *
 */
public class QuickFind implements UnionFind {

	/**
	 * Interpretation of Data Structure is that
	 * - Each index number of the array will act an object.
	 * - Value at each index will specify which component the object belongs to.
	 * 
	 * We presume that the index is our object and it's value is the position.
	 * Initially, each object will have it's own separate component.
	 * After a Union operation, it's value will change to the object's value, with which it's being connected.
	 * i.e. both objects will have same value indicating that both belongs to same component.
	 * 
	 * First, we need to initialize the array with there index values.
	 */
	private int[] id;
	
	/**
	 * Initialize the Data Structure with N positions 
	 * @param N
	 */
	QuickFind(int N) {
		id = new int[N];
		
		for(int index = 0; index < id.length; index++) {
			id[index] = index;
		}
	}
	
	/**
	 * @see UnionFind#union(int, int)
	 * 
	 * Union is More difficult here
	 * In order to merge both the components, we need to iterate over whole array and find all positions where ids needs to be changed.
	 * 
	 * As 
	 * For this implementation, we will check need to check position for each object in array.
	 * - if the value at index is equal to value at <code>p</code>
	 * - if found, change it to value of <code>q</code>
	 * 
	 * @param p
	 * 		Object p, it's the index in the array
	 * @param q
	 * 		Object q, it's the index in the array
	 */
	public void union(int p, int q) {
		int pValue = id[p];
		int qValue = id[q];
		
		for(int index = 0; index < id.length; index++) {
			if(id[index] == pValue) {
				id[index] = qValue;
			}
		}
	}

	/**
	 * @see UnionFind#connected(int, int)
	 * 
	 * This is why the name is QuickFind. The algorithm is able to find whether objects are connected or not in a single operation.
	 * For implementation of connected, check if the entries for both <code>p</code> and <code>q</code> are equal
	 * @return
	 * 		true if both p and q belongs to same component, otherwise false
	 */
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

}
