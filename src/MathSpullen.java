import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.io.*;

/**
 * Created by Tycho on 29-3-2017.
 */
public class MathSpullen {
    private static FileWriter schrijver;
    private int array[];
    private int length;
    public double timequicksort;

    public static int[] GenereerRandomGetallen(int aantalrijen, int max, int min) {
        Random GenereertRandom = new Random();
        int[] randomGetallen = new int[aantalrijen];
        for (int i = 0; i < randomGetallen.length; ++i) {
            randomGetallen[i] = GenereertRandom.nextInt((max - min) + 1) + min;
        }
        try {
            schrijver = new FileWriter("Ongesorteerd.txt");
            for (int i = 0; i < randomGetallen.length; i++) {
                String getal = randomGetallen[i] + System.lineSeparator();
                schrijver.write(getal);
            }
            schrijver.close();
        } catch (IOException e) {
            System.out.println("Fout bij het openen, maken of sluiten bestand");
            e.printStackTrace();
        }
        System.out.println(java.util.Arrays.toString(randomGetallen));
        return randomGetallen;
    }

    public void sort(int[] inputArr) {
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;

        quickSort(0, length - 1);
        try {
            schrijver = new FileWriter("quickSortGesorteerd.txt");

            for (int x = 0; x < inputArr.length; x++) {
                String getal = inputArr[x] + System.lineSeparator();
                schrijver.write(getal);
                System.out.println(inputArr[x]);
            }
            schrijver.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void quickSort(int lowerIndex, int higherIndex) {

        long starttime = System.nanoTime();
        int i = lowerIndex;
        int j = higherIndex;

        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);

                i++;
                j--;
            }
        }

        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);


        long stoptime = System.nanoTime();
        long time = stoptime - starttime;
        double seconds = time / 1000000000.0;
        double timequicksort = seconds;
        this.timequicksort = timequicksort;

    }

    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public double getExeTime() {
        return timequicksort;
    }

}
