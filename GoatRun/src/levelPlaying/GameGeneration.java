package levelPlaying;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import errors.NumberOutOfBoundsException;
import errors.WrongLevelTypeException;
import mapGenerator.Level;
import mapGenerator.LevelType;
import mapMaterial.PlankList;

public class GameGeneration {
	private static String readInput(String displayMSG){
		Scanner scanner = new Scanner(System.in);
		System.out.println(displayMSG);
		String indexes = scanner.next();
		return indexes;
	}
	
	private static void collectInput(int max, ArrayList<Integer> holdInputs) throws NumberOutOfBoundsException{
		int input = 0;
		while(holdInputs.size() < 2){ // the player can has to make 2 inputs to continue
			input = Integer.parseInt(readInput("block to select")) - 1;
			if (input <= max)
				holdInputs.add(input);
			else{
				// if the player makes an invalid input throw this exception
				throw new NumberOutOfBoundsException(input, max);
			}
		}
	}
	
	private static void playLevel(Level level, int numOfPlanks){
		
		while (level.checkWin() != true){
			boolean finished = true;
			System.out.println("new height order is ");
			for (int i = 0; i < numOfPlanks; i++) 
				System.out.print(level.getPlankOrder()[i] + " ");
			ArrayList<Integer> s = new ArrayList<Integer>(); // this array holds the inputs
			while(finished){
				try{ 
					collectInput(numOfPlanks, s);
					// this means they inputed valid numbers
					PlankList.swapPlanks(s.get(0),s.get(1));
					finished = false;
				}catch(NumberOutOfBoundsException e){
					// this means they inputed invalid numbers
					System.out.println(e);
					System.out.println("redo inputs");
					s.clear();
				}
			}
			
		}
	}
	
	private static Level genLevel(int i){
		List<LevelType> types = Collections.unmodifiableList(Arrays.asList(LevelType.values()));
		int size = types.size();
		Random random = new Random();
		Level lvl = null;
		try{
			lvl = new Level(i,types.get(random.nextInt(size)));
			return lvl;
		}catch(WrongLevelTypeException e){ // this exception is in case the gen generates a level with a type not suited to the amount of planks
			System.out.println("reGenning...");
			return genLevel(i);
		}
		
	}
	public static void playGame(int amountOfGames){
		for (int i = 4; i < amountOfGames; i++){
			System.out.println("number of planks is " + i);
			Level level = genLevel(i);
			System.out.println("LevelType is " + level.getLevelType());
			playLevel(level,i);
			System.out.println("congrats");
			//this is where the goat moves across the blocks
		}
	}
	
	public static void main(String[] args) {
		playGame(6);
	}
}
