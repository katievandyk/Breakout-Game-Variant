import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HitBlock extends Driver {

	ImageView DISPLAY;
	int numhits;
	double X;
	double Y;
	String powerUp;
	boolean VALID;
	String[] availablePowerUps = {SNOWBALL, BALL_POWERUP, LIFE_POWERUP, PADDLE_POWERUP, null};

	
	public HitBlock (double x, double y, int nHits, String BLOCK_IMAGE, String powerUp){
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
	// Constructor
	public HitBlock (double x, double y, int i) {
		String img;
		int numhits;
		if(i % 2 == 0) {
			numhits = 2;
			img = BLOCK2_IMG;
		}
		else {
			numhits = 1;
			img = BLOCK_IMG;
		} 

		java.util.Random random = new java.util.Random();
		int r = random.nextInt(availablePowerUps.length);
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(img));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.numhits = numhits;
		this.X = x;
		this.Y = y;
		this.powerUp = availablePowerUps[r];
		this.VALID = true;
	}
	

	public boolean canRemove(Bouncer bouncer) {
		return this.intersect(bouncer) && this.numhits == 1 && this.VALID;
	}

	public boolean canHit(Bouncer bouncer) {
		return this.intersect(bouncer) && this.VALID;	
	}

	public void decreaseHits(String newImage) {
		this.numhits--;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(newImage));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(this.X);
		this.DISPLAY.setY(this.Y);
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

	public ImageView getDisplay() {
		return this.DISPLAY;
	}

	public double getX() {
		return this.DISPLAY.getX();

	}

	public double getY() {
		return this.DISPLAY.getY();
	}
}





