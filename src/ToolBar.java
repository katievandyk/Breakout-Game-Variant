import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ToolBar extends Driver {
	
	private String LIVES = "LIVES:";
	private String POINTS = "POINTS:";
	private String LEVEL = "LEVEL:";
	
	private Text LIVES_LABEL;
	private Text POINTS_LABEL;
	private Text LEVEL_LABEL;
	
	private int space = MARGIN/2; 

	private Rectangle DISPLAY;
	private int XOFFSET = MARGIN/2;
	private int YOFFSET = SIZE - 20;
	private Text CURR_LEVEL_TXT;
	private Text NUM_POINTS_TXT;

	public ToolBar(int points, int level) {
		this.DISPLAY = new Rectangle(0, SIZE - MARGIN, SIZE, MARGIN);
		this.XOFFSET = space;
		this.YOFFSET = SIZE - space + 5;
		this.CURR_LEVEL_TXT = null;
		this.NUM_POINTS_TXT = null;
		this.LIVES_LABEL = makeText(LIVES, XOFFSET, YOFFSET);
		this.POINTS_LABEL = makeText(POINTS, XOFFSET + 5*space, YOFFSET);
		this.LEVEL_LABEL = makeText(LEVEL, XOFFSET + 12*space, YOFFSET);
		
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), 20*space, YOFFSET);
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), 12*space, YOFFSET);
	}
	
	public Rectangle getBar() {
		return this.DISPLAY;
	}
	
	public Text getPointsLabel() {
		return this.POINTS_LABEL;
	}
	
	public Text getLivesLabel() {
		return this.LIVES_LABEL;
	}
	
	public int getOffset() {
		return this.XOFFSET;
	}
	
	public Text getLevelLabel() {
		return this.LEVEL_LABEL;
	}
	
	public Text getPoints() {
		return this.NUM_POINTS_TXT;
	}
	
	public Text getLevel() {
		return this.CURR_LEVEL_TXT;
	}
	
	public void updateLevel(int level) {
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), 10*space, YOFFSET);
	}
	
	public void updatePoints(int points) {
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), 10*space, YOFFSET);
	}

	private Text makeText(String in, double XOFFSET, int y){
		Text t = new Text(XOFFSET, y, in);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Advent Pro", 15));
		return t;
	}

	

}
