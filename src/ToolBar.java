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
	
	private int SPACE = 20;
	private int CHAR_SIZE = 12;
	private int MAX_LIVES = 3;
	private Rectangle DISPLAY;
	private int XOFFSET;
	private int YOFFSET;
	private int LIVES_OFFSET;
	private Text CURR_LEVEL_TXT;
	private Text NUM_POINTS_TXT;

	/**
	 * Constructor
	 */
	public ToolBar(int points, int level) {
		this.DISPLAY = new Rectangle(0, SIZE - X_MARGIN, SIZE, X_MARGIN);
		this.XOFFSET = SPACE;
		this.YOFFSET = SIZE - SPACE;
		this.CURR_LEVEL_TXT = null;
		this.NUM_POINTS_TXT = null;
		this.LIVES_OFFSET = LIVES.length() * CHAR_SIZE;
		this.LIVES_LABEL = makeText(LIVES, XOFFSET, YOFFSET);
		
		XOFFSET += LIVES.length()*SPACE + SPACE * MAX_LIVES;
		this.POINTS_LABEL = makeText(POINTS, XOFFSET, YOFFSET);
		XOFFSET += POINTS.length() * SPACE;
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), XOFFSET, YOFFSET);
		XOFFSET += SPACE;
		this.LEVEL_LABEL = makeText(LEVEL, XOFFSET, YOFFSET);
		XOFFSET += LEVEL.length()*CHAR_SIZE;
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), XOFFSET, YOFFSET);
		XOFFSET += SPACE;
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
		return this.LIVES_OFFSET;
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
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), 10*SPACE, YOFFSET);
	}
	
	public void updatePoints(int points) {
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), 10*SPACE, YOFFSET);
	}

	private Text makeText(String in, double XOFFSET, int y){
		Text t = new Text(XOFFSET, y, in);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Advent Pro", 15));
		return t;
	}

	

}
