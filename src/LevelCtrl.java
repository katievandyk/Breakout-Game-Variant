public class LevelCtrl extends Driver {
	
	private double[][] hit_coords;
	private double[][] bounce_coords = null;
	private int CURR_LEVEL;
	
	/**
	 * Controls coordinates of level
	 */
	public LevelCtrl(int curr) {
		this.CURR_LEVEL = curr;
		this.hit_coords = null;
		this.bounce_coords = null;
	}
	
	/**
	 * Changes coordinates to input level, l
	 */
	public void changeLevel(int l) {
		this.CURR_LEVEL = l;
		updateCoords();
	}

	/**
	 * Block that only bounces objects off of it
	 */
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
	
	// Get coordinates of hit blocks
	public double[][] getHitCoords(){
		return this.hit_coords;
	}
	
	// Get coordinates of bounce blocks
	public double[][] getBounceCoords(){
		return this.bounce_coords;
	}
	
	// Returns current level
	public int currLevel() {
		return this.CURR_LEVEL;
	}

}





