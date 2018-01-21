public class LevelCtrl extends Driver {
	
	private double[][] hit_coords;
	private double[][] bounce_coords = null;
	private int CURR_LEVEL;
	
	public LevelCtrl(int curr) {
		this.CURR_LEVEL = curr;
		this.hit_coords = null;
		this.bounce_coords = null;
	}
	
	public void changeLevel(int l) {
		this.CURR_LEVEL = l;
		updateCoords();
	}
	
	public int currLevel() {
		return this.CURR_LEVEL;
	}

	public void updateCoords() {
		if(this.CURR_LEVEL == 1) {
			this.hit_coords = Levels.HitBlocksLevel1();
			this.bounce_coords = null;
		}
		else if(this.CURR_LEVEL == 2) {
			this.hit_coords = Levels.HitBlocksLevel2();
			this.bounce_coords = Levels.BounceBlocksLevel2();
		}
		else if(this.CURR_LEVEL == 3) {
			this.hit_coords = Levels.HitBlocksLevel3();
			this.bounce_coords = Levels.BounceBlocksLevel3();
		}
		else {
			this.hit_coords = null;
			this.bounce_coords = null;
		}
	}
	
	public double[][] getHitCoords(){
		return this.hit_coords;
	}
	
	public double[][] getBounceCoords(){
		return this.bounce_coords;
	}
}





