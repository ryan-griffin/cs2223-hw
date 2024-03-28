public class Sequence {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Main <sequence> <n>");
			System.exit(1);
		}

		String sequence = args[0].trim().toLowerCase();
		int n = Integer.parseInt(args[1]);

		System.out.println(sequence + " numbers up to " + n + ":");

		int prevNum = 0;
		long prevTime = 0;
		for (int i = 0; i <= n; i++) {
			long startTime = System.nanoTime();
			int num = sequence.equals("lucas") ? lucas(i) : ryan(i);
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;

			System.out.println("Lucas(" + i + "): " + num + ", Time: " + elapsedTime + "ns");

			if (i > 1) {
				double ratio = (double) num / prevNum;
				double timeRatio = (double) elapsedTime / prevTime;

				System.out.println("Ratio: " + ratio + ", Time Ratio: " + timeRatio + "\n");
			}

			prevNum = num;
			prevTime = elapsedTime;
		}
	}

	private static int lucas(int n) {
		return n == 0 ? 2 : n == 1 ? 1 : lucas(n - 1) + lucas(n - 2);
	}

	private static int ryan(int n) {
		return n == 0 ? 3 : n == 1 ? 12 : ryan(n - 1) + ryan(n - 1) * 2;
	}
}
