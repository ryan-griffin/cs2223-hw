import java.util.Arrays;

public class NQueens {
	public static void main(String[] args) {
		int[] board1 = { 1, 6, 8, 3, 5, 0, 0, 0 };
		printNextLegal(board1);

		int[] board2 = { 1, 6, 8, 3, 7, 0, 0, 0 };
		printNextLegal(board2);

		int[] board3 = { 1, 6, 8, 3, 7, 4, 2, 5 };
		printNextLegal(board3);
	}

	private static void printNextLegal(int[] board) {
		System.out.println(
				"Next legal position from " + Arrays.toString(board) + " is "
						+ Arrays.toString(nextLegalPosition(board)));
	}

	private static int[] nextLegalPosition(int[] board) {
		do
			board = Successor(board);
		while (!isLegalPosition(board));
		return board;
	}

	private static int[] Successor(int[] board) {
		int n = board.length;
		int i = n - 1;
		while (i >= 0 && board[i] == n)
			i--;

		if (i < 0)
			return board;

		board[i]++;
		for (int j = i + 1; j < n; j++)
			board[j] = 0;
		return board;
	}

	private static boolean isLegalPosition(int[] board) {
		int n = board.length;
		for (int i = 0; i < n; i++) {
			if (board[i] == 0)
				continue;

			for (int j = i + 1; j < n; j++) {
				if (board[j] == 0)
					continue;

				if (board[i] == board[j] || Math.abs(board[i] - board[j]) == Math.abs(i - j))
					return false;
			}
		}
		return true;
	}
}
