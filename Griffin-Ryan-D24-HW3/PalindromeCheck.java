public class PalindromeCheck {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Palindrome <string>");
			System.exit(1);
		}

		String s = args[0].trim().toLowerCase();

		if (isPalindrome(s)) {
			System.out.println(s + " is a palindrome.");
		} else {
			System.out.println(s + " is not a palindrome.");
		}
	}

	private static boolean isPalindrome(String s) {
		if (s.length() <= 1) {
			return true;
		} else if (s.charAt(0) != s.charAt(s.length() - 1)) {
			return false;
		} else {
			return isPalindrome(s.substring(1, s.length() - 1));
		}
	}
}
