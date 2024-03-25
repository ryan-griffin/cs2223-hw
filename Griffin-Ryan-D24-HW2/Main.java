public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main <n>");
			System.exit(1);
		}

		int n = Integer.parseInt(args[0]);

		System.out.println("Lucas numbers up to " + n + ":");

		int prevLucas = 0;
		long prevTime = 0;
		for (int i = 0; i <= n; i++) {
			long startTime = System.nanoTime();
			int lucasNumber = lucas(i);
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;

			System.out.println("Lucas(" + i + "): " + lucasNumber + ", Time: " + elapsedTime + "ns");

			if (i > 1) {
				double ratio = (double) lucasNumber / prevLucas;
				double timeRatio = (double) elapsedTime / prevTime;

				System.out.println("Ratio: " + ratio + ", Time Ratio: " + timeRatio + "\n");
			}

			prevLucas = lucasNumber;
			prevTime = elapsedTime;
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
