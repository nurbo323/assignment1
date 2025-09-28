package edu.astanait.assignment1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSortDepthTest {

    @Test
    void testQuickSortDepthBound() {
        int n = 8192; // 2^13
        Random rnd = new Random(123);
        int[] arr = Util.randomArray(n, rnd);

        Metrics.reset();
        Sorts.quickSort(arr);

        int bound = 2 * (int) (Math.floor(Math.log(n) / Math.log(2))) + 50;
        System.out.println("QS maxDepth = " + Metrics.maxDepth + ", bound = " + bound);
        assertTrue(Metrics.maxDepth <= bound, "QuickSort recursion depth is too large");
    }
}
