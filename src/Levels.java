
public class Levels extends Driver{
	
	static double[][] blocks;
	
	public static double[][] HitBlocksLevel1(){
		blocks = new double[28][2];
		blocks[0][0] = MARGIN;
		blocks[0][1] = MARGIN;
		
		for(int i=1; i < blocks.length; i++) {
			blocks[i][0] = blocks[i-1][0] + BLOCK_WIDTH;
			blocks[i][1] = blocks[i-1][1];
			if(blocks[i][0] >= (SIZE - (BLOCK_WIDTH + MARGIN))) {
				blocks[i][0] = MARGIN;
				blocks[i][1] = blocks[i-1][1] + BLOCK_HEIGHT;
			}
		}
		return blocks;	
	}
	
	public static double[][] HitBlocksLevel2(){
		double MULTIPLIER_X = .75;
		double MULTIPLIER_Y = .50;
		blocks = new double[17][2];
		
		blocks[0][0] = (SIZE - BLOCK_WIDTH) / 2;
		blocks[0][1] = MARGIN;
		
		for(int i=1; i < blocks.length; i++) {
			blocks[i][0] = blocks[i-1][0] + BLOCK_WIDTH * MULTIPLIER_X;
			blocks[i][1] = blocks[i-1][1] + MARGIN * MULTIPLIER_Y;
			if(blocks[i][0] > (SIZE - (BLOCK_WIDTH + 2*MARGIN)) || blocks[i][0] <= 2*MARGIN) {
				MULTIPLIER_X = - MULTIPLIER_X;
			}
			else if(blocks[i][1] >= (SIZE - 2*BLOCK_WIDTH) /2) {
				MULTIPLIER_Y = - MULTIPLIER_Y;
			}
		}

		return blocks;
	
	}
	
	public static double[][] HitBlocksLevel3(){
		double MULTIPLIER_X = .5;
		double MULTIPLIER_Y = .5;
		int num = 5;
		blocks = new double[22][2];
		
		blocks[0][0] = (SIZE - BLOCK_WIDTH)/2;
		blocks[0][1] = (SIZE - BLOCK_HEIGHT)/4;
		
		int i;
		for(i=1; i < num; i++) {
			blocks[i][0] = blocks[i-1][0] + BLOCK_WIDTH * MULTIPLIER_X;
			blocks[i][1] = blocks[i-1][1] + MARGIN * MULTIPLIER_Y;
		}	
		MULTIPLIER_Y = - MULTIPLIER_Y;
		
		blocks[num][0] = blocks[0][0] + BLOCK_WIDTH * MULTIPLIER_X;
		blocks[num][1] = blocks[0][1] + MARGIN * MULTIPLIER_Y;
		for(i = num + 1; i < 2*num-1; i++ ) {
			blocks[i][0] = blocks[i-1][0] + BLOCK_WIDTH * MULTIPLIER_X;
			blocks[i][1] = blocks[i-1][1] + MARGIN * MULTIPLIER_Y;
		}
	
		blocks[i][0] = blocks[0][0] + BLOCK_WIDTH + MARGIN/2;
		blocks[i][1] = blocks[0][1];
		for(i = 2*num; i < 3*num -4; i++) {
			blocks[i][0] = blocks[i-1][0] + BLOCK_WIDTH + MARGIN/2;
			blocks[i][1] = blocks[0][1];
		}

		for(int j = 0; j < i; j++) {
			blocks[i+j][0] = SIZE - BLOCK_WIDTH - blocks[j][0];
			blocks[i+j][1] = blocks[j][1];
		}

		return blocks;
	}
	

	public static double[][] BounceBlocksLevel2(){
		blocks = new double[1][2];
		blocks[0][0] = (SIZE - BLOCK_WIDTH) / 2 ;
		blocks[0][1] = SIZE / 4;

		return blocks;
	}
	
	public static double[][] BounceBlocksLevel3(){
		blocks = new double[16][2];
		blocks[0][1] = 3*(SIZE)/4;
		blocks[0][0] = 3 *SIZE / 4;
		
		/*
		for(int i=0; i < blocks.length; i++) {
			blocks[i][1] = 20*i + (SIZE - BLOCK_WIDTH) / 2;
			blocks[i][0] = 3 *SIZE / 4;
		}
		*/

		return blocks;
	}
	
}
