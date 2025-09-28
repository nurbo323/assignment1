package edu.astanait.assignment1;

public class Select {

    public static int deterministicSelect(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right);
        int pos = partition(arr, left, right, pivot);

        if (k == pos) return arr[k];
        else if (k < pos) return select(arr, left, pos - 1, k);
        else return select(arr, pos + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        Util.swap(arr, pivotIndex, right);
        int i = left;
        for (int j = left; j < right; j++) {
            Metrics.comparisons++;
            if (arr[j] <= pivot) {
                Util.swap(arr, i, j);
                i++;
            }
        }
        Util.swap(arr, i, right);
        return i;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            insertionSort(arr, left, right);
            return arr[left + n / 2];
        }

        int numMedians = (n + 4) / 5;
        Metrics.allocations++; // array of medians
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }
        return medianOfMedians(medians, 0, numMedians - 1);
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
