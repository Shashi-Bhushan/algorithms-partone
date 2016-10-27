package com.coursera.algorithms.partone.week01.assignment;

/**
 * Percolation Matrix is a N * N matrix of Sites
 * - Each site is either blocked or open
 * - An open site is full, iff it is connected to the top via open sites
 * @author shabhushan
 *
 */
public class Percolation {
	
	/**
	 * Interpretation is that percolation array signifies which Sites in matrix are open
	 * A site will be open only if it's corresponding value is true
	 */
	private boolean[][] open;
	/**
	 * N is the Row or Column number of the N * N square matrix
	 */
	private final int N;
	
	/**
	 * create N-by-N grid, with all sites blocked
	 * 
	 * Initially
	 * - Each site will be blocked 
	 */
	public Percolation(int N) {
		this.N = N;
		
		open = new boolean[N][N];
		
		for(int row = 0; row < N; row++) {
			for(int column = 0; column < N; column++) {
				// will be false anyway by default. Anyway, adding for clarification
				open[row][column] = false;
			}
		}
	}
	
	/**
	 * Open the site and check if it is Full
	 * @param row
	 * @param col
	 */
	public void open(int row, int col) {       // open site (row, col) if it is not open already
		open[row][col] = true;
	}
	
	public boolean isOpen(int row, int col) {  // is site (row, col) open?
		return open[row][col];
	}
	
	public boolean isFull(int row, int col) {  // is site (row, col) full?
		
		boolean[][] full = getFullMatrix(open);
		
		return full[row][col];
	}
	
	public boolean percolates() {              // does the system percolate?
		
		boolean[][] full = getFullMatrix(open);
		
		// Last row has any Full Site, then System percolates
		for(int index = 0; index < N; index++) {
			if(full[N - 1][index]){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns a Full Matrix having details about what Sites are Full in the Matrix
	 * isFull array will keep count of how many Sites in the matrix are connected to the top
	 * 
	 * @param open
	 * @return
	 */
	private boolean[][] getFullMatrix(boolean[][] open) {
		
		boolean[][] full = new boolean[N][N];
		
		// copy First Row from open 
		for(int index = 0; index < N; index++) {
			full[0][index] = open[0][index];
		}
		
		// Check if other rows percolated
		for(int row = 1; row < N; row++) {
			for(int col = 0; col < N; col++) {
				// if cell is open and any one adjacent is Full
				 if( open[row][col] && (
						 							// TOP SITE
						   						(0 < col ? full[row][col - 1] : true)
						   		// LEFT SITE												// RIGHT SITE
					|| (0 < row ? full[row - 1][col] : true) 						|| (row < this.N ? full[row + 1][col] : true)
													// BOTTOM SITE
											|| (col < this.N ? full[row][col + 1] : true)		
						 )
					) {
					 full[row][col] = true;
				 }
			}
		}
		
		return full;
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
}
