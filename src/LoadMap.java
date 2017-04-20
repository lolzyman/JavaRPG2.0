import java.util.Scanner;

public class LoadMap extends FileManager{
	//Scanner for importing the files.
	Scanner scan;
	//Stack or que needed to handle all the lines from the file
	MultiPurposeStack pear = new MultiPurposeStack();
	//The file is expected to handle 3 different types of data.
	//charpos is the starting position of the player on the map
	//coins is the array for coins that spawn naturally
	//mobs is the array for all the mobs that spawn naturally
	int[] 	charpos;
	int[][]	coins,
			mobs;
	//Constructor
	//All computations are done in this method
	public LoadMap(String fileName){
		//index for the rows.
		int rows = 0;
		//creates the array size for the charpos array
		charpos = new int[2];
		// sets up the scanner using two methods from the superclass
		scan = setUpScanner(getFile("" + fileName));
		try{
			while(scan.hasNextLine()){
				//trys to load the first line of the string into the stack
				String something = scan.nextLine();
				pear.addEnd(something);
				rows++;
			}
		}catch(NullPointerException e){
			//Honestly not sure what this segment is here for
			//Performs the same function as the previous segment of code, but will break on the last line of code.
			//Leaving it in here because this comment is created at 1 in the morning and possibility of it being
			// something that im missing
			boolean modify = true;
			while(modify){
				if(scan.nextLine() == null){
					modify = false;
				}
				String something = scan.nextLine();
				pear.addEnd(something);
				rows++;
			}
		}
		//For loop goes through each of the lines loaded into the stack and analysis each one.
		// bounds are handled by the rows indicator, which keeps track of the size of the stack.
		for(int i = rows; i > 0; i--){
			//loads the present element in the stack
			String working = pear.getHead().getStringValue();
			//Splits the string element into expected components. More on this in the text file
			String[] tokens = working.split(":");
			switch(tokens[0]){
				case "Start":
					String[] pos = tokens[1].split(",");
					charpos[0] = Integer.parseInt(pos[0]);
					charpos[1] = Integer.parseInt(pos[1]);
					break;
				case "//":
					//This area is for comments.
					break;
				case "Coin":
					String[] data = tokens[1].split(";");
					coins = new int[data.length][];
					int coinindex = 0;
					for(String apple: data) {
						coins[coinindex] = new int[3];
						String[] banana = apple.split(",");
						coins[coinindex][0] = Integer.parseInt(banana[0]);
						coins[coinindex][1] = Integer.parseInt(banana[1]);
						coins[coinindex][2] = Integer.parseInt(banana[2]);
						coinindex++;
					}
					break;
				case "Mobs":
					String[] horde = tokens[1].split(";");
					mobs = new int[horde.length][];
					int mobindex = 0;
					for(String apple: horde) {
						mobs[mobindex] = new int[3];
						String[] banana = apple.split(",");
						mobs[mobindex][0] = Integer.parseInt(banana[0]);
						mobs[mobindex][1] = Integer.parseInt(banana[1]);
						mobs[mobindex][2] = Integer.parseInt(banana[2]);
						mobindex++;
					}
					break;
				default:
					System.out.println("You have some data in the files that isn/'t being read");
					break;
			}
			pear.deleteBeginning();
		}
	}
	//Getters for the information extracted from the txt file//
	public int[] getCharPos(){
		return charpos;
	}
	public int[][] getCoins(){
		return coins;
	}
	public int[][] getMobs(){
		return  mobs;
	}
}
