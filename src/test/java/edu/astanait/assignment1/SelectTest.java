package edu.astanait.assignment1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {

    @Test
    void testDeterministicSelectSmall() {
        int[] arr = {5, 2, 9, 1, 6};
        int k = 2;
        int expected = Arrays.stream(arr).sorted().toArray()[k];

        Metrics.reset();
        int result = Select.deterministicSelect(arr.clone(), k);
        assertEquals(expected, result);
    }

    @Test
    void testDeterministicSelectRandom() {
        Random rnd = new Random(42);
        for (int trial = 0; trial < 50; trial++) {
            int n = 100;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(1000);

            int k = rnd.nextInt(n);
            int expected = Arrays.stream(arr).sorted().toArray()[k];

            Metrics.reset();
            int result = Select.deterministicSelect(arr.clone(), k);
            assertEquals(expected, result);
        }
    }
}
