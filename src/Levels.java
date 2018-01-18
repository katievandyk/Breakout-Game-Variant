
public class Levels extends Game{
	
	static int[][] blocks;
	
	public static int[][] Level1(){
		blocks = new int[28][2];
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
	
	public static int[][] Level2(){
		
		
		blocks = new int[1][2];
		blocks[0][0] = SIZE / 2;
		blocks[0][1] = SIZE / 2;
		
		return blocks;
	
	}
	
	public static int[][] Level3(){
		return null;
	}
	
	
}
