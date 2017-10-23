import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tycho on 17-5-2017.
 */
public class BestandDingen {
    private static String FILENAME = "";

    public static List<String> leesBestand(int keuze) {
        String[] woord = null;
        List<String> woorden = new ArrayList<String>();

        try {
            switch (keuze) {
                case 1: FILENAME = "Ongesorteerd.txt";
                break;
                case 2: FILENAME = "bogoSort.txt";
                break;
                case 3: FILENAME = "bubbleSortGesorteerd.txt";
                break;
                case 4: FILENAME = "quickSortGesorteerd.txt";
            }
            FileInputStream fstream_school = new FileInputStream(FILENAME);
            DataInputStream data_input = new DataInputStream(fstream_school);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
            String str_line;

            while ((str_line = buffer.readLine()) != null) {
                str_line = str_line.trim();
                if ((str_line.length() != 0)) {
                    woorden.add(str_line);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return woorden;
    }
}
