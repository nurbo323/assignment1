package edu.astanait.assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000};
        Random rnd = new Random();

        try (PrintWriter out = new PrintWriter(new FileWriter("metrics.csv"))) {
            // header for CSV
            out.println("Algorithm,n,comparisons,maxDepth,time(ns)");

            for (int n : sizes) {
                int[] arr = Util.randomArray(n, rnd);

                // ----- MergeSort -----
                int[] arr1 = arr.clone();
                Metrics.reset();
                long start = System.nanoTime();
                Sorts.mergeSort(arr1);
                long end = System.nanoTime();
                System.out.println("MergeSort n=" + n +
                        " comps=" + Metrics.comparisons +
                        " depth=" + Metrics.maxDepth +
                        " time=" + (end - start));
                out.println("MergeSort," + n + "," + Metrics.comparisons + "," + Metrics.maxDepth + "," + (end - start));

                // ----- QuickSort -----
                int[] arr2 = arr.clone();
                Metrics.reset();
                start = System.nanoTime();
                Sorts.quickSort(arr2);
                end = System.nanoTime();
                System.out.println("QuickSort n=" + n +
                        " comps=" + Metrics.comparisons +
                        " depth=" + Metrics.maxDepth +
                        " time=" + (end - start));
                out.println("QuickSort," + n + "," + Metrics.comparisons + "," + Metrics.maxDepth + "," + (end - start));

                // ----- Select -----
                int k = n / 2;
                Metrics.reset();
                start = System.nanoTime();
                int kth = Select.deterministicSelect(arr.clone(), k);
                end = System.nanoTime();
                System.out.println("Select n=" + n +
                        " kth=" + kth +
                        " comps=" + Metrics.comparisons +
                        " time=" + (end - start));
                out.println("Select," + n + "," + Metrics.comparisons + ",-," + (end - start));

                // ----- Closest Pair -----
                double[][] pts = Util.randomPoints(n, rnd);
                Metrics.reset();
                start = System.nanoTime();
                ClosestPair.Result res = ClosestPair.closestPair(pts);
                end = System.nanoTime();
                System.out.println("ClosestPair n=" + n +
                        " dist=" + res.dist +
                        " depth=" + Metrics.maxDepth +
                        " time=" + (end - start));
                out.println("ClosestPair," + n + ",-," + Metrics.maxDepth + "," + (end - start));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
