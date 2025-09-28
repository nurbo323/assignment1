package edu.astanait.assignment1;

public class Metrics {
    public static long comparisons = 0; // number of comparisons (semantic)
    public static int depth = 0;        // current recursion depth
    public static int maxDepth = 0;     // maximal observed depth
    public static long allocations = 0; // number of array-like allocations counted manually

    public static void reset() {
        comparisons = 0;
        depth = 0;
        maxDepth = 0;
        allocations = 0;
    }

    public static void enter() {
        depth++;
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public static void exit() {
        depth--;
    }
}
