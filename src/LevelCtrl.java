public class LevelCtrl extends Driver {

	public static void changeLevel() {
		if(CURR_LEVEL == 1) coords = Levels.Level1();
		else if(CURR_LEVEL == 2) coords = Levels.Level2();
		else if(CURR_LEVEL == 3) coords = Levels.Level3();
		else if(CURR_LEVEL == -1){
			SceneCtrl.createLoseScreen();
		}
		else {
			SceneCtrl.createWinScreen();
		}
	}

	public static void clearLevel() {
		if(CURR_LEVEL > 1) {
			for(Bouncer bouncer : bouncers) {
				root.getChildren().remove(bouncer.DISPLAY);
			}
			bouncers.clear();
			Bouncer b = new Bouncer(MOVER_SPEED);
			b.reset(SIZE, SIZE);
			bouncers.add(b);
			root.getChildren().add(b.DISPLAY);
		}
		if(CURR_LEVEL >=1) {
			int numhits = 1;
			blocks.clear();
			for(PowerUp p : powerUps) {
				root.getChildren().remove(p);
			}
			powerUps.clear();
			for(int i=0; i < coords.length; i++) {
				blocks.add(new HitBlock(coords[i][0], coords[i][1], numhits, BLOCK_IMG, BALL_POWERUP));
				root.getChildren().add(blocks.get(i).DISPLAY);
			}
		}

	}
}





