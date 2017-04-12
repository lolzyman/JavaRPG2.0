import java.awt.event.KeyAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;


public class EscapeListener extends KeyAdapter
{
    JFrame frame;

    public void addFrame(JFrame frm)
    {
        frame = frm;
    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == e.VK_ESCAPE)
        {
            //Menu menu = new Menu(frame);
            frame.removeKeyListener(EscapeListener.this);
            //menu.addButtons();

            System.out.println("Escape Listener Working");

            EscapeMenu escapeMenu = new EscapeMenu(frame);
            escapeMenu.escape();
        }
    }
}
