public class NQueens {
	public static void main(String[] args) {
		int[] board1 = { 1, 6, 8, 3, 7, 4, 2, 5 };
		System.out.println("1, 6, 8, 3, 7, 4, 2, 5: " + isLegalPosition(board1, 8));

		int[] board2 = { 1, 6, 8, 3, 7, 0, 0, 0 };
		System.out.println("1, 6, 8, 3, 7, 0, 0, 0: " + isLegalPosition(board2, 8));

		int[] board3 = { 1, 6, 8, 3, 5, 0, 0, 0 };
		System.out.println("1, 6, 8, 3, 5, 0, 0, 0: " + isLegalPosition(board3, 8));
	}

	public static boolean isLegalPosition(int[] board, int n) {
		for (int i = 0; i < n; i++) {
			if (board[i] == 0)
				continue;

			for (int j = i + 1; j < n; j++) {
				if (board[j] == 0)
					continue;

				if (board[i] == board[j] || Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
					return false;
				}
			}
		}
		return true;
	}
}
