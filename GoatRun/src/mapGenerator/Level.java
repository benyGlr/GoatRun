package mapGenerator;

import java.util.ArrayList;

import errors.WrongLevelTypeException;
import mapMaterial.Plank;
import mapMaterial.PlankList;

public class Level {
	private int numberOfPlanks = 0;
	private LevelType type = LevelType.UP; 
	
	public Level(int p, LevelType t) throws WrongLevelTypeException{
		this.numberOfPlanks = p;
		this.type = t;
		PlankList.clean();
		switch (t) {
			case UP:
				GenerateArrayUD();
				break;
			case DOWN:
				GenerateArrayUD();
				break;
			case UP_DOWN:
				if (numberOfPlanks%2==1)
					GenerateArrayHill();
				else
					throw new WrongLevelTypeException(t);
			case DOWN_UP:
				if (numberOfPlanks%2==1)
					GenerateArrayGrove();
				else
					throw new WrongLevelTypeException(t);
				break;	
		}
		
	}
	
	private void GenerateArrayUD(){ //generates array for planks go only up or only down
		ArrayList<Integer> heights = new ArrayList<Integer>();
		
		int randomNum ;
		for (int i = 0; i < this.numberOfPlanks; i++){
			heights.add(i);
			
		}
		for (int p = 0; p < this.numberOfPlanks; p++){
			randomNum = (int)(Math.random() * heights.size());
			int newHeight = heights.get(randomNum);
			heights.remove(randomNum);
			PlankList.add(new Plank(newHeight,p));
		}
	}
	
	private void GenerateArrayHill(){ //generates array for planks go only up or only down
		ArrayList<Integer> heights = new ArrayList<Integer>();
		
		int randomNum ;
		for (int i = 0; i < this.numberOfPlanks; i++){
			if (i <= this.numberOfPlanks/2)
				heights.add(i);
			else
				heights.add(i%(this.numberOfPlanks/2));
			
			
		}
		for (int p = 0; p < this.numberOfPlanks; p++){
			randomNum = (int)(Math.random() * heights.size());
			int newHeight = heights.get(randomNum);
			heights.remove(randomNum);
			PlankList.add(new Plank(newHeight,p));
		}
	}
	
	private void GenerateArrayGrove(){ //generates array for planks go only up or only down
		ArrayList<Integer> heights = new ArrayList<Integer>();
		
		int randomNum ;
		for (int i = this.numberOfPlanks/2; i >= 0; i--)
			heights.add(i);
		for (int i = 1; i <= this.numberOfPlanks/2; i++) 
			heights.add(i);
		for (int p = 0; p < this.numberOfPlanks; p++){
			randomNum = (int)(Math.random() * heights.size());
			int newHeight = heights.get(randomNum);
			heights.remove(randomNum);
			PlankList.add(new Plank(newHeight,p));
		}
	}
	
	public int[] getPlankOrder(){ //returns current order of plank heights
		int [] retList = new int[this.numberOfPlanks];
		for (int i = 0; i < this.numberOfPlanks; i++){
			retList[i] =  PlankList.getPlank(i).getHeight();
		}
		return retList;
	}
	
	public int[] getPlankOrderPos(){ //returns current order of plank positions
		int [] retList = new int[this.numberOfPlanks];
		for (int i = 0; i < this.numberOfPlanks; i++){
			retList[i] =  PlankList.getPlank(i).getPos();
		}
		return retList;
	}
	
	public boolean checkWin(){
		switch (type) {
			case UP:
				return checkWinUp();
			case DOWN:
				return checkWinDown();
			case UP_DOWN:
				return checkWinUpDown();
			case DOWN_UP:
				return checkWinDownUp();
			default:
				return false;	
		}
	}
	
	private boolean checkWinUp(){
		for (int i = 0; i < numberOfPlanks - 1; i++){
			if (PlankList.getPlank(i + 1).getHeight() < PlankList.getPlank(i).getHeight()){
				return false;
			}
		}
		PlankList.clean();
		return true;
	}
	
	private boolean checkWinDown(){
		for (int i = 0; i < numberOfPlanks - 1; i++){
			if (PlankList.getPlank(i + 1).getHeight() > PlankList.getPlank(i).getHeight()){
				return false;
			}
		}
		PlankList.clean();
		return true;
	}
	
	private boolean checkWinUpDown(){
		int i = 0;
		for (i = i ;i < numberOfPlanks/2 - 1; i++){
			if (PlankList.getPlank(i + 1).getHeight() < PlankList.getPlank(i).getHeight()){
				return false;
			}
		}
		for (i = i+1 ;i < numberOfPlanks - 1; i++){
			if (PlankList.getPlank(i + 1).getHeight() > PlankList.getPlank(i).getHeight()){
				return false;
			}
		}
		PlankList.clean();
		return true;
	}
	
	private boolean checkWinDownUp(){
		int i = 0;
		for (i = i ;i < numberOfPlanks/2 - 1; i++){
			if (PlankList.getPlank(i + 1).getHeight() > PlankList.getPlank(i).getHeight()){
				return false;
			}
		}
		for (i = i+1 ;i < numberOfPlanks - 1; i++){
			if (PlankList.getPlank(i + 1).getHeight() < PlankList.getPlank(i).getHeight()){
				return false;
			}
		}
		PlankList.clean();
		return true;
	}
	
	public int getNumOfPlanks(){
		return this.numberOfPlanks;
	}
	
	public LevelType getLevelType(){
		return this.type;
	}
	
	
}
