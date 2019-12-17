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
		for(int i = 0; i < rows; i++){
			for(int j = i+1; j < rows; j++){
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
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols/2; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][cols - 1 - j];
				matrix[i][cols - 1 -j] = temp;
			}
		}	
	}

	public static void flipItHoriontalAxis(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		//flip in place
		for(int i = 0; i < rows/2; i++){
			for(int j = 0; j < cols; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[rows - 1 - i][ j];
				matrix[rows - 1 - i][j] = temp;
			}
		}	
	}

	public static void rotateSquareImageCW(int[][] matrix) {
		transportMatrix(matrix);
		flipItVerticalAxis(matrix);		    
	}

	public static void rotateSquareImageCCW(int[][] matrix) {
		transportMatrix(matrix);
		flipItHoriontalAxis(matrix);
	}

	public static ArrayList<Integer> findSpiral(int[][] matrix) { // print all the nodes in the CW
		ArrayList<Integer> spiralOrder = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) return spiralOrder;

		int rows = matrix.length;
		int cols = matrix[0].length;
		int x = 0, y =0;

		while (rows > 0 && cols > 0) {
			if (rows == 1) { // when only one row
				for (int i =0; i <cols; i++) {
					spiralOrder.add(matrix[x][y++]);
				}
				break;
			} else if (cols == 1) { // when only one column
				for(int i = 0; i < rows; i++) {
					spiralOrder.add(matrix[x++][y]);
				}
				break;
			}			
			for (int i=0;i<cols-1;i++) {
				spiralOrder.add(matrix[x][y++]);
			}
			for (int j=0;j<rows-1;j++) {
				spiralOrder.add(matrix[x++][y]);
			}

			for (int i=0;i<cols-1;i++) {
				spiralOrder.add(matrix[x][y--]);
			}
			for (int j=0;j<rows-1;j++) {
				spiralOrder.add(matrix[x--][y]);
			}	
			x++;
			y++;
			rows=rows-2;
			cols=cols-2;
		}
		return spiralOrder;
	}

	public static int matrixMaxSumDfs(int[][] grid) { // recursion
		class TravelNode { //Inner Class
			int row;
			int col;
			int nodeSum;

			TravelNode(int row, int col, int sum, int[][]matrix) {
				this.row = row;
				this.col = col;
				sum +=matrix[row][col];
				this.nodeSum = sum;
			}
		}

		int maxSum = Integer.MIN_VALUE;
		int rows = grid.length;
		int cols = grid[0].length;
		if (rows <2 && cols<2) {
			return grid[0][0];
		}else {
			Deque<TravelNode> stack = new LinkedList<>();
			stack.addFirst(new TravelNode(0,0,0,grid));
			while(!stack.isEmpty()) {
				TravelNode node= stack.removeFirst();
				if (node.row == rows -1 && node.col == cols -1) {//update the maxSum whne the last node is reached
					if (node.nodeSum > maxSum) maxSum = node.nodeSum;
				}else {
					//go right 
					if (node.col < cols - 1) {
						stack.addFirst(new TravelNode(node.row, node.col + 1, node.nodeSum,grid));
					}
					//go down
					if (node.row < rows -1) {
						stack.addFirst(new TravelNode(node.row +1, node.col, node.nodeSum,grid));
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
		for(int i = 1; i < m; i++){
			memo[i][0] = memo[i-1][0] + grid[i][0]; 
		}
		// Pre-Fill first Row
		for(int j = 1; j < n; j++){
			memo[0][j] = memo[0][j-1] + grid[0][j]; 
		}
		// Fill remaining cells
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				memo[i][j] = grid[i][j] + Math.max(memo[i-1][j], memo[i][j-1]);
			}
		}
		return memo[m-1][n-1];
	}

}
