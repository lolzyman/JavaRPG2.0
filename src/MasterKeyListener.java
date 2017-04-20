import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class MasterKeyListener{
	//Creates a stack that we use to main Key values
	//We are using stacks because we are unsure how many keys we are holding down at once.
	//We create different classes to hold different categories of key inputs.
	//The stacks maintain which keys were pressed in what order, the Manager Classes maintain which keys are down or not.

	//Creates a stack for WASD keys
	MultiPurposeStack 	keyArrayWASD = new MultiPurposeStack();

	//Creates a manager Class to help maintain which keys are down.
    WASDKeyManager manager = new WASDKeyManager();
    //Variable so we only have to type so much so ofter
	public static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	//Strings so we can make things more readable
    public static final String 	MOVE_UP = "move up",
    							MOVE_LEFT = "move left",
    							MOVE_RIGHT = "move right",
    							MOVE_DOWN = "move down",
    							MOVE_UPR = "stop up",
    	    					MOVE_LEFTR = "stop left",
    	    					MOVE_RIGHTR = "stop right",
    	    					MOVE_DOWNR = "stop down";
    //Getter for the WASD stack
	public MultiPurposeStack getKeyArrayWASD() {
		return keyArrayWASD;
	}
	//Constructor
	//Assigns things to the input map and to the action map. 2 in each map for each key, one for press, the other for releaseing
	public MasterKeyListener(World target){
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), MOVE_UP);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), MOVE_DOWN);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), MOVE_LEFT);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), MOVE_RIGHT);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), MOVE_UPR);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), MOVE_DOWNR);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), MOVE_LEFTR);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), MOVE_RIGHTR);
		target.getActionMap().put(MOVE_UP, new Listener(keyArrayWASD, "W", true, manager));
		target.getActionMap().put(MOVE_DOWN, new Listener(keyArrayWASD, "S", true, manager));
		target.getActionMap().put(MOVE_LEFT, new Listener(keyArrayWASD, "A", true, manager));
		target.getActionMap().put(MOVE_RIGHT, new Listener(keyArrayWASD, "D", true, manager));
		target.getActionMap().put(MOVE_UPR, new Listener(keyArrayWASD, "W", false, manager));
		target.getActionMap().put(MOVE_DOWNR, new Listener(keyArrayWASD, "S", false, manager));
		target.getActionMap().put(MOVE_LEFTR, new Listener(keyArrayWASD, "A", false, manager));
		target.getActionMap().put(MOVE_RIGHTR, new Listener(keyArrayWASD, "D", false, manager));
	}
}
@SuppressWarnings("serial")
//Needed to work with the Action Map. Gets called by it
class Listener extends AbstractAction{
	//Creates the variables String, the stack that the key corresponds to, the type of action, and the manager
	//Associated with the key
	String Key = null;
	MultiPurposeStack banana = null;
	boolean add = false;
	WASDKeyManager manager;
	//Constructor
	//Populates the above variables
	public Listener(MultiPurposeStack b, String key, boolean keyPressed, WASDKeyManager manage){
		banana = b;
		Key = key;
		add = keyPressed;
		manager = manage;
	}
	//overrides the basic actionPerformed method
	@Override
	public void actionPerformed(ActionEvent e) {
		//Handles keys when being added
		if(add){
			//Switch statement deals with all the possible keys
			//For all the add functions, we had a condition that prevents extra lines of code if the key is already
			//Considered true. This deals with held keys
			switch(Key){
			case "D":
				if(!manager.isKey_d()){
					manager.setKey_d(true);
					banana.addBeginning(1);
				}
				break;
			case "A":
				if(!manager.isKey_a()){
					manager.setKey_a(true);
					banana.addBeginning(3);
				}
				break;
			case "S":
				if(!manager.isKey_s()){
					manager.setKey_s(true);
					banana.addBeginning(2);
				}
				break;
			case "W":
				if(!manager.isKey_w()){
					manager.setKey_w(true);
					banana.addBeginning(0);
				}
				break;
			}
			//This else part deals with keys being removed
			//Contains the same if statements, but not need by nature of the removal
			//Only there as a precaution
		}else{
			switch(Key){
			case "D":
				if(manager.isKey_d()){
					manager.setKey_d(false);
					banana.deleteValue(1);
				}
				break;
			case "A":
				if(manager.isKey_a()){
					manager.setKey_a(false);
					banana.deleteValue(3);
				}
				break;
			case "S":
				if(manager.isKey_s()){
					manager.setKey_s(false);
					banana.deleteValue(2);
				}
				break;
			case "W":
				if(manager.isKey_w()){
					manager.setKey_w(false);
					banana.deleteValue(0);
				}
				break;
			}
		}
	}
}
//this class handles weather the wasd keys are up or down
class WASDKeyManager{
	boolean key_w,
			key_s,
			key_a,
			key_d;
	public boolean isKey_w() {
		return key_w;
	}
	public void setKey_w(boolean key_w) {
		this.key_w = key_w;
	}
	public boolean isKey_s() {
		return key_s;
	}
	public void setKey_s(boolean key_s) {
		this.key_s = key_s;
	}
	public boolean isKey_a() {
		return key_a;
	}
	public void setKey_a(boolean key_a) {
		this.key_a = key_a;
	}
	public boolean isKey_d() {
		return key_d;
	}
	public void setKey_d(boolean key_d) {
		this.key_d = key_d;
	}
}