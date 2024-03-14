import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();
	private static HashMap<String, Integer> markers = new HashMap<>() {
		{
			put("green", 3);
			put("yellow", 7);
			put("orange", 5);
		}
	};

	public static void main(String[] args) {
		System.out.println("Welcome to Double Trouble!\n");
		System.out.println("Instructions:");
		System.out.println(
				"Two players take turns removing as many markers of a single color as they wish.");
		System.out.println("The player who removes the last marker wins.\n");

		// Determine who goes first
		boolean playerTurn = decideFirstPlayer();

		// Game loop
		while (true) {
			displayMarkers();
			if (playerTurn) {
				playerMove();
			} else {
				computerMove();
			}
			if (isGameOver()) {
				displayWinner(playerTurn);
				break;
			}
			playerTurn = !playerTurn;
		}
	}

	// Method to decide who goes first
	private static boolean decideFirstPlayer() {
		while (true) {
			System.out.print("Who goes first? Enter 'player' or 'computer': ");
			String choice = scanner.nextLine().trim().toLowerCase();
			if (choice.equals("player")) {
				return true;
			} else if (choice.equals("computer")) {
				return false;
			} else {
				System.out.println("Invalid input. Please enter 'player' or 'computer'.");
			}
		}
	}

	// Method to display the current markers
	private static void displayMarkers() {
		System.out.println("Current markers:");
		for (String color : markers.keySet()) {
			System.out.println(color + ": " + markers.get(color));
		}
		System.out.println();
	}

	// Method for player's move
	private static void playerMove() {
		while (true) {
			System.out.print("Enter your move as 'color:amount': ");
			String input = scanner.nextLine();
			String[] parts = input.split(":");
			if (parts.length != 2) {
				System.out.println("Invalid input. Please enter in format 'color:amount'.");
				continue;
			}
			String color = parts[0].trim().toLowerCase();
			int amount;
			if (!markers.containsKey(color)) {
				System.out.println("Invalid color. Please choose from available colors.");
				continue;
			}
			try {
				amount = Integer.parseInt(parts[1].trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number for amount.");
				continue;
			}
			if (amount <= 0) {
				System.out.println("Invalid input. Amount must be greater than zero.");
				continue;
			}
			if (amount > markers.get(color)) {
				System.out.println("Not enough markers in the chosen color.");
				continue;
			}
			markers.put(color, markers.get(color) - amount);
			break;
		}
	}

	// Method for computer's move
	private static void computerMove() {
		// Check if there's a winning move available
		String winningColor = findWinningMove();
		if (winningColor != null) {
			int maxAmount = markers.get(winningColor);
			int amount = random.nextInt(maxAmount) + 1;
			System.out.println("Computer removes " + amount + " markers from " + winningColor + ".");
			markers.put(winningColor, markers.get(winningColor) - amount);
		} else {
			// Make a random move
			String[] colors = markers.keySet().toArray(new String[0]);
			String color = colors[random.nextInt(colors.length)];
			int maxAmount = markers.get(color);
			int amount = random.nextInt(maxAmount) + 1;
			System.out.println("Computer removes " + amount + " markers from " + color + ".");
			markers.put(color, markers.get(color) - amount);
		}
	}

	// Method to check for a winning move
	private static String findWinningMove() {
		int sum = 0;
		for (int count : markers.values()) {
			sum ^= count;
		}
		if (sum != 0) {
			for (String color : markers.keySet()) {
				int modifiedSum = sum ^ markers.get(color);
				if (modifiedSum < markers.get(color)) {
					return color;
				}
			}
		}
		return null;
	}

	// Method to check if the game is over
	private static boolean isGameOver() {
		for (int count : markers.values()) {
			if (count > 0) {
				return false;
			}
		}
		return true;
	}

	// Method to display the winner
	private static void displayWinner(boolean playerWon) {
		System.out.println("");
		if (playerWon) {
			System.out.println("Congratulations! You win!");
		} else {
			System.out.println("The computer wins! Better luck next time.");
		}
	}
}
