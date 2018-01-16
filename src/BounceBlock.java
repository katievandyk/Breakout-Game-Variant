import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BounceBlock extends Game {

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


}
