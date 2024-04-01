public class FastInversionCount extends InversionCount {
	public static void main(String[] args) {
		int[] arr = parseArgs("Usage: java FastInversionCount <n> <n> ...", args);

		int inversions = countInversions(arr, 0, arr.length - 1);

		output(inversions);
	}

	private static int countInversions(int[] arr, int left, int right) {
		int count = 0;
		if (left < right) {
			int mid = left + (right - left) / 2;
			count += countInversions(arr, left, mid);
			count += countInversions(arr, mid + 1, right);
			count += merge(arr, left, mid, right);
		}
		return count;
	}

	private static int merge(int[] arr, int left, int mid, int right) {
		int[] temp = new int[right - left + 1];
		int count = 0;
		int i = left;
		int j = mid + 1;
		int k = 0;

		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
				count += mid - i + 1;
			}
		}

		while (i <= mid) {
			temp[k++] = arr[i++];
		}

		while (j <= right) {
			temp[k++] = arr[j++];
		}

		for (int p = 0; p < temp.length; p++) {
			arr[left + p] = temp[p];
		}

		return count;
	}
}
