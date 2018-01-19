public class LevelCtrl extends Driver {

	public static void changeLevel() {
		if(CURR_LEVEL == 1) {
			hit_coords = Levels.HitBlocksLevel1();
		}
		else if(CURR_LEVEL == 2) {
			hit_coords = Levels.HitBlocksLevel2();
			bounce_coords = Levels.BounceBlocksLevel2();
		}
		else if(CURR_LEVEL == 3) {
			hit_coords = Levels.HitBlocksLevel3();
			bounce_coords = Levels.BounceBlocksLevel2();
		}
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
			hit_blocks.clear();
			bounce_blocks.clear();
			for(PowerUp p : powerUps) {
				root.getChildren().remove(p.DISPLAY);
			}
			powerUps.clear();
		}

	}
	
	public static void makeBlocks() {
		String img;
		int numhits;
		for(int i=0; i < hit_coords.length; i++) {
			if(i % 2 == 0) {
				numhits = 2;
				img = BLOCK2_IMG;
			}else {
				numhits = 1;
				img = BLOCK_IMG;
			}
			hit_blocks.add(new HitBlock(hit_coords[i][0], hit_coords[i][1], numhits, img, BALL_POWERUP));
			root.getChildren().add(hit_blocks.get(i).DISPLAY);
		}
		
		if(bounce_coords != null) {
			for(int i=0; i < bounce_coords.length; i++) {
				bounce_blocks.add(new BounceBlock(bounce_coords[i][0], bounce_coords[i][1], BOUNCEBLOCK_IMG));
				root.getChildren().add(bounce_blocks.get(i).DISPLAY);
			}
		}
	}
}





