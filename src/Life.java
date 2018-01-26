import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Creates life object/display
 * 
 * @return double[][] 	
 */
public class Life extends Driver {

	private ImageView DISPLAY;
	
	/**
	 * Life constructor
	 * 
	 * @param x		X-coord of life on screen
	 * @param y		Y-coord of life on screen
	 */
	public Life(int x, int y) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(LIFE_IMG));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}
	
	/**
	 * Returns display for life
	 * 
	 * @return ImageView		Display to be put onto screen
	 */
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

}
