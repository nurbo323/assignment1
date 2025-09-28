package edu.astanait.assignment1;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Result {
        public double[] p1, p2;
        public double dist;
        Result(double[] p1, double[] p2, double dist) {
            this.p1 = p1;
            this.p2 = p2;
            this.dist = dist;
        }
    }

    public static Result closestPair(double[][] points) {
        double[][] ptsSortedX = points.clone();
        Arrays.sort(ptsSortedX, Comparator.comparingDouble(a -> a[0]));

        double[][] ptsSortedY = points.clone();
        Arrays.sort(ptsSortedY, Comparator.comparingDouble(a -> a[1]));

        Metrics.enter();
        Result res = closestPairRec(ptsSortedX, ptsSortedY);
        Metrics.exit();
        return res;
    }

    private static Result closestPairRec(double[][] ptsSortedX, double[][] ptsSortedY) {
        int n = ptsSortedX.length;

        if (n <= 3) {
            return bruteForce(ptsSortedX);
        }

        int mid = n / 2;
        double midX = ptsSortedX[mid][0];

        // left and right by X
        Metrics.allocations++; // leftX
        double[][] leftX = Arrays.copyOfRange(ptsSortedX, 0, mid);
        Metrics.allocations++; // rightX
        double[][] rightX = Arrays.copyOfRange(ptsSortedX, mid, n);

        // split ptsSortedY into leftY and rightY
        Metrics.allocations++; // leftY
        double[][] leftY = new double[mid][];
        Metrics.allocations++; // rightY
        double[][] rightY = new double[n - mid][];
        int li = 0, ri = 0;
        for (double[] p : ptsSortedY) {
            if (p[0] <= midX && li < leftY.length) {
                leftY[li++] = p;
            } else {
                rightY[ri++] = p;
            }
        }

        Metrics.enter();
        Result resLeft = closestPairRec(leftX, leftY);
        Metrics.exit();

        Metrics.enter();
        Result resRight = closestPairRec(rightX, rightY);
        Metrics.exit();

        Result best = (resLeft.dist < resRight.dist) ? resLeft : resRight;

        // strip: points within delta of midX, kept in Y order
        Result stripRes = stripClosest(ptsSortedY, midX, best.dist);
        if (stripRes != null && stripRes.dist < best.dist) {
            best = stripRes;
        }

        return best;
    }

    private static Result bruteForce(double[][] points) {
        double minDist = Double.POSITIVE_INFINITY;
        double[] bestP1 = null, bestP2 = null;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = dist(points[i], points[j]);
                if (d < minDist) {
                    minDist = d;
                    bestP1 = points[i];
                    bestP2 = points[j];
                }
            }
        }
        return new Result(bestP1, bestP2, minDist);
    }

    private static Result stripClosest(double[][] ptsSortedY, double midX, double delta) {
        // build strip
        double[][] strip = Arrays.stream(ptsSortedY)
                .filter(p -> Math.abs(p[0] - midX) < delta)
                .toArray(double[][]::new);
        Metrics.allocations++; // strip array counted

        double minDist = delta;
        double[] bestP1 = null, bestP2 = null;

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j][1] - strip[i][1]) < minDist; j++) {
                double d = dist(strip[i], strip[j]);
                if (d < minDist) {
                    minDist = d;
                    bestP1 = strip[i];
                    bestP2 = strip[j];
                }
            }
        }

        if (bestP1 == null) return null;
        return new Result(bestP1, bestP2, minDist);
    }

    private static double dist(double[] p1, double[] p2) {
        Metrics.comparisons++;
        double dx = p1[0] - p2[0];
        double dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
