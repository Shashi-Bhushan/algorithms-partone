package com.coursera.algorithms.partone.assignment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coursera.algorithms.partone.week01.assignment.Percolation;

public class PercolationTest {
	
	private static Percolation percolation;
	
	@BeforeClass
	public static void setup() {
		percolation = new Percolation(5);
	}
	
	@Test
	public void basicOpenTest() {
		assertFalse( percolation.isOpen(0, 0));
		
		percolation.open(0, 0);
		
		assertTrue( percolation.isOpen(0, 0));
	}
	
	@Test
	public void basicFullTest() {
		assertFalse( percolation.isFull(0, 3));
		
		// Open First Row Site
		percolation.open(0, 3);
		
		assertTrue( percolation.isFull(0, 3));
		
		// Open Exactly Down Site
		percolation.open(1, 3);
		
		assertTrue( percolation.isFull(1, 3));
		
		// Open Right Site
		percolation.open(1, 4);
		
		assertTrue( percolation.isFull(1, 4));
		
		// Open Down Site
		percolation.open(2, 4);
		
		assertTrue( percolation.isFull(2, 4));
		
		// Check that Left one is Still Not Full
		assertFalse( percolation.isFull(2, 3));
	}
	
	@Test
	public void percolationTest(){
		assertFalse( percolation.percolates());
		
		percolation.open(0, 0);
		percolation.open(1, 0);
		percolation.open(2, 0);
		percolation.open(3, 0);
		percolation.open(4, 0);
		
		assertTrue( percolation.percolates());
	}
}
