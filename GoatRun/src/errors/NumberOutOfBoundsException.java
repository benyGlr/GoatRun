package errors;

public class NumberOutOfBoundsException extends Exception{
	int wrongNum;
	int rightNum;
	public NumberOutOfBoundsException(int w, int r){
		wrongNum = w;
		rightNum = r;
	}
	
	public String toString(){
		return "number put in was " + (rightNum - wrongNum) + " off an acceptable number"; 
	}
}
