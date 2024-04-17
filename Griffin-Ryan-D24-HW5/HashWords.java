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
		String[][] hashTable = new String[m][2];

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split("[^a-zA-Z'-]+");
				for (String word : words) {
					if (!word.isEmpty()) {
						int hash = computeHash(word, c, m);
						while (hashTable[hash][0] != null && !hashTable[hash][0].equals(word)) {
							hash = (hash + 1) % m;
						}
						if (hashTable[hash][0] == null) {
							hashTable[hash][0] = word;
							hashTable[hash][1] = Integer.toString(hash);
						}
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + filename);
			System.exit(1);
		}

		displayHashTable(hashTable);
	}

	private static int computeHash(String word, int c, int m) {
		int hash = 0;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			hash = (hash * c + (int) ch) % m;
		}
		return hash;
	}

	private static void displayHashTable(String[][] hashTable) {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i][0] != null) {
				System.out.println(i + ", " + hashTable[i][0] + ", " + hashTable[i][1]);
			}
		}
	}
}
