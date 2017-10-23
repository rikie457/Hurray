import javax.swing.*;

/**
 * Created by Tycho on 29-3-2017.
 */
public class StartApp extends JFrame{
    public static JFrame mainframe;

    public static void main(String[] args){
        mainframe = new JFrame("Hurray!");
        mainframe.setContentPane(new MainPanel());
        mainframe.setSize(1000,1000);
        mainframe.setVisible(true);
        mainframe.setResizable(true);
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JFrame getFrame(){
        return mainframe;
    }
}
