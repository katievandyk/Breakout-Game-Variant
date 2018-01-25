import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Block class that contains images/positions of blocks to be placed on screen, and methods to determine removal and power ups
 * 
 */
public class Block extends Driver {
	private ImageView DISPLAY;
	private String POWERUP;
	private boolean VALID;
	private int numHits;
	private double X; 
	private double Y;

	/**
	 * Constructor for a block
	 *@param x		Upper left x-coordinate of where block is to be placed
	 *@param y		Upper left y-coordinate of where block is to be placed
	 */
	public Block (double x, double y){
		this.POWERUP = null;
		this.VALID = true;
		this.numHits = 0;
		this.X = x;
		this.Y = y;
	}

	/**
	 * Check if a block intersects with a bouncer
	 *@param bouncer		Instance of bouncer at specific time
	 */
	public boolean intersect(Bouncer bouncer) {
		double bX = bouncer.getX();
		double bY = bouncer.getY();	

		if(bX >= this.getX() && bX <= (this.getX() + BLOCK_WIDTH) && bY >= Y && bY <= (this.getY() + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns ImageView of block
	 */
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

	/**
	 * Sets ImageView of block
	 *@param img		String of image file name
	 */
	public void setDisplay(String img) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(img));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(this.getX());
		this.DISPLAY.setY(this.getY());
	}

	/**
	 * Returns upper left x-coordinate of block
	 */
	public double getX() {
		return this.X;
	}

	/**
	 * Returns upper left y-coordinate of block
	 */
	public double getY() {
		return this.Y;
	}

	/**
	 * Returns if block is valid
	 */
	public boolean getValid() {
		return this.VALID;
	}

	/**
	 * Sets block to be valid/invalid
	 * @param v		Boolean indicating if block is valid
	 */
	public void setValid(boolean v) {
		this.VALID = v;
	}

	/**
	 * Sets power up to specific type
	 * @param s 		String s containing power up type
	 */
	public void setPowerUp(String s) {
		this.POWERUP = s;
	}

	/**
	 * Returns type of power up
	 */
	public String getPowerUp() {
		return this.POWERUP;
	}

	/**
	 * Returns number of hits a block has
	 */
	public int getNumHits() {
		return this.numHits;
	}

	/**
	 * Sets number of hits a block has
	 */
	public void setNumHits(int n) {
		this.numHits = n;
	}
}

