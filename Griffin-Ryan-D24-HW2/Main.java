public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main <n>");
			System.exit(1);
		}

		int n = Integer.parseInt(args[0]);

		System.out.println("Lucas numbers up to " + n + ":");
		for (int i = 0; i <= n; i++) {
			System.out.println(lucas(i));
		}
	}

	private static int lucas(int n) {
		if (n == 0) {
			return 2;
		}
		if (n == 1) {
			return 1;
		}
		return lucas(n - 1) + lucas(n - 2);
	}
}
