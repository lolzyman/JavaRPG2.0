import com.sun.prism.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class EscapeMenu
{
    JFrame frame;
    //JFrame frame2 = new JFrame("EscapeMenu");
    private BufferedImage image;

    public EscapeMenu(JFrame frm)
    {
        frame = frm;
    }


    public void escape()
    {
        final JPanel glass = (JPanel) frame.getGlassPane();
        glass.setVisible(true);
        glass.setLayout(new GridBagLayout());
        frame.add(glass);
        frame.getContentPane().repaint();

        continueAction listener = new continueAction();
        JButton glassButton = new JButton("Continue");
        listener.setFrame(frame);
        listener.setGlass(glass);
        listener.setContinue(glassButton);
        glassButton.addActionListener(listener);
        glass.add(glassButton);


        exitToMenuListener menuList = new exitToMenuListener();
        JButton menuButton = new JButton("Main Menu");
        menuList.setFrame(frame);
        menuList.setGlass(glass);
        menuList.setMenuButton(menuButton);
        menuList.setPlay(glassButton);
        listener.setMainMenu(menuButton);
        menuButton.addActionListener(menuList);
        glass.add(menuButton);
    }


    static class continueAction implements ActionListener
    {
        JFrame frame;
        JPanel glass;
        JButton play;
        JButton mainMenu;

        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        public void setGlass(JPanel glss)
        {
            glass = glss;
        }

        public void setContinue(JButton cont)
        {
            play = cont;
        }

        public void setMainMenu(JButton menBt)
        {
            mainMenu = menBt;
        }

        public void actionPerformed(ActionEvent e)
        {
            glass.setVisible(false);
            glass.remove(play);
            glass.remove(mainMenu);

            //Re add escape listener
            EscapeListener esc = new EscapeListener();
            frame.addKeyListener(esc);
            esc.addFrame(frame);
        }
    }


    private static class exitToMenuListener implements ActionListener
    {
        JFrame frame;
        JPanel glass;
        JButton menuButton;
        JButton play;

        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        public void setGlass(JPanel glss)
        {
            glass = glss;
        }

        public void setMenuButton(JButton menBt)
        {
            menuButton = menBt;
        }

        public void setPlay(JButton ply)
        {
            play = ply;
        }

        public void actionPerformed(ActionEvent e)
        {
            glass.setVisible(false);
            glass.remove(menuButton);
            glass.remove(play);

            //frame.getContentPane().remove(glass);

            Menu men = new Menu(frame);
            men.addButtons();



            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
        }
    }

    /*
    private static class escapeListener extends KeyAdapter
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
                frame.removeKeyListener(EscapeMenu.escapeListener.this);
                //menu.addButtons();



                EscapeMenu escapeMenu = new EscapeMenu(frame);
                escapeMenu.escape();
            }
        }
    }*/
}
