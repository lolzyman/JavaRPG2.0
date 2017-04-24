import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends JPanel
{
    JFrame frame;                   //Space for JFrame
    private BufferedImage image;    //Reserve space for image

    /************************************
    Constructor that receives the JFrame
     ************************************/
    public Menu(JFrame frm)
    {
        frame = frm;  //Set the frame
    }


    /********************************
    Method to set the menu background
    ********************************/
    public void addMenuBackground()
    {
        //The following gets the image for the background
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("nonClass/Cover_Girl.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //Test message to declare that this method was successfully used
        System.out.println("Image Added");


        //Paint Background image
        frame.setContentPane(new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 712, 512, this);
            }
        });
    }

    /**************************************
    //Method to add the buttons to the menu
     *************************************/
    public void addButtons()
    {
        //Set so buttons are aligned on y axis
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        /*********************************
        Position the Buttons on the Screen
        *********************************/
        Dimension minSize = new Dimension(15,375);              //Set the min size
        Dimension prefSize = new Dimension(15,375);             //Set the preferred size
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 375);       //Set the max size
        Component customFiller = new Box.Filler(minSize, prefSize, maxSize);  //Create a custom filler to position buttons
        frame.getContentPane().add(customFiller);                             //Add the filler to the content pane

        /**************
        Add Play Button
        **************/
        playButtonAction plyBttAct = new playButtonAction();                                                                 //Create Play Button Action Listener
        JButton playButton = new JButton(null, new ImageIcon(Menu.class.getResource("nonClass/button-6.png")));  //Create new Button and add play button image to it
        playButton.setOpaque(false);                                                                                         //Set Button Transparent
        playButton.setContentAreaFilled(false);                                                                              //Clear content of button
        playButton.setBorderPainted(false);                                                                                  //Set the border of the button transparent
        plyBttAct.setFrame(frame);                                                                                           //Send the frame to the setter in the action listener
        frame.getContentPane().add(playButton);                                                                              //Add the play button to the content pane
        playButton.addActionListener(plyBttAct);                                                                             //Add action listener to the button

        /*****************
        Add Credits Button
        *****************/
        creditButtonAction crdtBttAct = new creditButtonAction();                                                              //Create Credit Button Action Listener
        JButton creditButton = new JButton(null, new ImageIcon(Menu.class.getResource("nonClass/button-7.png")));  //Create new Button and add credit button image to it
        creditButton.setOpaque(false);                                                                                         //Set Button Transparent
        creditButton.setContentAreaFilled(false);                                                                              //Clear content of button
        creditButton.setBorderPainted(false);                                                                                  //Set the border of the button transparent
        crdtBttAct.setFrame(frame);                                                                                            //Send the frame to the setter in the action listener
        frame.getContentPane().add(creditButton);                                                                              //Add the credit button to the content pane
        creditButton.addActionListener(crdtBttAct);                                                                            //Add action listener to the button


        /**************
        Add Quit Button
        **************/
        JButton quitButton = new JButton(null, new ImageIcon(Menu.class.getResource("nonClass/button-8.png")));   //Create new Button and add quit button image to it
        quitButton.setOpaque(false);                                                                                          //Set Button Transparent
        quitButton.setContentAreaFilled(false);                                                                               //Clear content of button
        quitButton.setBorderPainted(false);                                                                                   //Set the border of the button transparent
        frame.getContentPane().add(quitButton);                                                                               //Add quite button to the content pane
        quitButton.addActionListener(new quitButtonAction());                                                                 //Add quite action listener to the button

        frame.setVisible(true);  //Set the frame visible
    }

    /*
    protected static ImageIcon createImageIcon(String path)
    {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/

    /********************************************
    Class for action listener for the play button
     *******************************************/
    static class playButtonAction implements ActionListener
    {
        JFrame frame;   //Reserved for the JFrame

        /**
        Setter for the frame
         */
        public void setFrame(JFrame frm)
        {
            frame = frm;  //Set the frame to the frame sent to the setter
        }

        /**
        Action Performed for the play button
         */
        public void actionPerformed(ActionEvent e)
        {
            frame.getContentPane().removeAll();  //Remove everything from the content pane

            World w = new World(frame);          //Create World (Send the frame to world)
            frame.getContentPane().add(w);       //Add world to the frame

            EscapeListener esc = new EscapeListener();   //Create a new listener for the escape key
            esc.addFrame(frame);                         //Send the frame to the escape listeners setter
            frame.addKeyListener(esc);                   //Add the escape listener to the frame

            frame.getContentPane().revalidate();        //Revalidate the content pane
            frame.getContentPane().repaint();           //Repaint the content pane
        }
    }

    /**********************************************
    Class for action listener for the credit button
     *********************************************/
    static class creditButtonAction extends JPanel implements ActionListener
    {
        JFrame frame;   //Space reserved for the frame

        /**
        Setter for the JFrame
         */
        public void setFrame(JFrame frm)
        {
            frame = frm;     //Set frame to the frame sent through the setter
        }

        /*
        protected static ImageIcon createImageIcon(String path)
        {
            java.net.URL imgURL = Menu.class.getResource(path);
            if (imgURL != null)
            {
                return new ImageIcon(imgURL);
            }
            else
            {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        }*/

        /**
        Action Performed for the credit button
         */
        public void actionPerformed(ActionEvent e)
        {
            //Clear Components from frame
            frame.getContentPane().removeAll();

            Component rigidArea = Box.createRigidArea(new Dimension(150,100));  //Create a new rigid area
            frame.getContentPane().add(rigidArea);                                            //Add rigid area to the content pane

            /**************
            Add Back Button
            **************/
            backButtonAction bckBttAct = new backButtonAction();                                                                  //Create Back Button Action Listener
            JButton backButton = new JButton(null, new ImageIcon(Menu.class.getResource("nonClass/button-9.png")));   //Create new Button and add back button image to it
            backButton.setOpaque(false);                                                                                          //Set Button Transparent
            backButton.setContentAreaFilled(false);                                                                               //Clear content of button
            backButton.setBorderPainted(false);                                                                                   //Set the border of the button transparent
            bckBttAct.setFrame(frame);                                                                                            //Send the frame to the setter in the action listener
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);                                                                 //Set the the button to be center aligned on the x axis
            frame.getContentPane().add(backButton);                                                                               //Add the back button to the frame
            backButton.addActionListener(bckBttAct);                                                                              //Add action listener to the button


            Component rigidArea2 = Box.createRigidArea(new Dimension(150,10));  //Create a second rigid area
            frame.getContentPane().add(rigidArea2);                                           //Add rigid area to the content pane


            //Add Credits Text
            JLabel text = new JLabel("Credits");       //Create new text that says Credits
            text.setAlignmentX(Component.CENTER_ALIGNMENT); //Set alignment of the text
            frame.getContentPane().add(text);               //Add text to content pane

            frame.getContentPane().revalidate();  //Revalidate content pane
            frame.getContentPane().repaint();     //Repaint the content pane
        }
    }

    /********************************************
    Class for action listener for the quit button
     *******************************************/
    static class quitButtonAction implements ActionListener
    {
        //Action Performed Listener
        public void actionPerformed(ActionEvent e)
        {
            //Exit program
            System.exit(0);
        }
    }

    /********************************************
    Class for action listener for the back button
     *******************************************/
    static class backButtonAction implements ActionListener
    {
        JFrame frame;  //Reserve space for the frame


        /**
        Setter for the frame
         */
        public void setFrame(JFrame frm)
        {
            frame = frm;  //Set frame to the frame that was sent
        }

        /**
        Action Performed method
         */
        public void actionPerformed(ActionEvent e)
        {
            //Clear Components from frame
            frame.getContentPane().removeAll();

            Menu mens = new Menu(frame);  //Create menu
            mens.addButtons();            //Add buttons to menu

            frame.getContentPane().revalidate();  //Revalidate content pane
            frame.getContentPane().repaint();     //Repaint content pane
        }
    }
}