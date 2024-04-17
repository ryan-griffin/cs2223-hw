import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashWords {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java HashWords <filename>");
			System.exit(1);
		}

		String filename = args[0].trim();
		int c = 123;
		int m = 997;
		int[] hashTable = new int[m];

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split("[^a-zA-Z'-]+");
				for (String word : words) {
					if (!word.isEmpty()) {
						int hash = computeHash(word, c, m);
						hashTable[hash]++;
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + filename);
			System.exit(1);
		}

		for (int i = 0; i < m; i++) {
			System.out.println(i + ": " + hashTable[i]);
		}
	}

	private static int computeHash(String word, int c, int m) {
		int hash = 0;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			hash = (hash * c + (int) ch) % m;
		}
		return hash;
	}
}
