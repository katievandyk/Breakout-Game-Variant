import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BounceBlock extends Driver {

	ImageView DISPLAY;
	int numhits;
	double X;
	double Y;
	
	/**
	 * Block that only bounces objects off of it
	 */
	public BounceBlock(double x, double y, String BLOCK_IMAGE) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.X = x;
		this.Y = y;
	}
	
	public boolean intersect(Bouncer bouncer) {
		double X = this.X;
		double Y = this.Y;
		double bX = bouncer.DISPLAY.getX();
		double bY = bouncer.DISPLAY.getY();		

		if(bX >= X && bX <= (X + BLOCK_WIDTH) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}
	
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

}
