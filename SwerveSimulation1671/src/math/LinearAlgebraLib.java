package math;

public class LinearAlgebraLib {
	
	public static int dot(Vector a, Vector b) {
		int dotProduct = 0;
		for(int i = 0; i < a.getDimensions(); i++) {
			dotProduct += a.getComponent(i) * b.getComponent(i);
		}
		return dotProduct;
	}
	
	public static Vector add(Vector a, Vector b) {
		Vector result = new Vector(new double[a.getDimensions()]);
		for(int i = 0; i < result.getDimensions(); i++) {
			result.setComponent(i, a.getComponent(i) + b.getComponent(i));
		}
		return result;
	}
	
	public static Matrix multiply(Matrix a, Matrix b) {
		Matrix result = new Matrix(new int[a.getRows()][b.getColumns()]);
		for(int i = 0; i < result.getRows(); i++) {
			for(int j = 0; j < result.getColumns(); j++) {
				result.set(i, j, 0);
				for(int k = 0; k < a.getColumns(); k++) {
					result.set(i, j, result.get(i, j) + a.getArray()[i][k] * b.getArray()[k][j]);
				}
			}
		}
		return result;
	}
	
	public static void transpose(Matrix matrix) {
		int[][] matrixOld = matrix.getArray();
		matrix.reset(matrix.getColumns(), matrix.getRows());
		for(int i = 0; i < matrix.getRows(); i++) {
			for(int j = 0; j < matrix.getColumns(); j++) {
				matrix.set(i, j, matrixOld[j][i]);
			}
		}
	}
	
}
