package edu.astanait.assignment1;

import java.util.Random;

public class Util {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] randomArray(int n, Random rnd) {
        Metrics.allocations++; // we create one array here
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(100000);
        }
        return arr;
    }

    public static double[][] randomPoints(int n, Random rnd) {
        Metrics.allocations++; // one allocation for points[]
        double[][] points = new double[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = rnd.nextDouble() * 1000;
            points[i][1] = rnd.nextDouble() * 1000;
        }
        return points;
    }
}
