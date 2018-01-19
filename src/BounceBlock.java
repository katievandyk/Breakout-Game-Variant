import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BounceBlock extends Driver {

	ImageView DISPLAY;
	int numhits;
	int X;
	int Y;
	
	public BounceBlock(int x, int y, String BLOCK_IMAGE) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.X = x;
		this.Y = y;
	}
	
	public boolean intersect(Bouncer bouncer) {
		double X = this.DISPLAY.getX();
		double Y = this.DISPLAY.getY();
		double bX = bouncer.DISPLAY.getX();
		double bY = bouncer.DISPLAY.getY();		

		if(bX >= X && bX <= (X + BOUNCE_BLOCK) && bY >= Y && bY <= (Y + BOUNCE_BLOCK)) {
			return true;
		}
		return false;
	}

}
