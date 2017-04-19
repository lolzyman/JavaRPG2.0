import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainClass
{
    public static void main(String args [])
    {
        //Create JFrame
        JFrame f = new JFrame("Java RPG");

        //Set so program will exit on close
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Set frame focusable
        f.setFocusable(true);

        //<editor-fold desc="Sets the size of the screen">
        //The Height and width values have been set so that they match the grid perfectly.
        f.setSize(712, 525);
        //</editor-fold>
        f.setResizable(false);

        //Set Location of the JFrame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);


        Menu menu = new Menu(f);    //Create Menu
        menu.addMenuBackground();   //Add background to menu
        menu.addButtons();          //Add buttons to menu
    }

}