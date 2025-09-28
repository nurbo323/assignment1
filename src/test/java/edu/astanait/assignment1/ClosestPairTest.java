package edu.astanait.assignment1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testClosestPairSmall() {
        double[][] points = {
                {0, 0}, {3, 4}, {1, 1}, {2, 2}
        };

        Metrics.reset();
        ClosestPair.Result res = ClosestPair.closestPair(points);

        double expectedDist = Math.sqrt(2);
        assertEquals(expectedDist, res.dist, 1e-9);
    }

    @Test
    void testClosestPairRandom() {
        Random rnd = new Random(42);
        double[][] points = Util.randomPoints(200, rnd);

        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dx = points[i][0] - points[j][0];
                double dy = points[i][1] - points[j][1];
                double d = Math.sqrt(dx * dx + dy * dy);
                minDist = Math.min(minDist, d);
            }
        }

        Metrics.reset();
        ClosestPair.Result res = ClosestPair.closestPair(points);

        assertEquals(minDist, res.dist, 1e-6);
    }
}
