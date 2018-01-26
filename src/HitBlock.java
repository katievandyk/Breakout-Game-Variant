public class HitBlock extends Block {
	
	public static final String BLOCK_IMG = "brick1.gif";
	public static final String BLOCK2_IMG = "brick2.gif";
	private String[] availablePowerUps = {SNOWBALL, BALL_POWERUP, LIFE_POWERUP, PADDLE_POWERUP, null};

	/**
	 * Block that can be hit one or multiple times
	 */
	public HitBlock (double x, double y){
		super(x, y);
	}


	/**
	 * Check that block can be hit, but not removed
	 * @param bouncer 		Instance of bouncer when block can be hit
	 */
	public boolean canHit(Bouncer bouncer) {
		return this.intersect(bouncer) && this.getValid() && this.getNumHits() > 1;
	}
	
	/**
	 * Check that bouncer can be removed from screen
	 * @param bouncer 		Instance of bouncer when block is hit
	 */
	public boolean canRemove(Bouncer bouncer) {
		return this.intersect(bouncer) && this.getValid() && this.getNumHits() == 1;
	}
	
	/**
	 * Remove block by setting it invalid, returning power up to drop
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
	 * Decrease number of hits left before removal
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
	 * Set unique features of hit block- power up, initial number of hits
	 */
	public void setFeatures(int numHits) {
		// Use random number generator to determine power up
		java.util.Random random = new java.util.Random();
		int r = random.nextInt(availablePowerUps.length);
		this.setPowerUp(availablePowerUps[r]);
		this.setNumHits(numHits);
		this.changeImage();
	}
}





