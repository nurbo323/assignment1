package edu.astanait.assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class SortsTest {

    @Test
    void testMergeSort() {
        // создаем случайный массив
        int[] arr = Util.randomArray(1000, new Random());

        // создаем копию и сортируем стандартным методом Java
        int[] expected = arr.clone();
        Arrays.sort(expected);

        // сортируем нашим MergeSort
        Sorts.mergeSort(arr);

        // проверяем, что массивы совпадают
        assertArrayEquals(expected, arr, "MergeSort работает неправильно!");
    }

    @Test
    void testQuickSort() {
        // создаем случайный массив
        int[] arr = Util.randomArray(1000, new Random());

        // создаем копию и сортируем стандартным методом Java
        int[] expected = arr.clone();
        Arrays.sort(expected);

        // сортируем нашим QuickSort
        Sorts.quickSort(arr);

        // проверяем, что массивы совпадают
        assertArrayEquals(expected, arr, "QuickSort работает неправильно!");
    }
}
