import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Tycho on 29-3-2017.
 */
public class MainPanel extends JPanel {
    int[] ongesorteerdearray = MathSpullen.GenereerRandomGetallen(10000, 10000, 1);
    int aantalkolommen = 10;
    JPanel knoppenpaneel;
    DefaultTableModel model = null;

    private JTable tabel;
    private static FileWriter schrijver;
    private JButton bogoSort, bubbleSort, quickSort;
    private JTextArea ongesorteerdbestand, bogosortbestand, bubblesortbestand, quicksortbestand;
    private JScrollPane tabelScroll, ongesorteerdbestandscroll, bogosortbestandscroll, bubblesortbestandscroll, quicksortbestandscroll;
    private JPanel ongesorteerdbestandpaneel, bogosortbestandpaneel, bubblesortbestandpaneel, quicksortbestandpaneel;

    public MainPanel() {
        setLayout(new GridLayout(6,0));

        DefaultTableModel model = getTableModel(ongesorteerdearray, aantalkolommen);

        tabel = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ongesorteerdbestandpaneel = new JPanel();
        ongesorteerdbestandpaneel.setLayout(new GridLayout(1,0));
        bogosortbestandpaneel = new JPanel();
        bogosortbestandpaneel.setLayout(new GridLayout(1,0));
        bubblesortbestandpaneel = new JPanel();
        bubblesortbestandpaneel.setLayout(new GridLayout(1,0));
        quicksortbestandpaneel = new JPanel();
        quicksortbestandpaneel.setLayout(new GridLayout(1,0));
        knoppenpaneel = new JPanel();
        knoppenpaneel.setLayout(new GridLayout(0,3));
        tabelScroll = new JScrollPane(tabel);
        bogoSort = new JButton("BogoSort");
        bogoSort.addActionListener(new BogoSort());
        bubbleSort = new JButton("BubbleSort");
        bubbleSort.addActionListener(new BubbleSort());
        quickSort = new JButton("QuickSort");
        quickSort.addActionListener(new QuickSort());


        BestandDingen lezen = new BestandDingen();
        ongesorteerdbestand = new JTextArea( String.join(System.lineSeparator(),lezen.leesBestand(1)));
        ongesorteerdbestand.setEditable(false);
        bogosortbestand = new JTextArea(String.join(System.lineSeparator(),lezen.leesBestand(2)));
        bogosortbestand.setEditable(false);
        bubblesortbestand = new JTextArea(String.join(System.lineSeparator(),lezen.leesBestand(3)));
        bubblesortbestand.setEditable(false);
        quicksortbestand = new JTextArea(String.join(System.lineSeparator(),lezen.leesBestand(4)));
        quicksortbestand.setEditable(false);
        ongesorteerdbestandscroll = new JScrollPane(ongesorteerdbestand);
        ongesorteerdbestandscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bogosortbestandscroll = new JScrollPane(bogosortbestand);
        bogosortbestandscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bubblesortbestandscroll = new JScrollPane(bubblesortbestand);
        bubblesortbestandscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        quicksortbestandscroll = new JScrollPane(quicksortbestand);
        quicksortbestandscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ongesorteerdbestandpaneel.setBorder(new TitledBorder(new EtchedBorder(), "Ongestorteerd"));
        ongesorteerdbestandpaneel.add(ongesorteerdbestandscroll);
        bogosortbestandpaneel.setBorder(new TitledBorder(new EtchedBorder(), "BogoSort"));
        bogosortbestandpaneel.add(bogosortbestandscroll);
        bubblesortbestandpaneel.setBorder(new TitledBorder(new EtchedBorder(), "BubbleSort"));
        bubblesortbestandpaneel.add(bubblesortbestandscroll);
        quicksortbestandpaneel.setBorder(new TitledBorder(new EtchedBorder(), "QuickSort"));
        quicksortbestandpaneel.add(quicksortbestandscroll);
        knoppenpaneel.add(bogoSort);
        knoppenpaneel.add(bubbleSort);
        knoppenpaneel.add(quickSort);
        add(tabelScroll);
        add(knoppenpaneel);
        add(ongesorteerdbestandpaneel);
        add(bogosortbestandpaneel);
        add(bubblesortbestandpaneel);
        add(quicksortbestandpaneel);



    }

