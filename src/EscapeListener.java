import java.awt.event.KeyAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;


public class EscapeListener extends KeyAdapter
{
    JFrame frame;    //Reserve space for the frame

    /**
    Empty Constructor
     */
    public EscapeListener()
    {

    }

    /**
    Setter for frame
     */
    public void addFrame(JFrame frm)
    {
        frame = frm;   //Set frame to the frame that was sent
    }

    /**
    Method if key pressed
     */
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();  //Get key code of key pressed and set to keyCode

        //If statment for if the escape key is pressed
        if (keyCode == e.VK_ESCAPE)
        {
            //Remove the listener for the escape key
            frame.removeKeyListener(EscapeListener.this);

            //Test message to say the escape listener is working
            System.out.println("Escape Listener Working");

            EscapeMenu escapeMenu = new EscapeMenu(frame);  //Create escape menu and send frame to it
            escapeMenu.escape();                            //Call escape method
        }
    }
}