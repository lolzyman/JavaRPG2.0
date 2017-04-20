public class Character extends Entity{

    //holds the world class so that we can update the map relative to the screen
	private World environment;
	//How far the player can see. This is needed for moving the screen around and eventually fog clearing
	private int sightRange = 0;
	//The constructor for the Class. assigns the image, the environment variable, the gird positions, the sight range
    // and the basic entity variables.
    // calls the method to update the x and y position for the Entity.
	public Character(String image, World planet, int gridx, int gridy, int health, int speed, int agility, int power, int sight){
        environment = planet;
        sightRange = sight;
        setImage(image);
        setGridx(gridx);
        setGridy(gridy);
        setHealth(health);
        setSpeed(speed);
        setAgility(agility);
        setPower(power);
        updatePosOnGrid();
    }
    //Checks to see if the position that it is advancing upon contains a coin. will need future work to add the
    //coin value to the players purse
	public void checkCoins(){
		if(getMap()[getGridy()][getGridx()][3] != 0) {
			if(environment.coins.deleteCoinIndex(getMap()[getGridy()][getGridx()][3])){
                getMap()[getGridy()][getGridx()][3] = 0;
			}
		}
	}
	//Method that handles attacking monsters
	public void attackMonster(){
	    //checks to see if there is a monster to attack.
	    if(getMap()[getGridy()][getGridx()][2] != 0){
	        //called if there is a monster to attack
            attack(environment.mobs.getNodeWithValue(getMap()[getGridy()][getGridx()][2]).getMonsterValue());
            //this switch statement is here to push the player back, like there is knockback.
            switch (getDir()){
                case 0:
                    setY(getY() + 16);
                    break;
                case 1:
                    setX(getX() - 16);
                    break;
                case 2:
                    setY(getY() - 16);
                    break;
                case 3:
                    setX(getX() + 16);
                    break;
                default:
                    System.out.print("This should never ever run");
                    break;
            }
        }
    }
	@Override
    public void update(){
	    //adds 3 methods to the class.
        //Adjusts the map position relative to the screen
        //checks for coins, and checks for monsters
	    super.update();
	    environment.updateOffset();
	    checkCoins();
	    attackMonster();
    }
    //Handles the attacking for the monsters
    public void attack(Monster monster) {
        //Compares the agility values of each of the Entities, and allows the quicker one to attack first
        //If the first attack kills the other, the quicker one doesn't receive any damage.
        //If them agility values are the same, then both Entities are dealt damage, regardless of if it kills anyone
        if (monster.getAgility() > getAgility()) {
            setHealth(getHealth()- monster.getPower());
            if(!isDeath()) {
                monster.setHealth(monster.getHealth() - getPower());
            }
        }else if(monster.getAgility() < getAgility()){
            monster.setHealth(monster.getHealth() - getPower());
            if(!monster.isDeath()) {
                setHealth(getHealth() - monster.getPower());
            }
        }else{
            monster.setHealth(monster.getHealth() - getPower());
            setHealth(getHealth()- monster.getPower());
        }
    }
    //Getter for sight range
    public int getSightRange(){
        return sightRange;
    }
}
