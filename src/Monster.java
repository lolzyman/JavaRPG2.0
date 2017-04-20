/**
 * Created by Mark on 3/19/2017.
 */
public class Monster extends Entity{

    // index variable is an identifier for the game dynamics. So the Game machine can find it in the stack
    // monsterType is a method for using prefabs for later game development
    private int index, monsterType;
    //Constructor
    //Assigns the gird positions, the type of monster and its index variable
    public Monster(int gx, int gy, int type, int id){
        index = id;
        setGridx(gx);
        setGridy(gy);
        monsterType = type;
        // Switch statement handles the prefab selection. An additional case can be added for a different monster type.
        switch (type) {
            case 0:
                setImage("/nonClass/EnemyLevel1.png");
                setPower(0);
                setAgility(0);
                setSpeed(0);
                setHealth(1);
                break;
            case 1:
                setImage("/nonClass/EnemyLevel1.png");
                setPower(1);
                setAgility(2);
                setSpeed(0);
                setHealth(1);
                break;
            case 2:
                setImage("/nonClass/EnemyLevel2.png");
                setPower(2);
                setAgility(2);
                setSpeed(0);
                setHealth(1);
                break;
        }
    }
    //Getter, for when the World class is searching for a monster
    public int getId(){
        return index;
    }
    //Method for dropping loot on the Map. Current Implementation isn't working.
    //The Method is trying to drop coins when the monster dies for the player to collect.
    public void dropItems(int[][][] mapGrid, int coinId, MultiPurposeStack apple){
        int meta = mapGrid[getGridy()][getGridx()][3];
        if(meta == 0) {
            System.out.println("Here");
            Coin coin = new Coin(getGridx(), getGridy(), coinId, monsterType + 2);
            apple.addEnd(coin);
            mapGrid[getGridy()][getGridx()][3] = coinId;
        }else{
            Coin coin = apple.getNodeWithValue(mapGrid[getGridy()][getGridx()][3]).getCoinValue();
            System.out.println("Here2");
            coin.setValue(coin.getValue() + monsterType + 2);
        }
    }
}