    public DefaultTableModel getTableModel(int[] RandomArray, int aantalkolommen) {

        Vector<String> Kolom = new Vector<>();
        for (int h = 1; h <= aantalkolommen; h++) {
            Kolom.add("" + h);
        }
        model = new DefaultTableModel(Kolom, 0);

        Vector<Integer> Rijen = new Vector<>();
        int index = 0;
        for (int i = 0; i < RandomArray.length; i++) {
            Rijen.add(RandomArray[i]);
            if ((i + 1) % aantalkolommen == 0) {
                model.addRow(Rijen);

                Rijen = new Vector<>();

            }
            index++;
        }
        if ((index % aantalkolommen) != 0) {
            model.addRow(Rijen);
        }
        return model;
    }

    class BogoSort implements ActionListener {
        private final Random generator = new Random();

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] array = ongesorteerdearray;
            long starttime = System.nanoTime();
            while (!isSorted(array)) {


                for (int i = 0; i < array.length; i++) {
                    int randomPosition = generator.nextInt(array.length);

                    int temp = array[i];
                    array[i] = array[randomPosition];
                    array[randomPosition] = temp;
                }
            }
            BestandDingen lezen = new BestandDingen();
            StartApp SA = new StartApp();
            MainPanel MP = new MainPanel();
            model = MP.getTableModel(array, aantalkolommen);
            try {
                schrijver = new FileWriter("bogoSort.txt");

                for (int x = 0; x < array.length; x++) {
                    String getal = array[x] + System.lineSeparator();
                    schrijver.write(getal);
                    System.out.println(array[x]);
                }
                schrijver.close();
            } catch (IOException er) {
                System.out.println(er);
            }
            tabel.setModel(model);

            bogosortbestand.setText(String.join(System.lineSeparator(),lezen.leesBestand(2)));

            long stoptime = System.nanoTime();
            long time = stoptime - starttime;
            double seconds = time / 1000000000.0;
            JFrame mainframe = SA.getFrame();
            JOptionPane.showMessageDialog(mainframe, "Het duurde " + seconds + "seconden");

        }

        private boolean isSorted(int[] data) {
            for (int i = 1; i < data.length; i++)
                if (data[i] < data[i - 1])
                    return false;

            return true;
        }
    }

    class BubbleSort implements ActionListener {
        private final Random generator = new Random();

        @Override
        public void actionPerformed(ActionEvent e) {
            int n = ongesorteerdearray.length;
            long starttime = System.nanoTime();
            int temp = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j < (n - i); j++) {

                    if (ongesorteerdearray[j - 1] > ongesorteerdearray[j]) {
                        temp = ongesorteerdearray[j - 1];
                        ongesorteerdearray[j - 1] = ongesorteerdearray[j];
                        ongesorteerdearray[j] = temp;
                    }

                }
            }
            BestandDingen lezen = new BestandDingen();
            StartApp SA = new StartApp();
            MainPanel MP = new MainPanel();
            try {
                schrijver = new FileWriter("bubbleSortGesorteerd.txt");

                for (int x = 0; x < ongesorteerdearray.length; x++) {
                    String getal = ongesorteerdearray[x] + System.lineSeparator();
                    schrijver.write(getal);
                    System.out.println(ongesorteerdearray[x]);
                }
                schrijver.close();
            } catch (IOException er) {
                System.out.println(er);
            }
            model = MP.getTableModel(ongesorteerdearray, aantalkolommen);
            tabel.setModel(model);

            bubblesortbestand.setText(String.join(System.lineSeparator(),lezen.leesBestand(3)));

            long stoptime = System.nanoTime();
            long time = stoptime - starttime;
            double seconds = time / 1000000000.0;
            JFrame mainframe = SA.getFrame();
            JOptionPane.showMessageDialog(mainframe, "Het duurde " + seconds + "seconden");

        }
    }

    class QuickSort implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BestandDingen lezen = new BestandDingen();
            StartApp SA = new StartApp();
            MainPanel MP = new MainPanel();
            MathSpullen math = new MathSpullen();
            math.sort(ongesorteerdearray);
            model = MP.getTableModel(ongesorteerdearray, aantalkolommen);
            tabel.setModel(model);
            double timequicksort = math.getExeTime();
            JFrame mainframe = SA.getFrame();

            quicksortbestand.setText(String.join(System.lineSeparator(),lezen.leesBestand(4)));

            JOptionPane.showMessageDialog(mainframe, "Het duurde " + timequicksort + "seconden");
        }
    }

}

