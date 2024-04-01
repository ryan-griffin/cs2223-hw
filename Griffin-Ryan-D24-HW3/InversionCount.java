public abstract class InversionCount {
	public static int[] parseArgs(String error, String[] args) {
		if (args.length == 0) {
			System.out.println(error);
			System.exit(1);
		}

		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			arr[i] = Integer.parseInt(args[i]);
		}

		return arr;
	}

	public static void output(int inversions) {
		System.out.println("Number of inversions: " + inversions);
	}
}
