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
	public void basicSanityTest() {
		assertFalse( percolation.isOpen(0, 0));
		
		percolation.open(0, 0);
		
		assertTrue( percolation.isOpen(0, 0));
	}
}
