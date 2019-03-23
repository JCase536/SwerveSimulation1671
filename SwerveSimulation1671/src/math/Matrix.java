package math;

public class Matrix {
	
	private int r;
	private int c;
	private int[][] matrix;
	
	/**
	 * 
	 * @param arr
	 * Formatted using row x column
	 */
	public Matrix(int[][] arr) {
		this.r = arr.length;
		this.c = arr[0].length;
		matrix = arr;
	}
	
	public void addRow(int[] arr) {
		int[][] matrixOld = matrix;
		matrix = new int[++r][c];
		for(int i = 0; i < r - 1; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = matrixOld[i][j];
			}
		}
		for(int i = 0; i < c; i++) {
			matrix[r][i] = arr[i];
		}
	}
	
	public void addColumn(int[] arr) {
		int[][] matrixOld = matrix;
		matrix = new int[r][++c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c - 1; j++) {
				matrix[i][j] = matrixOld[i][j];
			}
		}
		for(int i = 0; i < r; i++) {
			matrix[i][c] = arr[i];
		}
		
	}
	
	public void removeRow(int r) {
		int[][] matrixOld = matrix;
		matrix = new int[--this.r][c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = matrixOld[i][j];
			}
		}
		for(int i = r; i < this.r; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = matrixOld[i + 1][j];
			}
		}
	}
	
	public void removeColumn(int c) {
		int[][] matrixOld = matrix;
		matrix = new int[r][--this.c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = matrixOld[i][j];
			}
		}
		for(int i = 0; i < r; i++) {
			for(int j = c; j < this.c; j++) {
				matrix[i][j] = matrixOld[i][j + 1];
			}
		}
	}
	
	public void display() {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
	}
	
	public void reset(int r, int c) {
		this.r = r;
		this.c = c;
		matrix = new int[r][c];
	}
	
	public void set(int r, int c, int value) {
		matrix[r][c] = value;
	}
	
	public int get(int r, int c) {
		return matrix[r][c];
	}
	
	public int getRows() {
		return r;
	}
	
	public int getColumns() {
		return c;
	}
	
	public int[][] getArray() {
		return matrix;
	}
	
}
