/**
 * Controls hitBlock and bounceBlock coordinates determined by current level
 * Dependencies: Driver
 * 
 * @author Katherine Van Dyk
 *
 */
public class LevelCtrl extends Driver {
	
	private double[][] hit_coords;
	private double[][] bounce_coords = null;
	private int CURR_LEVEL;
	
	/**
	 * Constructor for level controller
	 * 
	 * @param curr		Current level
	 */
	public LevelCtrl(int curr) {
		this.CURR_LEVEL = curr;
		this.hit_coords = null;
		this.bounce_coords = null;
	}
	
	/**
	 * Changes level
	 * 
	 * @param l		Level to be changed to
	 */
	public void changeLevel(int l) {
		this.CURR_LEVEL = l;
		updateCoords();
	}

	/**
	 * Updates hit_coords[] and bounce_block[] coords to match current level
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
	
	/**
	 * Return coordinates of hit blocks
	 * 
	 * @return double	hitBlock coords [x,y]
	 */
	public double[][] getHitCoords(){
		return this.hit_coords;
	}
	
	/**
	 * Return coordinates of bounce blocks
	 * 
	 * @return double	bounceBlock coords [x,y]
	 */
	public double[][] getBounceCoords(){
		return this.bounce_coords;
	}
	
	/**
	 * Returns current level
	 * 
	 * @return int	Current level
	 */
	public int currLevel() {
		return this.CURR_LEVEL;
	}

}





