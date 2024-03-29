import java.util.HashMap;

public class Square {
	private static int[] square = { 1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15 };
	private static int sum = 33;

	public static void main(String[] args) {
		System.out.println("Number of 4-element combinations with the same sum: " + countFourElementCombinations());
		System.out.println("Number of all combinations with the same sum: " + countAllCombinations());
		System.out.println("Number of ways every possible sum can be formed: " + countWaysForSums());
	}

	private static int countFourElementCombinations() {
		int count = 0;
		for (int i = 0; i < square.length; i++) {
			for (int j = i + 1; j < square.length; j++) {
				for (int k = j + 1; k < square.length; k++) {
					for (int l = k + 1; l < square.length; l++) {
						if (square[i] + square[j] + square[k]
								+ square[l] == sum) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	private static int countAllCombinations() {
		int count = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < square.length; i++) {
			for (int j = i; j < square.length; j++) {
				int currentSum = square[i] + square[j];
				if (map.containsKey(sum - currentSum)) {
					count += map.get(sum - currentSum);
				}
				map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
			}
		}
		return count;
	}

	private static int countWaysForSums() {
		int[] dp = new int[sum + 1];
		dp[0] = 1;
		for (int i = 0; i < square.length; i++) {
			for (int j = sum; j >= square[i]; j--) {
				dp[j] += dp[j - square[i]];
			}
		}
		return dp[sum];
	}
}
