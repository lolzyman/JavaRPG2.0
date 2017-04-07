import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainClass
{
    public static void main(String args [])
    {
        int escape = KeyEvent.VK_ESCAPE;

        JFrame f = new JFrame("Java RPG");

        f.setFocusable(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //<editor-fold desc="Sets the size of the screen">
        //The Height and width values have been set so that they match the grid perfectly.
        f.setSize(502, 525);
        //</editor-fold>
        f.setResizable(false);

        //Set Location of the JFrame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);



        Menu menu = new Menu(f);
        menu.addButtons();
    }

}