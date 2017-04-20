import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel implements ActionListener
{
    //Time delay for the timer class
	int timerSpeed = 5;
	//Timer for the action listener
    Timer t = new Timer(timerSpeed, this);
    /**
     * Item Arrays for the map. Contains:
     * mapGrid: Data For the Map
     * coins: Loose Coins on the map that can be collected
     * mobs: monsters on the map.
     */
    //Array that contains all the information on the map.
    int[][][] mapGrid;
    // Stacks for handling all the coins and the arrays.
    // Also has the index values for them
    MultiPurposeStack   coins = new MultiPurposeStack(),
                        mobs = new MultiPurposeStack();
    int                 coinId = 1,
                        mobsId = 1;
    //Class for handling the background image
    BackgroundImage back = new BackgroundImage();
    //Class for handling the character
    Character me;
    //Loads all the information of the map stored in the text file.
    LoadMap map = new LoadMap("nonClass/map2.txt");
    //Stack for handling the WASD key inputs
    MultiPurposeStack keyArrayWASD;
    //Class for handling all KeyListeners
    MasterKeyListener master;
    // Offset for drawing the world. In pixels and allows the movement of the map as the character gets close to the edge
    int masterOffsetx, masterOffsety;
    //frame containing the world?
    JFrame frame;
    //Constructor.
    //Handles the importing of map details, character position, mob locations, coin location.
    public World(JFrame frm){
        setFocusTraversalKeysEnabled(false);
        frame = frm;
        ImportCSV importer = new ImportCSV();
        mapGrid = importer.importArray("nonClass/map2.csv");
        int midabcde[][] = map.getCoins();
        for(int i = 0; i < midabcde.length; i++){
            addCoin(midabcde[i][0],midabcde[i][1],midabcde[i][2]);
        }
        midabcde = map.getMobs();
        for(int i = 0; i < midabcde.length; i++){
            addMob(midabcde[i][0],midabcde[i][1],midabcde[i][2]);
        }
        spawnPlayer();
        master = new MasterKeyListener(this);
        keyArrayWASD = master.getKeyArrayWASD();
        t.start();
        back.setImage("nonClass/map2.png");
    }
    //Updates the offset for drawing of the world based on the character positions.
    public void updateOffset(){
        int mex = me.getX(), mey = me.getY(), sightRange = me.getSightRange() * 16;
        int x = mex - masterOffsetx, y = mey - masterOffsety;
        int mapSizeX = (mapGrid[0].length - 1)* 16, mapSizeY = (mapGrid.length - 1) * 16;
        if(x < sightRange){
            masterOffsetx += x - sightRange;
            if(masterOffsetx < 0){
                masterOffsetx = 0;
            }
        }else if(x > 480 - sightRange){
            masterOffsetx -= 480 - x - sightRange;
            if(masterOffsetx > mapSizeX - 480){
                masterOffsetx = mapSizeX - 480;
            }
        }
        if(y < sightRange){
            masterOffsety += y - sightRange;
            if(masterOffsety < 0){
                masterOffsety = 0;
            }
        }else if(y > 480 - sightRange) {
            masterOffsety -= 480 - y - sightRange;
            if(masterOffsety > mapSizeY - 480){
                masterOffsety = mapSizeY - 480;
            }
        }
    }
    public void paint(Graphics g){
    	super.paint(g);
    	//Sets the background color
    	setBackground(Color.BLACK);
    	//Draws the background
        g.drawImage(back.getImage(), back.getX() - masterOffsetx, back.getY() - masterOffsety, null);
        Node inQuestion = coins.getHead();
        //Draws all the coins
        while(inQuestion != coins.getFoot()){
            g.drawImage(inQuestion.getCoinValue().getImage(), inQuestion.getCoinValue().getX() - masterOffsetx, inQuestion.getCoinValue().getY() - masterOffsety, null);
            inQuestion = inQuestion.getChild();
        }
        if(inQuestion != null)
        g.drawImage(inQuestion.getCoinValue().getImage(), inQuestion.getCoinValue().getX() - masterOffsetx, inQuestion.getCoinValue().getY() - masterOffsety, null);
        inQuestion = mobs.getHead();
        //Draws all the monsters
        while(inQuestion != mobs.getFoot()){
            g.drawImage(inQuestion.getMonsterValue().getImage(), inQuestion.getMonsterValue().getX() - masterOffsetx, inQuestion.getMonsterValue().getY() - masterOffsety, null);
            inQuestion = inQuestion.getChild();
        }
        if(inQuestion != null)
            g.drawImage(inQuestion.getMonsterValue().getImage(), inQuestion.getMonsterValue().getX() - masterOffsetx, inQuestion.getMonsterValue().getY() - masterOffsety, null);
        //Draws the charater
        g.drawImage(me.getImage(),me.getX() - masterOffsetx,me.getY() - masterOffsety,null);
    }
    //Used as our run method., Called every cycle.
    public void actionPerformed(ActionEvent e){
    	//checks to see if there are any keys that in the linked-list keyArrayWASD
        /**
         * move the code after this into the Character Class
         */
    	if(!keyArrayWASD.isEmpty()){
    		//switch statement gets the last key that was pressed and passes
    		//the direction info to the Character
    		switch(keyArrayWASD.getHead().getIntValue()){
    		case 0:
    			me.setBuffDir(0);
    			break;
    		case 1:
    			me.setBuffDir(1);
    			break;
    		case 2:
    			me.setBuffDir(2);
    			break;
    		case 3:
    			me.setBuffDir(3);
    			break;
    		default:
    			me.setBuffDir(-1);
    		}
    	//Default case that clears the direction if the linked-list is empty
    	}else if(keyArrayWASD.isEmpty()){
			me.setBuffDir(-1);
		}
        checkDeath();
    	repaint();
    }
    //Method for adding coins to the world.
    public void addCoin(int gx, int gy, int value){
        Coin apple = new Coin(gx, gy, coinId, value);
        mapGrid[gy][gx][3] = coinId;
        coins.addEnd(apple);
        coinId++;
    }
    //Method for adding monsters to the class
    public void addMob(int gx, int gy, int type){
        Monster monster = new Monster(gx, gy, type, mobsId);
        mapGrid[gy][gx][2] = mobsId;
        monster.updatePosOnGrid();
        mobs.addBeginning(monster);
        mobsId++;
    }
    //spawns the player at the location loaded by the class
    public void spawnPlayer(){
        int[] charPos = map.getCharPos();
        me = new Character("nonClass/character.png",this, charPos[0], charPos[1], 2, 1, 1, 1, 5);
        me.setMap(mapGrid);
    }
    //Checks the players death and handles everything surrounding that. Handles the death of Monsters as well
    public void checkDeath(){
        if(!me.isDeath()) {
            me.update();
        }else{
            spawnPlayer();
        }
        Node inQuestion;
        inQuestion = mobs.getHead();
        Monster monster;
        while(inQuestion != mobs.getFoot()){
            monster = inQuestion.getMonsterValue();
            if(monster.isDeath()){
                monster.dropItems(mapGrid, coinId, mobs);
                coinId++;
                mapGrid[monster.getGridy()][monster.getGridx()][2] = 0;
                inQuestion = inQuestion.getChild();
                mobs.deleteNode(inQuestion.getParent());
            }else{
                inQuestion = inQuestion.getChild();
            }
        }

        if (mobs.getFoot().getMonsterValue().isDeath()) {
            //mobs.getFoot().getMonsterValue().dropItems(mapGrid, coinId, mobs);
            coinId++;
            mapGrid[inQuestion.getMonsterValue().getGridy()][inQuestion.getMonsterValue().getGridx()][2] = 0;
            mobs.deleteNode(inQuestion);
        }
    }

    //<editor-fold desc="Something Noah was working on">
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
                frame.removeKeyListener(escapeListener.this);
                //menu.addButtons();

                System.out.println("Escape Listener Working");

                EscapeMenu escapeMenu = new EscapeMenu(frame);
                escapeMenu.escape();
            }
        }
    }*/
    //</editor-fold>
}