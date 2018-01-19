import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HitBlock extends Driver {

	ImageView DISPLAY;
	int numhits;
	double X;
	double Y;
	String powerUp;
	boolean VALID;

	// Constructor
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
		ArrayList<HitBlock> save = new ArrayList<HitBlock>();
		for(HitBlock block : hit_blocks) {
			for(Bouncer bouncer : bouncers) {
				if(block.intersect(bouncer) && block.numhits == 1 && block.VALID) {
					block.VALID = false;
					root.getChildren().remove(block.DISPLAY);
					NUM_POINTS++;
					SceneCtrl.updatePointsText();
					if(block.powerUp.equals(BALL_POWERUP)) {
						PowerUp p = new PowerUp(BALL_POWERUP);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						root.getChildren().add(p.DISPLAY);
					}
					else if(block.powerUp.equals(LIFE_POWERUP)) {
						PowerUp p = new PowerUp(LIFE_POWERUP);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						root.getChildren().add(p.DISPLAY);
					}
					else if(block.powerUp.equals(PADDLE_POWERUP)) {
						PowerUp p = new PowerUp(PADDLE_POWERUP);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						root.getChildren().add(p.DISPLAY);
					}
					bouncer.bounceBlocks(elapsedTime);
				}
				else if(block.intersect(bouncer) && block.VALID) {	
					int nh = block.numhits -1;
					double x = block.X;
					double y = block.Y;
					root.getChildren().remove(block.DISPLAY);
					block.VALID = false; 
					block = new HitBlock(x, y, nh, BLOCK_IMG, PADDLE_POWERUP);
					save.add(block);
					root.getChildren().add(block.DISPLAY);
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
		for(HitBlock newBlock : save) {
			hit_blocks.add(newBlock);
		}
	}
}





