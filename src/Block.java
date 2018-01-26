import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Superclass for block, which is displayed on screen and hit by bouncer
 * Dependencies: Driver
 *
 * @author Katherine Van Dyk
 * 
 */
public class Block extends Driver {
	private ImageView DISPLAY;
	private double X; 
	private double Y;

	/**
	 * Constructor for a block
	 * Dependencies: Driver
	 * Purpose: This class holds methods used by all blocks, including intersect and getting/setting coordinates
	 * Why Well-Designed: This code is well-designed because it avoids duplication and uses inheritance to allow for code flexibility/reuse. 
	 * 
	 * @param x		Upper left x-coordinate of where block is to be placed
	 * @param y		Upper left y-coordinate of where block is to be placed
	 */
	public Block (double x, double y){
		this.X = x;
		this.Y = y;
	}

	/**
	 * Checks if block intersects with bouncer
	 * 
	 * @param bouncer	Instance of bouncer with position bX, bY
	 * @return true if bouncer/block intersect, false otherwise
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
	 * Returns display of block object
	 * 
	 * @return ImageView 	Image of block to be added to scene
	 */
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

	/**
	 * Sets ImageView of block
	 * 
	 *@param img		String of image file name
	 */
	public void setDisplay(String img) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(img));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(this.getX());
		this.DISPLAY.setY(this.getY());
	}

	/**
	 * Retrieve x-position of block on scene
	 * 
	 * @return double	Upper left x-coord of block
	 */
	public double getX() {
		return this.X;
	}

	/**
	 * Retrieve y-position of block on scene
	 * 
	 * @return double	Upper left y-coord of block
	 */
	public double getY() {
		return this.Y;
	}
}

