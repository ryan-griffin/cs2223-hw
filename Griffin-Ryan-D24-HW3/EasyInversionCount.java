public class EasyInversionCount extends InversionCount {
	public static void main(String[] args) {
		int[] arr = parseArgs("Usage: java EasyInversionCount <n> <n> ...", args);

		int inversions = countInversions(arr);

		output(inversions);
	}

	private static int countInversions(int[] arr) {
		int count = 0;
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					count++;
				}
			}
		}
		return count;
	}
}
