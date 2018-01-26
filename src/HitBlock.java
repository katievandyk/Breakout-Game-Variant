public class HitBlock extends Block {
	
	private int NUMHITS;
	private String POWERUP;
	private boolean VALID;
	private final String BLOCK_IMG = "brick1.gif";
	private final String BLOCK2_IMG = "brick2.gif";
	private String[] availablePowerUps = {SNOWBALL, BALL_POWERUP, LIFE_POWERUP, PADDLE_POWERUP, null};

	/**
	 * Constructor for block that can be hit one or multiple times
	 * Dependencies: Block, Driver
	 * Purpose: HitBlock is a subclass of the Block class and creates a block that can be hit multiple times. Includes extra methods for block removal/power ups. 
	 * Why Well-Designed: Reuses methods from block super-class and adds block-specific methods (removal, setPowerup) without code duplication. 
	 * 
	 * @author Katherine Van Dyk
	 * @param x		x-coordinate on screen
	 * @param y		y-coordinate on screen
	 */
	public HitBlock (double x, double y){
		super(x, y);
		this.NUMHITS = 0;
		this.POWERUP = null;
		this.VALID = true;
	}
	
	/**
	 * Set features of hit block- power up, initial number of hits
	 * 
	 * @param numHits
	 */
	public void setFeatures(int numHits) {
		java.util.Random random = new java.util.Random();
		int r = random.nextInt(availablePowerUps.length);
		this.setPowerUp(availablePowerUps[r]);
		this.setNumHits(numHits);
		this.changeImage();
	}

	/**
	 * Check that block can be hit, but not removed
	 * 
	 * @param bouncer 		Instance of bouncer when block can be hit
	 */
	public boolean canHit(Bouncer bouncer) {
		return this.intersect(bouncer) && this.getValid() && this.getNumHits() > 1;
	}
	
	/**
	 * Check that bouncer can be removed from screen
	 * 
	 * @param bouncer 		Instance of bouncer when block is hit
	 */
	public boolean canRemove(Bouncer bouncer) {
		return this.intersect(bouncer) && this.getValid() && this.getNumHits() == 1;
	}
	
	/**
	 * Remove block by setting it invalid
	 * 
	 * @return String	PowerUp contained in block (to drop)
	 */
	public String Remove() {
		this.setValid(false);
		return this.getPowerUp();
	}
	
	/**
	 * Hit block by decreasing number of hits and changing image
	 */
	public void Hit() {
		this.decreaseHits();
		this.changeImage();
	}

	/**
	 * Decrease number of hits left before block removal
	 */
	private void decreaseHits() {
		this.setNumHits(this.getNumHits() - 1);
	}
	
	/**
	 * Change the image of the block
	 */
	private void changeImage() {
		if(this.getNumHits() == 2) {
			this.setDisplay(BLOCK2_IMG);
		}
		else {
			this.setDisplay(BLOCK_IMG);
		}
	}
	
	/**
	 * Sets block power up
	 * 
	 * @param s 		String s containing power up type
	 */
	public void setPowerUp(String s) {
		this.POWERUP = s;
	}


	/**
	 * Returns type of powerUp contained in block
	 * 
	 * @return String	Contains type of power up
	 */
	public String getPowerUp() {
		return this.POWERUP;
	}

	/**
	 * Returns number of hits a block has
	 */
	public int getNumHits() {
		return this.NUMHITS;
	}

	/**
	 * Sets number of hits a block has left before removal
	 */
	public void setNumHits(int n) {
		this.NUMHITS = n;
	}
	
	/**
	 * Returns if block is valid (not removed)
	 * 
	 * @return boolean	True if valid, false otherwise
	 */
	public boolean getValid() {
		return this.VALID;
	}

	/**
	 * Sets block to be valid/invalid
	 * 
	 * @param v		Boolean indicating if block is valid
	 */
	public void setValid(boolean v) {
		this.VALID = v;
	}
}





