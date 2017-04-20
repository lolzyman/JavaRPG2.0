/**
 * Created by Mark on 3/18/2017.
 */
public class Coin extends Object{
    //value is the value of the coin
    //index is the unique id for the coin.
    private int value,
            index;
        public Coin(int gx, int gy, int in, int value){
            // sets the value of the coin, its positions, the image, as well as its id.
            this.value = value;
            setGridx(gx);
            setGridy(gy);
            updatePosOnGrid();
            setImage("nonClass/Coin.png");
            index = in;
    }
    //Getters and Setters for the class//
    public void setValue(int v){
        value = v;
    }
    public int getValue(){
        return value;
    }
    public int getIndex(){
        return index;
    }
}
