import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Mark on 3/19/2017.
 */
public class Object{
    /*
     * variables:
     *      image object
     *      x and y positions
     *      the grid positions
     */
    private BufferedImage image;
    private int x = 0;
    private int y = 0;
    private int gridx = 0;
    private int gridy = 0;
    //Constructor
    public Object(){

    }
    //*******************//
    //Getters And Setters//
    //*******************//
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(String location) {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(location));
        }
        catch(IOException e)
        {
            //interrupt statement if the image can't be found
            //contingency plans need to be put here
            e.printStackTrace();
        }
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getGridx() {
        return gridx;
    }
    public void setGridx(int gridx) {
        this.gridx = gridx;
    }
    public int getGridy() {
        return gridy;
    }
    public void setGridy(int gridy) {
        this.gridy = gridy;
    }
    //Method used when creating the class to set the pixel location based on the grid position.
    public void updatePosOnGrid(){
        x = gridx*16;
        y = gridy*16;
    }
}
