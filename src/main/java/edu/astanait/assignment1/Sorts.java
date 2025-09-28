package edu.astanait.assignment1;

import java.util.Random;

public class Sorts {

    public static void mergeSort(int[] arr) {
        Metrics.allocations++; // buffer allocation counted
        int[] buffer = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, buffer);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] buffer) {
        if (left >= right) return;

        if (right - left < 16) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, buffer);
        mergeSort(arr, mid + 1, right, buffer);
        merge(arr, left, mid, right, buffer);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            Metrics.comparisons++;
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];
        for (int p = left; p <= right; p++) arr[p] = buffer[p];
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, new Random());
    }

    private static void quickSort(int[] arr, int left, int right, Random rnd) {
        if (left >= right) return;

        int pivotIndex = left + rnd.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
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

        quickSort(arr, left, i - 1, rnd);
        quickSort(arr, i + 1, right, rnd);
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
