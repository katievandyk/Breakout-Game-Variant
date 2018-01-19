import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BounceBlock extends Driver {

	ImageView DISPLAY;
	int numhits;
	double X;
	double Y;
	
	public BounceBlock(double bounce_coords, double bounce_coords2, String BLOCK_IMAGE) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(bounce_coords);
		this.DISPLAY.setY(bounce_coords2);
		this.X = bounce_coords;
		this.Y = bounce_coords2;
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
	
	public static void killBlocks(double elapsedTime) {
		for(BounceBlock block : bounce_blocks) {
			for(Bouncer bouncer : bouncers) {
				if(block.intersect(bouncer)) {
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
	}

}
