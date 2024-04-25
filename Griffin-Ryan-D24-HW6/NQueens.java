import java.util.Arrays;

public class NQueens {
	public static void main(String[] args) {
		int[] board1 = { 1, 6, 8, 3, 5, 0, 0, 0 };
		printNextLegal(board1, 8);

		int[] board2 = { 1, 6, 8, 3, 7, 0, 0, 0 };
		printNextLegal(board2, 8);

		int[] board3 = { 1, 6, 8, 3, 7, 4, 2, 5 };
		printNextLegal(board3, 8);
	}

	private static void printNextLegal(int[] board, int n) {
		System.out.println(
				"Next legal position from " + Arrays.toString(board) + " is "
						+ Arrays.toString(NextLegalPosition(board, n)));
	}

	private static int[] NextLegalPosition(int[] board, int n) {
		do {
			board = Successor(board, n);
		} while (!isLegalPosition(board, n));
		return board;
	}

	private static int[] Successor(int[] board, int n) {
		int[] nextBoard = Arrays.copyOf(board, n);
		int i = n - 1;

		while (i >= 0 && nextBoard[i] == n) {
			i--;
		}

		if (i < 0) {
			Arrays.fill(nextBoard, 0);
			return nextBoard;
		}

		nextBoard[i]++;

		for (int j = i + 1; j < n; j++) {
			nextBoard[j] = 0;
		}

		return nextBoard;
	}

	private static boolean isLegalPosition(int[] board, int n) {
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
