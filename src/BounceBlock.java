public class BounceBlock extends Block {
	
	private static final String BOUNCEBLOCK_IMG = "brick3.gif";
	
	/**
	 * Block that only bounces objects off of it
	 */
	public BounceBlock(double x, double y) {
		super(x, y);
		this.setDisplay(BOUNCEBLOCK_IMG);
	}
}
