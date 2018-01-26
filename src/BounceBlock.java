public class BounceBlock extends Block {
	
	private static final String BOUNCEBLOCK_IMG = "brick3.gif";
	
	/**
	 * Constructor for block that can be hit without ever being removed
	 * Dependencies: Block, Driver
	 * Purpose: BounceBlock is a subclass of the Block class and creates a block that can be hit without changing states.  
	 * Why Well-Designed: BounceBlock reuses methods from Block class and also sets image to default BounceBlock image.
	 * 
	 * @author Katherine Van Dyk
	 * @param x 		x-coordinate on screen
	 * @param y		y-coordinate on screen
	 * 
	 */
	public BounceBlock(double x, double y) {
		super(x, y);
		this.setDisplay(BOUNCEBLOCK_IMG);
	}
}
