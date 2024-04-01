public class EasyInversionCount {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java EasyInversionCount <n> <n> ...");
			System.exit(1);
		}

		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			arr[i] = Integer.parseInt(args[i]);
		}

		int inversions = countInversions(arr);
		System.out.println("Number of inversions: " + inversions);
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
