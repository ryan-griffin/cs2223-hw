import java.util.Scanner;

public class Dijkstra {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		int[][] matrix = {
				{ 0, 53, 10, 12, 0, 0, 0, 0, 0, 0 },
				{ 53, 0, 33, 0, 2, 0, 101, 0, 0, 0 },
				{ 10, 33, 0, 9, 30, 18, 0, 0, 0, 0 },
				{ 12, 0, 9, 0, 0, 17, 0, 0, 6, 0 },
				{ 0, 2, 30, 0, 0, 14, 123, 122, 0, 0 },
				{ 0, 0, 18, 17, 14, 0, 0, 137, 7, 0 },
				{ 0, 101, 0, 0, 123, 0, 0, 8, 0, 71 },
				{ 0, 0, 0, 0, 122, 137, 8, 0, 145, 66 },
				{ 0, 0, 0, 6, 0, 7, 0, 145, 0, 212 },
				{ 0, 0, 0, 0, 0, 0, 71, 66, 212, 0 }
		};

		System.out.print("Enter starting vertex: ");
		int start = scanner.nextInt();

		System.out.print("Enter ending vertex: ");
		int end = scanner.nextInt();

		dijkstra(matrix, start, end);
	}

	private static void dijkstra(int[][] matrix, int start, int end) {
		int n = matrix.length;
		int[] distances = new int[n];
		boolean[] visited = new boolean[n];
		int[] previous = new int[n];

		for (int i = 0; i < n; i++) {
			distances[i] = Integer.MAX_VALUE;
			visited[i] = false;
			previous[i] = -1;
		}

		distances[start] = 0;

		for (int count = 0; count < n - 1; count++) {
			int u = minDistance(distances, visited);
			visited[u] = true;

			for (int v = 0; v < n; v++) {
				if (!visited[v] && matrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE &&
						distances[u] + matrix[u][v] < distances[v]) {
					distances[v] = distances[u] + matrix[u][v];
					previous[v] = u;
				}
			}
		}

		System.out.println("Shortest distance from " + start + " to " + end + ": " + distances[end]);

		System.out.print("Shortest path: ");
		printPath(previous, start, end);
		System.out.println();
	}

	private static int minDistance(int[] distances, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int i = 0; i < distances.length; i++) {
			if (!visited[i] && distances[i] <= min) {
				min = distances[i];
				minIndex = i;
			}
		}

		return minIndex;
	}

	private static void printPath(int[] previous, int start, int end) {
		if (end == start) {
			System.out.print(start + " ");
			return;
		}
		printPath(previous, start, previous[end]);
		System.out.print(end + " ");
	}
}
