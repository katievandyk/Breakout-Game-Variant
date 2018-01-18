import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HitBlock extends Game {

	ImageView DISPLAY;
	int numhits;
	int X;
	int Y;
	String powerUp;
	boolean VALID;

	// Constructor
	public HitBlock (int x, int y, int nHits, String BLOCK_IMAGE, String powerUp){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.numhits = nHits;
		this.X = x;
		this.Y = y;
		this.powerUp = powerUp;
		this.VALID = true;

	}

	public boolean intersect(Bouncer bouncer) {
		double X = this.DISPLAY.getX();
		double Y = this.DISPLAY.getY();
		double bX = bouncer.DISPLAY.getX();
		double bY = bouncer.DISPLAY.getY();		

		if(bX >= X && bX <= (X + BLOCK_WIDTH) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}
}




