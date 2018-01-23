import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BounceBlock extends Driver {

	private ImageView DISPLAY;
	
	/**
	 * Block that only bounces objects off of it
	 */
	public BounceBlock(double x, double y, String BLOCK_IMAGE) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}
	
	/**
	 * Check if block intersects with bouncer
	 */
	public boolean intersect(double bX, double bY) {
		double X = this.getX();
		double Y = this.getY();

		if(bX >= X && bX <= (X + BLOCK_WIDTH) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}
	
	// Gets image display
	public ImageView getDisplay() {
		return this.DISPLAY;
	}
	
	// Gets top left x coord of block
	public double getX() {
		return this.DISPLAY.getX();
	}
	
	// Gets top left y coord of block
	public double getY() {
		return this.DISPLAY.getY();
	}


}
