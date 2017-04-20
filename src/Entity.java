/**
 * Created by Mark on 3/19/2017.
 */
//<editor-fold desc="Documentation">
//Class: Entity        Extends Object
    //<editor-fold desc="Variables:">
        /*inGrid:
            Value Meanings:
                True:   The Entity is in a grid spot and can change directions or start moving.
                False:  The Entity is currently moving and the direction can't be changed.
            Purpose:
                Needed to handle the movement of the Player. Checks to see if the Entity is occupying a grid spot.
        buffDir:
            Value Meanings:
                -1: There is no preferred direction.
                0:  The Controller for the Entity wants the entity to move upwards.
                1:  The Controller for the Entity wants the entity to move to the right.
                2:  The Controller for the Entity wants the entity to move downwards.
                3:  The Controller for the Entity wants the entity to move to the left.
            Purpose:
                Used to Store the direction the controller wants it to move while the Entity is moving. This necessary
                to prevent the player from turning when not in a grid position, and allows for PacMan like movement.
        health:
            Value Meanings:
                0>: The Entity is classified as dead.
                1<: The Entity is classified as alive.
            Purpose:
                Used to store the health of the Entity.
        agility:
            Value Meanings:
                The higher the value the quicker the Entity can attack. The higher value deals damage first.
        power:
            Value Meanings:
                The value directly corresponds to the damage that can be done by the Entity.
        speed:
            Value Meanings:
                The value is used to change the position of the Entity. This corresponds to how quickly the
                the Entity can move from grid position to grid position
        dir:
            Value Meanings:
                -1: The Entity isn't moving.
                0:  The Entity is moving upwards.
                1:  The Entity is moving to the right.
                2:  The Entity is moving downwards.
                3:  The Entity is moving to the left.
            Purpose:
                Handles the direction for the Entity's movement.
        map:
            Value Meanings:
                three dimensional integer array. First dimension is the y grid coordinate for the Entity.
                Second dimension is for the x grid coordinate.
                The Third dimension if various data in the array;
                    0:  Can or cannot walk for Entities
                    1:
                    2:  Mobs on the map
                    3:  Coins on the map
                    4:
                    5:
                    6:
                    7:
    *///</editor-fold>
    //<editor-fold desc="Methods">
        /*Entity(int xx, int yy, int hearts, int agl, int spe, int pow):
            Type:
                Constructor
            Desc:
                The constructor sets the following values:
                gridx of the superclass to xx.
                gridy of the superclass to yy.
                x of the superclass to xx * 16.
                y of the superclass to yy * 16.
                health to hearts.
                agility to agl.
                speed to spe.
                power to pow.
        Entity():
            Type:
                Constructor
            Desc:
                This Method does nothing.
        isInGrid():
            Type:
                Boolean - Getter
            Desc:
                Returns true if the position of the Entity is in a grid position.
        updateDir():
            Type:
                void
            Desc:
                changes the value of dir to buffDir iff the Entity is in a grid position.
        updateGridPos():
            Type:
                void
            Desc:
                Changes the gird position of the Entity as soon as the character starts moving towards its target
        move():
            Type:
                void
            Desc:
                Changes the x or y variable of the superclass based on the dir value.
        isClear():
            Type:
                Boolean
            Desc:
                Returns true if the target grid position is walkable.
        setAgility(int agility):
            Type:
                void - setter
            Desc:
                Sets the agility value to the input agility.
        setPower(int power):
            Type:
                void - setter
            Desc:
                Sets the power value to the input power.
        setSpeed(int speed):
            Type:
                void - setter
            Desc:
                Sets the speed value to the input speed.
        setDir(int dir):
            Type:
                void - setter
            Desc:
                Sets the dir value to the input dir.
        getBuffDir():
            Type:
                int - getter
            Desc:
                Returns the value of buffDir.
        getHealth():
            Type:
                int - getter
            Desc:
                Returns the value of health;
        getAgility():
            Type:
                int - getter
            Desc:
                Returns the value of agility
        getPower():
            Type:
                int - getter
            Desc:
                Returns the value of power
        getSpeed():
            Type:
                int - getter
            Desc:
                Returns the value of speed
        getDir():
            Type:
                int - getter
            Desc:
                Returns the value of dir
        setMap(int[][][] map):
            Type:
                void - setter
            Desc:
                Sets the address of Map to the input address Map
        getMap():
            Type:
                int[][][] - getter
            Desc:
                Returns the address of Map
        setBuffDir(int dir):
            Type:
                void - setter
            Desc:
                Sets the value of buffDir to the value of input dir
        setHealth(int hearts):
            Type:
                void - setter
            Desc:
                Sets the value of health to the input hearts
        update():
            Type:
                void
            Desc:
                Calls the move method, the updateDir method, and the updateGridPos method
        isDeath():
            Type:
                boolean
            Desc:
                Returns true if the Entity's health is less than zero.
*/
//</editor-fold>
//</editor-fold>
public class Entity extends Object{

