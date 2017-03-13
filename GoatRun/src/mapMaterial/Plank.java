package mapMaterial;

public class Plank {
	private int height = 0;
	private int pos = 0;
	private boolean used = false;
	
	public Plank(int h, int p){
		height = h;
		pos = p;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getPos(){
		return pos;
	}
	
	public boolean getUsed(){
		return used;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setPos(int p){
		pos = p;
	}
	
	public void setUsed(boolean u){
		used = u;
	}
	
	
	public boolean equals(Plank p){
		return this.height == p.getHeight();
	}
	
	public float plankHeight(){
		return ((height + Constants.ADDITIONAL_PLANK_HEIGHT) * Constants.PLANK_HEIGHT_MULTIPLIER);
	}
	
	public float LeftXOfPlank(){
		return pos * (Constants.WIDTH_OF_PLANK + Constants.DISTANCE_BETWEEN_PLANKS);
	}
	
	public boolean inHitBox(float x, float y){
		if (x >= this.LeftXOfPlank() && x <= this.LeftXOfPlank() + Constants.WIDTH_OF_PLANK && y <= this.plankHeight())
			return true;	
		return false;
		
	}
	
}
