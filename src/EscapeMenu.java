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
        JFrame frame;        //Reserved for frame
        JPanel glass;        //Reserved for glass
        JButton play;        //Reserved for play button
        JButton mainMenu;    //Reserved for mainMenu

        /**
        Setter for the frame
         */
        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        /**
        Setter for the glass
         */
        public void setGlass(JPanel glss)
        {
            glass = glss;
        }

        /**
        Setter for the continue button
         */
        public void setContinue(JButton cont)
        {
            play = cont;
        }

        /**
        Setter for the main menu button
         */
        public void setMainMenu(JButton menBt)
        {
            mainMenu = menBt;
        }

        /**
        Action performed for continue button
         */
        public void actionPerformed(ActionEvent e)
        {
            glass.setVisible(false);   //Set the glass pane invisible
            glass.remove(play);        //Remove the play button
            glass.remove(mainMenu);    //Remove the main menu button

            EscapeListener esc = new EscapeListener();  //Create escape listener
            frame.addKeyListener(esc);                  //Add key listener for escape button
            esc.addFrame(frame);                        //Add listener to the frame
        }
    }


    private static class exitToMenuListener implements ActionListener
    {
        JFrame frame;           //Reserved for frame
        JPanel glass;           //Reserved for glass panel
        JButton menuButton;     //reserved for menuButton
        JButton play;           //Reserved for play button

        /**
        Setter for frame
         */
        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        /**
        Setter for glass pane
         */
        public void setGlass(JPanel glss)
        {
            glass = glss;
        }

        /**
        Setter for menu button
         */
        public void setMenuButton(JButton menBt)
        {
            menuButton = menBt;
        }

        /**
        Setter for play button
         */
        public void setPlay(JButton ply)
        {
            play = ply;
        }

        /**
        Action performed
         */
        public void actionPerformed(ActionEvent e)
        {
            glass.setVisible(false);   //Set the glass invisible
            glass.remove(menuButton);  //Remove the menu button
            glass.remove(play);        //Remove the play button

            //frame.getContentPane().remove(glass);

            Menu men = new Menu(frame); //Create menu and send frame
            men.addButtons();           //Add buttons to the menu

            frame.getContentPane().revalidate();  //Revalidate the content pane
            frame.getContentPane().repaint();     //Repaint the content pane
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
