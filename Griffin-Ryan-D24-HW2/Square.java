public class Square {
	public static void main(String[] args) {
		int[][] magicSquare = {
				{ 1, 14, 14, 4 },
				{ 11, 7, 6, 9 },
				{ 8, 10, 10, 5 },
				{ 13, 2, 3, 15 }
		};

		int targetSum = magicSquare[0][0] + magicSquare[1][1] + magicSquare[2][2] + magicSquare[3][3];

		int combinationsCount = countCombinations(magicSquare, targetSum);
		System.out.println("Number of 4-element combinations with the same sum as rows/columns: " + combinationsCount);
	}

	private static boolean hasTargetSum(int[] arr, int targetSum) {
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		return sum == targetSum;
	}

	private static int countCombinations(int[][] square, int targetSum) {
		int count = 0;
		int n = square.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				for (int k = i + 1; k < n; k++) {
					for (int l = j + 1; l < n; l++) {
						int[] combination = { square[i][j], square[i][l], square[k][j], square[k][l] };
						if (hasTargetSum(combination, targetSum)) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}
}
