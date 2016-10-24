package com.coursera.algorithms.partone.week01.assignment;

public class Percolation {
	
	private boolean[][] percolationArray;
	private int[][] id;
	private int[][] size;
	private boolean[][] isFull;
	
	private final int N;
	
	/**
	 * create n-by-n grid, with all sites blocked
	 * 
	 * Initially
	 * - Each site will be blocked 
	 * - Each site will have unique parent entry. i.e. the site is it's own parent
	 */
	public Percolation(int N) {
		this.N = N;
		
		percolationArray = new boolean[N][N];
		id = new int[N][N];
		size = new int[N][N];
		isFull = new boolean[N][N];
		
		for(int row = 0; row < N; row++) {
			for(int column = 0; column < N; column++) {
				// will be false anyway by default. Anyway, adding for clarification
				percolationArray[row][column] = false;
				
				id[row][column] = row * N + column;
				
				size[row][column] = 1;
				
				isFull[row][column] = false;
			}
		}
	}
	
	public void open(int row, int col) {       // open site (row, col) if it is not open already
		percolationArray[row][col] = true;
		
		boolean isFull = true;
		
		// Now, need to connect to adjacent sites if they are open
		// Left Site
		if(0 < col && percolationArray[row][col - 1]) {
			unionInternal( new Point(row, col), new Point(row, col - 1));
		} else {
			isFull = false;
		}
		// Right Site
		if(col < percolationArray.length && percolationArray[row][col + 1]) {
			unionInternal( new Point(row, col), new Point(row, col + 1));
		} else {
			isFull = false;
		}
		// Up Site
		if(0 < row && percolationArray[row - 1][col]) {
			unionInternal( new Point(row - 1, col), new Point(row, col));
		} else {
			isFull = false;
		}
		// Down Site
		if(row < percolationArray.length && percolationArray[row + 1][col]) {
			unionInternal(new Point(row + 1, col), new Point(row, col));
		} else {
			isFull = false;
		}
		
		this.isFull[row][col] = isFull;
	}
	
	/**
	 * Merge Site id[r1][c1] with id[r2][c2]
	 * 
	 */
	private void unionInternal(Point p1, Point p2) {
		Point pRoot = rootInternal(p1.getX(), p1.getY());
		Point qRoot = rootInternal(p2.getX(), p2.getY());
		
		
		int pRootX = pRoot.getX();
		int pRootY = pRoot.getY();
		int qRootX = qRoot.getX();
		int qRootY = qRoot.getY();
		
		// Merge one into another, considering Size as well
		if(size[pRootX][pRootY] < size[qRootX][qRootY]) {
			id[pRootX][pRootY] = qRootX * N + qRootY;
			
			size[qRootX][qRootY] = size[qRootX][qRootY] + size[pRootX][pRootY];
		} else {
			id[qRootX][qRootY] = pRootX * N + pRootY;
			
			size[pRootX][pRootY] = size[pRootX][pRootY] + size[qRootX][qRootY];
		}
	}
	
	private Point rootInternal(int row, int column) {
		int r = row;
		int c = column;
		
		while(r * N + c != id[r][c]) {
			r = id[r][c] / N;
			c = id[r][c] % N;
		}
		
		return new Point(r, c); //r * N + c;
	}
	
	
	
	public boolean isOpen(int row, int col) {  // is site (row, col) open?
		return percolationArray[row][col];
	}
	public boolean isFull(int row, int col) {  // is site (row, col) full?
		
		if(isFull[row][col]) {
			return true;
		} else if(
									   (0 < col && percolationArray[row][col - 1])
		&& (0 < row && percolationArray[row - 1][col]) 								&& (row < this.N && percolationArray[row + 1][col])
									&& (col < this.N && percolationArray[row][col + 1])		
		) {
			// TODO: Bug here
			// if col is zero, it fails
			isFull[row][col] = true;
			return true;
		} else {
			return false;
		}
	}
	public boolean percolates() {              // does the system percolate?
		return false;
	}
	
	/**
	 * Test Client 
	 * isOpen is working
	 * isFull is not working
	 */
    public static void main(String[] args) {   // test client (optional)
    	Percolation percolation = new Percolation(5);
    	
    	percolation.open(0,0);
    	percolation.open(1,0);
    	percolation.open(0,1);
    	
    	System.out.println("isFull : " + percolation.isOpen(0, 0));
    	System.out.println("isFull : " + percolation.isOpen(1, 0));
    	System.out.println("isFull : " + percolation.isOpen(0, 1));
    	System.out.println("isFull : " + percolation.isFull(0, 0));
    }
    
    private static class Point {
    	private int x;
    	private int y;
    	
    	Point(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    	
    	public int getX() {
    		return x;
    	}
    	
    	public int getY() {
    		return y;
    	}
    }
}
