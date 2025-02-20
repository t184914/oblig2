package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oppgave3.SortBenchmark;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class Oppgave3test {
	  private Integer[] unsortedArray;
	    private Integer[] expectedSortedArray;

	    @BeforeEach
	    void setUp() {
	        unsortedArray = generateRandomArray(100);
	        expectedSortedArray = unsortedArray.clone();
	        Arrays.sort(expectedSortedArray);
	    }

	    @Test
	    void testInsertionSort() {
	        Integer[] array = unsortedArray.clone();
	        SortBenchmark.insertionSort(array);
	        assertArrayEquals(expectedSortedArray, array, "Insertion Sort failed");
	    }

	    @Test
	    void testSelectionSort() {
	        Integer[] array = unsortedArray.clone();
	        SortBenchmark.selectionSort(array);
	        assertArrayEquals(expectedSortedArray, array, "Selection Sort failed");
	    }

	    @Test
	    void testQuickSort() {
	        Integer[] array = unsortedArray.clone();
	        SortBenchmark.quickSort(array);
	        assertArrayEquals(expectedSortedArray, array, "Quick Sort failed");
	    }

	    @Test
	    void testMergeSort() {
	        Integer[] array = unsortedArray.clone();
	        SortBenchmark.mergeSort(array);
	        assertArrayEquals(expectedSortedArray, array, "Merge Sort failed");
	    }

	    private Integer[] generateRandomArray(int size) {
	        Random rand = new Random();
	        Integer[] array = new Integer[size];
	        for (int i = 0; i < size; i++) {
	            array[i] = rand.nextInt(1000);
	        }
	        return array;
	    }
	}

