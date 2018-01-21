import javafx.scene.input.KeyCode;

public class CheatKeys extends Driver {

	// Movement
	public static void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			myPaddle.DISPLAY.setX(myPaddle.DISPLAY.getX() + MOVER_SPEED/4);
		}
		else if (code == KeyCode.LEFT) {
			myPaddle.DISPLAY.setX(myPaddle.DISPLAY.getX() - MOVER_SPEED/4);
		}
		else if(code == KeyCode.DIGIT1) {
			CURR_LEVEL = 1; 
			updateLevel();
			resetBouncer();
		}
		else if(code == KeyCode.DIGIT2) {
			CURR_LEVEL = 2; 
			updateLevel();
			resetBouncer();
		}
		else if(code == KeyCode.DIGIT3) {
			CURR_LEVEL = 3; 
			clearBlocks();
			updateLevel();
			resetBouncer();
		}
		else if(code == KeyCode.L) {
			while(lives.size() < 3) {
				NUM_LIVES++;
				Life li = new Life(MARGIN + 20 * NUM_LIVES, SIZE - 30);
				root.getChildren().add(li.DISPLAY);
				lives.add(li);
			}
		}
		else if(code == KeyCode.R) {
			resetBouncer();
			clearBlocks();
			updateLevel();
		}
	}
	
	public static void clearBlocks() {
		for(HitBlock block : hit_blocks) {
			root.getChildren().remove(block.DISPLAY);
		}
		for(BounceBlock block : bounce_blocks) {
			root.getChildren().remove(block.DISPLAY);
		}
		
		hit_blocks.clear();
		bounce_blocks.clear();
	}
	
	public static void resetBouncer() {
		Bouncer temp = bouncers.get(0);
		temp.reset(SIZE, SIZE);
		for(Bouncer bouncer : bouncers) {
			root.getChildren().remove(bouncer.DISPLAY);
		}
		bouncers.clear();
		bouncers.add(temp);
		root.getChildren().add(temp.DISPLAY);
	}
}
