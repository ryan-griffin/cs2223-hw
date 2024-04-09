public class GaussJordanElimination {
	public static void main(String[] args) {
		float[][] augmentedMatrix = {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364 },
				{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16 },
				{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36 },
				{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79 },
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61 },
				{ 0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0 },
				{ 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -42 }
		};

		gaussJordanElimination(augmentedMatrix);

		System.out.println("Solution:");
		for (int i = 0; i < augmentedMatrix.length; i++) {
			System.out.println("x_" + (i + 1) + " = " + augmentedMatrix[i][augmentedMatrix[i].length - 1]);
		}
	}

	private static void gaussJordanElimination(float[][] matrix) {
		int rowCount = matrix.length;
		int colCount = matrix[0].length;

		for (int i = 0; i < rowCount; i++) {
			int pivotRow = i;
			for (int j = i + 1; j < rowCount; j++) {
				if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivotRow][i])) {
					pivotRow = j;
				}
			}

			float[] temp = matrix[i];
			matrix[i] = matrix[pivotRow];
			matrix[pivotRow] = temp;

			for (int j = 0; j < rowCount; j++) {
				if (j != i) {
					float factor = matrix[j][i] / matrix[i][i];
					for (int k = i; k < colCount; k++) {
						matrix[j][k] -= matrix[i][k] * factor;
					}
				}
			}

			float pivotElement = matrix[i][i];
			for (int j = i; j < colCount; j++) {
				matrix[i][j] /= pivotElement;
			}
		}
	}
}
