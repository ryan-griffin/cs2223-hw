public class BRGC {
	public static void main(String[] args) {
		int n = 5;
		int[] code = brgc(n);
		for (int i = 0; i < code.length; i++) {
			System.out.println(i + " -> " + toBinaryString(code[i], n));
		}
	}

	public static int[] brgc(int n) {
		int size = (int) Math.pow(2, n);
		int[] code = new int[size];
		code[0] = 0;
		for (int i = 1; i < size; i++) {
			code[i] = i ^ (i >> 1);
		}
		return code;
	}

	public static String toBinaryString(int num, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = n - 1; i >= 0; i--) {
			int bit = (num >> i) & 1;
			sb.append(bit);
		}
		return sb.toString();
	}
}
