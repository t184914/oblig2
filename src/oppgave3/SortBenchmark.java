package oppgave3;

import java.util.Random;

public class SortBenchmark {
	public static void main(String[] args) {
        int[] sizes = {32000, 64000, 128000};
        for (int size : sizes) {
            Integer[] arr = generateRandomArray(size);
            
            System.out.println("Sorteringsmetode: QuickSort");
            measureTime(arr.clone(), QuickSort::sort, size, "n log n");

            System.out.println("Sorteringsmetode: MergeSort");
            measureTime(arr.clone(), MergeSort::sort, size, "n log n");

            System.out.println("Sorteringsmetode: InsertionSort");
            measureTime(arr.clone(), InsertionSort::sort, size, "n^2");

            System.out.println("Sorteringsmetode: SelectionSort");
            measureTime(arr.clone(), SelectionSort::sort, size, "n^2");
        }
    }

    private static void measureTime(Integer[] arr, java.util.function.Consumer<Integer[]> sorter, int size, String complexity) {
        long startTime = System.nanoTime();
        sorter.accept(arr);
        long endTime = System.nanoTime();
        double timeInSeconds = (endTime - startTime) / 1e9;
        System.out.printf("n=%d, Tid=%.4f sekunder (%s)%n", size, timeInSeconds, complexity);
    }

    private static Integer[] generateRandomArray(int size) {
        Integer[] arr = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }
}