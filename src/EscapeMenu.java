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
    JFrame frame;                                               //Receives the main JFrame
    //JFrame frame2 = new JFrame("EscapeMenu");
    private BufferedImage image;                                //image stored to be painted

    public EscapeMenu(JFrame frm)
    {
        frame = frm;
    }              //Passes the frame in


    public void escape()
    {
        final JPanel glass = (JPanel) frame.getGlassPane();     //creates a panel that blocks keyclicks (mouse and keyboard) from effecting the game
        glass.setVisible(true);                                 //makes the panel visible
        glass.setLayout(new GridBagLayout());                   //creates a layout on the panel
        frame.add(glass);                                       //adds the glass panel to the frame
        frame.getContentPane().repaint();                       //repaints previous panel (which is empty)

        continueAction listener = new continueAction();         //creates object which implements the action listener
        JButton glassButton = new JButton("Continue");      //creates a new button called "Continue"
        listener.setFrame(frame);                               //sets the 1st action listener to the frame
        listener.setGlass(glass);                               //sets the 1t action listener to the glass pane
        listener.setContinue(glassButton);                      //sets the 1st action listener to the "continue" button
        glassButton.addActionListener(listener);                //adds the 1st action listener to the "continue" button
        glass.add(glassButton);                                 //adds the "continue" button to the glass pane


        exitToMenuListener menuList = new exitToMenuListener(); //creates object which implements action listener
        JButton menuButton = new JButton("Main Menu");      //creates a new button called "Main Menu"
        menuList.setFrame(frame);                               //sets the 2nd action listener to the frame
        menuList.setGlass(glass);                               //sets the 2nd action listener to the glass pane
        menuList.setMenuButton(menuButton);                     //sets the 2nd action listener to the "menu" button
        menuList.setPlay(glassButton);                          //sets the 2nd action listener to the "continue" button
        listener.setMainMenu(menuButton);                       //sets the 1st action listener to the "menu" button
        menuButton.addActionListener(menuList);                 //adds the 2nd action listener to the "menu"
        glass.add(menuButton);                                  //adds the "menu" button to the glass pane
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
