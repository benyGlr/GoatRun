package errors;

import mapGenerator.LevelType;

public class WrongLevelTypeException extends Exception {
	LevelType type;
	public WrongLevelTypeException(LevelType t){
		type = t;
	}
	
	public String toString(){
		return "level put in was " + type + " try UP or DOWN"; 
	}
	
}
