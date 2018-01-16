import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends Game {
	
	ImageView DISPLAY;
	int numhits;
	int X;
	int Y;
	
	// Constructor
	public Block (int x, int y, int nHits, String BLOCK_IMAGE){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.numhits = nHits;
		this.X = x;
		this.Y = y;
		
	}
	
	public boolean intersect(Block block, Bouncer bouncer) {
		double X = block.DISPLAY.getX();
		double Y = block.DISPLAY.getY();
		double bX = bouncer.DISPLAY.getX();
		double bY = bouncer.DISPLAY.getY();
		if(bX >= X && bX <= (X + 70) && bY >= Y && bY <= (Y + 30)) {
			return true;
		}
		return false;
	}
	


}
