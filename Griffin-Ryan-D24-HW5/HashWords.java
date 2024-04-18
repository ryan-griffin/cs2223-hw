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

		int emptyCount = countNonEmptyaddresses(hashTable);
		System.out.println("Number of non-empty addresses: " + emptyCount);
		System.out.println("Load factor: " + getLoadFactor(hashTable, emptyCount) + "\n");

		int[] longestEmptyArea = getLongestArea(hashTable, true);
		System.out.println("Longest empty area: "
				+ longestEmptyArea[0] + " to " + longestEmptyArea[1] + "\n");

		int[] longestNonEmptyArea = getLongestArea(hashTable, false);
		System.out.println("Longest non-empty area: "
				+ longestNonEmptyArea[0] + " to " + longestNonEmptyArea[1]);
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
		System.out.println();
	}

	private static int countNonEmptyaddresses(String[][] hashTable) {
		int count = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i][0] != null) {
				count++;
			}
		}
		return count;
	}

	private static double getLoadFactor(String[][] hashTable, int count) {
		return (double) count / hashTable.length;
	}

	private static int[] getLongestArea(String[][] hashTable, boolean empty) {
		int maxStart = -1;
		int maxLength = 0;
		int currentStart = -1;
		int currentLength = 0;

		for (int i = 0; i < hashTable.length; i++) {
			boolean condition = empty ? hashTable[i][0] == null : hashTable[i][0] != null;

			if (condition) {
				if (currentStart == -1) {
					currentStart = i;
				}
				currentLength++;
			} else {
				if (currentLength > maxLength) {
					maxStart = currentStart;
					maxLength = currentLength;
				}
				currentStart = -1;
				currentLength = 0;
			}
		}

		if (currentLength > maxLength) {
			maxStart = currentStart;
			maxLength = currentLength;
		}

		return new int[] { maxStart, maxStart + maxLength - 1 };
	}

}
