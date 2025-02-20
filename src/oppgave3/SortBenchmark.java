package oppgave3;

import java.util.Random;

public class SortBenchmark {

    public static void main(String[] args) {
        int[] sizes = {32000, 64000, 128000};
   
        
        benchmarkSort("Insertion Sort", sizes, SortBenchmark::insertionSort, n -> (double) (n * n));
        benchmarkSort("Selection Sort", sizes, SortBenchmark::selectionSort, n -> (double) (n * n));
        benchmarkSort("QuickSort", sizes, SortBenchmark::quickSort, n -> n * (Math.log(n) / Math.log(2)));
        benchmarkSort("MergeSort", sizes, SortBenchmark::mergeSort, n -> n * (Math.log(n) / Math.log(2)));
    }

    @FunctionalInterface
    interface SortingAlgorithm {
        void sort(Integer[] array);
    }

    @FunctionalInterface
    interface ComplexityFunction {
        double compute(int n);
    }

    public static void benchmarkSort(String name, int[] sizes, SortingAlgorithm algorithm, ComplexityFunction complexity) {
        System.out.println("\n" + name);
        System.out.println("N\tMålt tid (s)\tTeoretisk tid (s)");

        double c = -1;  // Start med en usannsynlig verdi for å fange feil
        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];

            // Sjekk at n er gyldig (større enn 0)
            if (n <= 0) {
                System.out.println("Feil: n må være større enn 0. Hopper over n = " + n);
                continue;
            }

            Integer[] array = generateRandomArray(n);

            long startTime = System.nanoTime();
            algorithm.sort(array);
            long endTime = System.nanoTime();

            double elapsedTime = (endTime - startTime) / 1e9; // Konverter til sekunder
            double f_n = complexity.compute(n); // Beregn f(n)

            // Sjekk at f_n er gyldig (større enn 0)
            if (f_n <= 0) {
                System.out.println("Feil: f(n) ble null eller negativ for n = " + n + ". Hopper over denne målingen.");
                continue;
            }

            if (i == 0) {  // Beregn c fra første måling
                c = elapsedTime / f_n;
                // Sjekk at c er gyldig (større enn 0)
                if (c <= 0) {
                    System.out.println("Feil: c ble negativ eller null! Bruker standardverdi c = 1.");
                    c = 1;
                }
            }

            double theoreticalTime = c * f_n;
            System.out.printf("%d\t%.6f\t%.6f%n", n, elapsedTime, theoreticalTime);
        }
    }
    public static Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100000);
        }
        return array;
    }

    public static void insertionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void selectionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void quickSort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void mergeSort(Integer[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Integer[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Integer[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Integer[] leftArr = new Integer[n1];
        Integer[] rightArr = new Integer[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }
}