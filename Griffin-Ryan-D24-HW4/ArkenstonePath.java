public class ArkenstonePath {
	public static void main(String[] args) {
		int[][] gems = {
				{ 96, 33, 44, 98, 75, 68, 99, 84 },
				{ 10, 41, 1, 86, 46, 24, 53, 93 },
				{ 83, 97, 94, 27, 65, 51, 30, 7 },
				{ 56, 70, 47, 64, 22, 88, 67, 12 },
				{ 91, 11, 77, 48, 13, 71, 92, 15 },
				{ 32, 59, 17, 25, 31, 4, 16, 63 },
				{ 79, 5, 14, 23, 78, 37, 40, 74 },
				{ 35, 89, 52, 66, 82, 20, 95, 21 }
		};

		int[][] dp = computeMaxGems(gems);
		int startSquare = findStartSquare(dp);
		int maxGems = dp[0][startSquare];

		printResults(gems, dp, startSquare, maxGems);
	}

	private static int[][] computeMaxGems(int gems[][]) {
		int[][] dp = new int[8][8];

		for (int j = 0; j < 8; j++) {
			dp[7][j] = gems[7][j];
		}

		for (int i = 6; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				int max = gems[i][j];

				if (j > 0) {
					max = Math.max(max, gems[i][j] + dp[i + 1][j - 1]);
				}

				max = Math.max(max, gems[i][j] + dp[i + 1][j]);

				if (j < 7) {
					max = Math.max(max, gems[i][j] + dp[i + 1][j + 1]);
				}

				dp[i][j] = max;
			}
		}

		return dp;
	}

	private static int findStartSquare(int[][] dp) {
		int maxGems = 0;
		int startSquare = -1;
		for (int j = 0; j < 8; j++) {
			if (dp[0][j] > maxGems) {
				maxGems = dp[0][j];
				startSquare = j;
			}
		}
		return startSquare;
	}

	private static void printResults(int[][] gems, int[][] dp, int startSquare, int maxGems) {
		System.out.println("Bilbo's starting square: Row 1, Column " + (startSquare + 1));
		System.out.println("Total number of gems collected: " + maxGems);

		int arkenstoneVault = startSquare + 1;
		System.out.println("The vault containing the Arkenstone: " + arkenstoneVault);

		System.out.println("Bilbo's path:");
		int row = 0;
		int col = startSquare;
		System.out.println("Row " + (row + 1) + ", Column " + (col + 1));
		for (int i = 1; i < gems.length; i++) {
			int nextRow = row + 1;
			int nextCol = col;
			if (col > 0 && dp[nextRow][nextCol - 1] == dp[row][col] - gems[row][col]) {
				nextCol = col - 1;
			} else if (col < 7 && dp[nextRow][nextCol + 1] == dp[row][col] - gems[row][col]) {
				nextCol = col + 1;
			}
			row = nextRow;
			col = nextCol;
			System.out.println("Row " + (row + 1) + ", Column " + (col + 1));
		}
	}
}
