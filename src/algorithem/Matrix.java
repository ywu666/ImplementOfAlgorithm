package algorithem;

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
}
