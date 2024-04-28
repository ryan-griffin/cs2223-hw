import java.util.Arrays;

public class NQueens {
	public static void main(String[] args) {
		for (int n = 4; n <= 20; n++) {
			int count = countSolutions(n);
			System.out.println("There are " + count + " solutions to the " + n + "-Queens problem.");
		}
	}

	private static int countSolutions(int n) {
		int[] board = new int[n];
		int count = 0;
		while (board[0] < n) {
			count++;
			board = nextSolution(board);
		}
		return count;
	}

	private static int[] nextSolution(int[] board) {
		do
			board = nextLegalPosition(board);
		while (!isSolution(board));
		return board;
	}

	private static boolean isSolution(int[] board) {
		return Arrays.stream(board).noneMatch(q -> q == 0);
	}

	private static int[] nextLegalPosition(int[] board) {
		do
			board = successor(board);
		while (!isLegalPosition(board));
		return board;
	}

	private static int[] successor(int[] board) {
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
