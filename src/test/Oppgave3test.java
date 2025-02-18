package test;

import org.junit.jupiter.api.Test;

import oppgave3.InsertionSort;
import oppgave3.MergeSort;
import oppgave3.QuickSort;
import oppgave3.SelectionSort;

import static org.junit.jupiter.api.Assertions.*;

public class Oppgave3test {
    @Test
    public void testInsertionSort() {
        Integer[] arr = {5, 2, 9, 1, 5, 6};
        InsertionSort.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    public void testSelectionSort() {
        Integer[] arr = {5, 2, 9, 1, 5, 6};
        SelectionSort.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    public void testQuickSort() {
        Integer[] arr = {5, 2, 9, 1, 5, 6};
        QuickSort.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    public void testMergeSort() {
        Integer[] arr = {5, 2, 9, 1, 5, 6};
        MergeSort.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 5, 5, 6, 9}, arr);
    }
}

