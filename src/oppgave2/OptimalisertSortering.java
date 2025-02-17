package oppgave2;
import java.util.Random;
import java.util.Arrays;

public class OptimalisertSortering {

	 public static void insertionSort(int[] a) {
	        int n = a.length;

	        // Finn minste element og flytt det til indeks 0
	        int minIndex = 0;
	        for (int i = 1; i < n; i++) {
	            if (a[i] < a[minIndex]) {
	                minIndex = i;
	            }
	        }
	        swap(a, minIndex, 0);

	        // Modifisert insertion sort for å sette inn to elementer om gangen
	        for (int i = 1; i < n - 1; i += 2) {
	            int min = a[i];
	            int max = a[i + 1];
	            if (min > max) {
	                int temp = min;
	                min = max;
	                max = temp;
	            }
	            
	            int j = i - 1;
	            
	            // Flytt elementer for å finne plass til max
	            while (j >= 0 && a[j] > max) {
	                a[j + 2] = a[j];
	                j--;
	            }
	            a[j + 2] = max;

	            // Finn plass for min
	            j++;
	            while (j >= 0 && a[j] > min) {
	                a[j + 1] = a[j];
	                j--;
	            }
	            a[j + 1] = min;
	        }

	        // Håndter tilfelle der antall elementer er oddetall
	        if (n % 2 == 0) return;
	        
	        int lastElement = a[n - 1];
	        int j = n - 2;
	        while (j >= 0 && a[j] > lastElement) {
	            a[j + 1] = a[j];
	            j--;
	        }
	        a[j + 1] = lastElement;
	    }

	    private static void swap(int[] a, int i, int j) {
	        int temp = a[i];
	        a[i] = a[j];
	        a[j] = temp;
	    }

	    public static void main(String[] args) {
	        int n = 500_000; // Stor nok mengde for å få målbart resultat
	        int[] data = new int[n];
	        Random rand = new Random();

	        for (int i = 0; i < n; i++) {
	            data[i] = rand.nextInt(n);
	        }

	        long startTime = System.nanoTime();
	        insertionSort(data);
	        long endTime = System.nanoTime();

	        double elapsedTime = (endTime - startTime) / 1_000_000_000.0;
	        System.out.println("Sorteringstid: " + elapsedTime + " sekunder");
	    }
	}