    private boolean inGrid = true;                                              //inGird Handles if the Entity is in the a grid position or if it is moving or not
    private int buffDir,                                                        //Buff dir is the direction the player wants to move. it gets changed to dir only when the player is in a grid position
            health,     //The health of the entity - for later implementations
            agility,    //The attack speed of the Entity - this decides weather a monster or the player attacks first.
            power,      //The attack power of the Entity -  ho
            speed,
            dir = -1;
    private int[][][] map;
    public Entity(int xx, int yy, int hearts, int agl, int spe, int pow) {
        setGridx(xx);
        setGridy(yy);
        setX(xx * 16);
        setY(yy * 16);

        //Takes the input from the constructor and populates the integers
        speed = spe;
        agility = agl;
        health = hearts;
        power = pow;
    }
    public Entity(){

    }
    public boolean isInGrid(){
        return inGrid;
    }
    public void updateDir(){
        if((getX() % 16 == 0) && (getY() % 16 == 0)){
            inGrid = true;
            dir = buffDir;
        }else{
            inGrid = false;
        }
    }
    public void updateGridPos(){
        //changes the current gird position to the grid position that the character is moving to
        switch(dir){
            case 0:
                //sets the target grid position to the one above
                setGridy((int)Math.floor((double)getY()/16));
                break;
            case 1:
                //sets the target grid position to the one to the right
                setGridx((int)Math.ceil((double)getX()/16));
                break;
            case 2:
                //sets the target grid position to the one below
                setGridy((int)Math.ceil((double)getY()/16));
                break;
            case 3:
                //sets the target grid position to the one to the left
                setGridx((int)Math.floor((double)getX()/16));
                break;
            default:
                break;
        }
    }
    public void move(){
        //the first if statement hands when the character is in mid-step
        //the second if statement checks to see if the block is open to walk when the player is inGrid
        if(!inGrid || (inGrid && isClear())){
            switch(dir){
                case -1:
                    //Character doesn't move
                    break;
                case 0:
                    //Character moves upward
                    setY(getY() - speed);
                    break;
                case 1:
                    //Character moves right
                    setX(getX() + speed);
                    break;
                case 2:
                    //Character moves down
                    setY(getY() + speed);
                    break;
                case 3:
                    //Character moves left
                    setX(getX() - speed);
                    break;
            }
        }
    }
    public boolean isClear(){
        //Further adaptation needs to be done to take into account the size of the image
        //the character and most mobs will be one block size, but bosses and mini-bosses will be larger
        //and it would be convenient to have them run through the same superclass
        switch(dir){
            case 0:
                //there is an offset because of the pictures height. this needs to be fixed ASAP.
                //this goes for all the comparison statements in this method
                return (map[getGridy() - 1][getGridx()][0] == 0);
            case 1:
                return (map[getGridy()][getGridx() + 1][0] == 0);
            case 2:
                return (map[getGridy() + 1][getGridx()][0] == 0);
            case 3:
                return (map[getGridy()][getGridx() - 1][0] == 0);
        }
        return false;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDir(int dir) {
        this.dir = dir;
    }
    public int getBuffDir() {
        return buffDir;
    }
    public int getHealth() {
        return health;
    }
    public int getAgility() {
        return agility;
    }
    public int getPower() {
        return power;
    }
    public int getSpeed() {
        return speed;
    }
    public int getDir() {
        return dir;
    }
    public void setMap(int[][][] map) {
        this.map = map;
    }
    public int[][][]getMap(){
        return map;
    }
    public void setBuffDir(int dir) {
        this.buffDir = dir;
    }
    public void setHealth(int hearts){
        health = hearts;
    }
    public void update(){
        move();
        updateDir();
        updateGridPos();
    }
    public boolean isDeath(){
        if(health <= 0){
            return true;
        }
        return false;
    }
}
