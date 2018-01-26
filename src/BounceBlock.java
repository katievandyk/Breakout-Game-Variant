public class BounceBlock extends Block {
	
	private static final String BOUNCEBLOCK_IMG = "brick3.gif";
	
	/**
	 * Constructor for block that can be hit without ever being removed
	 * Dependencies: Block, Driver
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
