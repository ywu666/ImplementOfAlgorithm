package algorithem;

import java.util.*;


/**
 * Flip the 2D m*n matrix functions
 * @author ywu
 *
 */
public class Matrix {

	public static void transportMatrix(int[][] matrix) {
		int rows = matrix.length;		
		for(int i = 0;i < rows;i++){
			for(int j = i + 1;j < rows;j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}

	public static void flipItVerticalAxis(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		//flip in place
		for(int i = 0;i < rows;i++){
			for(int j = 0;j < cols/2;j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][cols - 1 - j];
				matrix[i][cols - 1 - j] = temp;
			}
		}	
	}

	public static void flipItHoriontalAxis(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		//flip in place
		for(int i = 0;i < rows/2;i++){
			for(int j = 0; j < cols;j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[rows - 1 - i][j];
				matrix[rows - 1 - i][j] = temp;
			}
		}	
	}

	public static int[][] rotate(int[][] matrix) { //rotate CW 90 degree
		int n = matrix.length;
		for (int i = 0;i < n/2;i++) {
			for (int j = 0;j < Math.ceil(((double) n)/2.);j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
		return matrix;
	}

	public static void rotateSquareImageCW(int[][] matrix) {
		transportMatrix(matrix);
		flipItVerticalAxis(matrix);		    
	}

	public static void rotateSquareImageCCW(int[][] matrix) {
		transportMatrix(matrix);
		flipItHoriontalAxis(matrix);
	}

	//Time complexity O(n).
	public static ArrayList<Integer> findSpiral(int[][] matrix) { // print all the nodes in the CW
		ArrayList<Integer> spiralOrder = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) return spiralOrder;

		int rows = matrix.length;
		int cols = matrix[0].length;
		int x = 0, y = 0;

		while (rows > 0 && cols > 0) {
			if (rows == 1) { // when only one row
				for (int i = 0;i < cols;i++) {
					spiralOrder.add(matrix[x][y++]);
				}
				break;
			} else if (cols == 1) { // when only one column
				for(int i = 0;i < rows;i++) {
					spiralOrder.add(matrix[x++][y]);
				}
				break;
			}			
			// go through the matrix in CW
			for (int i = 0;i < cols - 1;i++) {
				spiralOrder.add(matrix[x][y++]);
			}
			for (int j = 0;j < rows - 1;j++) {
				spiralOrder.add(matrix[x++][y]);
			}

			for (int i = 0;i < cols - 1;i++) {
				spiralOrder.add(matrix[x][y--]);
			}
			for (int j = 0;j < rows - 1;j++) {
				spiralOrder.add(matrix[x--][y]);
			}	
			x++;
			y++;
			rows = rows - 2;
			cols = cols - 2;
		}
		return spiralOrder;
	}

	/**
	 * Use DFS to find the maximum sum along a path from the top-left of the grid to the bottom-right. 
	 * The direction of movement is limited to right and down.
	 * Time complexity = )= O(b^(m * n)), where b = branching factor. 
	 * Space complexity = O(b * n * m).
	 * @param grid A m x n matrix filled with non-negative integers.
	 * @return Return this maximum sum. 
	 */
	public static int matrixMaxSumDfS(int[][] grid) { // recursion
		class TravelNode { //Inner Class
			int row;
			int col;
			int nodeSum;

			TravelNode(int row, int col, int sum, int[][]matrix) {
				this.row = row;
				this.col = col;
				sum += matrix[row][col];
				this.nodeSum = sum;
			}
		}

		int maxSum = Integer.MIN_VALUE;
		int rows = grid.length;
		int cols = grid[0].length;
		if (rows < 2 && cols < 2) {
			return grid[0][0];
		}else {
			Deque<TravelNode> stack = new LinkedList<>();
			stack.addFirst(new TravelNode(0,0,0,grid));
			while(!stack.isEmpty()) {
				TravelNode node = stack.removeFirst();
				if (node.row == rows - 1 && node.col == cols - 1) {//update the maxSum whne the last node is reached
					if (node.nodeSum > maxSum) maxSum = node.nodeSum;
				}else {
					//go right 
					if (node.col < cols - 1) {
						stack.addFirst(new TravelNode(node.row,node.col + 1,node.nodeSum,grid));
					}
					//go down
					if (node.row < rows - 1) {
						stack.addFirst(new TravelNode(node.row + 1,node.col,node.nodeSum,grid));
					}
				}
			}
		}
		return maxSum;
	}

	public static int matrixMaxSumDP(int[][] grid) { //dynamic programming, more efficiency
		if(grid == null || grid.length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int[][] memo = new int[m][n];
		memo[0][0] = grid[0][0];
		// Pre-Fill first Column
		for(int i = 1;i < m;i++){
			memo[i][0] = memo[i - 1][0] + grid[i][0]; 
		}
		// Pre-Fill first Row
		for(int j = 1;j < n;j++){
			memo[0][j] = memo[0][j - 1] + grid[0][j]; 
		}
		// Fill remaining cells
		for(int i = 1;i < m;i++){
			for(int j = 1;j < n;j++){
				memo[i][j] = grid[i][j] + Math.max(memo[i - 1][j], memo[i][j - 1]);
			}
		}
		return memo[m - 1][n - 1];
	}
	
	public static boolean boggleSearch(char[][] board, String word){
		int rows = board.length;
		int cols = board[0].length;
		boolean out = false;

		for (int i = 0;i < rows;i++) {
			for (int j = 0;j < cols;j++) {
				out = search(i,j,board,word,"");
				if (out) return true;
			}
		}
		return out;
	}

	public static boolean search(int r, int c, char[][] board, String word, String predecessor) {
		int rows = board.length;
		int cols = board[0].length;
		//out of bound // not match pattern // visited
		if (r > (rows - 1) || r < 0 || c > (cols - 1) || c < 0 || !word.contains(predecessor) || board[r][c] == '@') {
			return false;
		}

		char ch = board[r][c];
		String s = predecessor + ch;
		boolean out = false;
		if (s.equals(word)) {
			return true;
		}else {

			board[r][c] = '@'; // Marked as visited

			out = search(r + 1,c,board,word,s)        // check up
					|| search(r - 1,c,board,word,s)   // check down
					|| search(r,c + 1,board,word,s)   // check left
					|| search(r,c - 1,board,word,s);  // check down

			board[r][c] = ch; //Un -Mark the board node
		}
		return out;
	}
	
	/**
	 * Find the largest square containing all 1's and return its 'area'. 
	 * The 'area' is simply the sum of all integers enclosed in the square.
	 * @param matrix : A two dimensional matrix made up of 0's and 1's.
	 * @return
	 */
	public static int largestSquare(char[][] matrix) {
		  if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		  
		    int rows = matrix.length;
		    int cols = matrix[0].length;
		    int[][] t = new int[rows][cols];
		    
		    //top row
		    for (int i = 0;i < rows;i++) {
		        t[i][0] = Character.getNumericValue(matrix[i][0]);
		    }
		    
		    //left column
		    for (int i = 0;i < cols;i++) {
		    	t[0][i] = Character.getNumericValue(matrix[0][i]);
		    }
		    
		    //cells inside
		    for (int i = 1;i < rows;i++) {
		    	for (int j = 1;j < cols;j++) {
		    		if (matrix[i][j] == '1') {
		    			//find the minimum in the square
		    			int min = Math.min(t[i][j - 1], Math.min(t[i - 1][j],t[i - 1][j - 1]));
		    			t[i][j] = min + 1;
		    		}else {
		    			t[i][j] = 0;
		    		}
		    	}
		    }
		    
		    int max = 0; // get maximum length
		    for (int i = 0;i < rows;i++) {
		    	for (int j = 0;j < cols;j++) {
		    		//System.out.println("t["+i+"]" + "[" + j +"]:" + t[i][j]);
		    		if (t[i][j] > max) max = t[i][j];
		    	}
		    }
		   return max * max; 
	}
	
	//The direction of movement is limited to right and down.
	public static int minWeightedPath(int[][] matrix) {
		  if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		  
		    int rows = matrix.length;
		    int cols = matrix[0].length;
		    int[][] t = new int[rows + 1][cols + 1];
		    
		    //Initialize the first column and row
		    for (int i = 0;i <= rows;i++) {
		        t[i][0] = Integer.MAX_VALUE/2;
		    }
		    
		    for (int i = 0;i <= cols;i++) {
		    	t[0][i] = Integer.MAX_VALUE/2;
		    }
		    t[1][0] = 0;
		    t[0][1] = 0;
		    
		    for (int i = 1;i <= rows;i++) {
		    	for (int j = 1;j <= cols;j++) {
		    		//find if minimum of right and down direction
		    		t[i][j] = Math.min(t[i - 1][j] + matrix[i - 1][j - 1], t[i][j - 1] + matrix[i - 1][j - 1]);
		    	}
		    }
		    return t[rows][cols];
	}
	
	/**
	 * Prints all possible paths from the top left cell to the bottom right cell. 
	 * @param board: A 2D board which contains an m x n matrix of chars
	 * @return An ArrayList of Strings, where each String represents a path with 
	 * characters appended in the order of movement. 
	 */
	public ArrayList<String> printPaths(char[][] board){
		StringBuilder str = new StringBuilder();
		ArrayList<String> output = new ArrayList<>();	
	    search(0,0,board,str,output);
	    return output;
	}
	//This is a help method.
	private void search(int i, int j, char[][] board, StringBuilder str, ArrayList<String> output) {
		int rows = board.length - 1;
		int cols = board[0].length - 1;
		if(i > rows || j > cols) return ; //Out of range
		str.append(board[i][j]);          //Mark the node.
		
		if(i == rows && j == cols) {      //Reach the end of board
			output.add(str.toString());
			str.deleteCharAt(str.length() - 1); //Un-Mark the node
			return ;
		}
		//limited direction to down and right
		search(i + 1,j,board,str,output);
		search(i,j + 1,board,str,output);
		str.deleteCharAt(str.length() - 1);    //Un-Mark the node
	}
}
