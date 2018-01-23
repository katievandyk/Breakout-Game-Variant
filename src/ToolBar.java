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
	private final int POINTS_OFFSET;
	private final int LEVEL_OFFSET;
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
		XOFFSET += POINTS.length() * CHAR_SIZE + SPACE;
		POINTS_OFFSET = XOFFSET;
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), POINTS_OFFSET, YOFFSET);
		XOFFSET += SPACE;
		this.LEVEL_LABEL = makeText(LEVEL, XOFFSET, YOFFSET);
		XOFFSET += LEVEL.length()*CHAR_SIZE + SPACE;
		LEVEL_OFFSET = XOFFSET;
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), LEVEL_OFFSET, YOFFSET);
		
	}
	
	// Get background of toolbar
	public Rectangle getBar() {
		return this.DISPLAY;
	}
	
	// Get points label of toolbar
	public Text getPointsLabel() {
		return this.POINTS_LABEL;
	}
	
	// Get lives label of toolbar
	public Text getLivesLabel() {
		return this.LIVES_LABEL;
	}
	
	// Get offset label of toolbar
	public int getOffset() {
		return this.LIVES_OFFSET;
	}
	
	// Get level label of toolbar
	public Text getLevelLabel() {
		return this.LEVEL_LABEL;
	}
	
	// Get points label of toolbar
	public Text getPoints() {
		return this.NUM_POINTS_TXT;
	}
	
	// Get level of toolbar
	public Text getLevel() {
		return this.CURR_LEVEL_TXT;
	}
	
	// Update level of toolbar
	public void updateLevel(int level) {
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), LEVEL_OFFSET, YOFFSET);
	}
	
	// Update points of toolbar
	public void updatePoints(int points) {
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), POINTS_OFFSET, YOFFSET);
	}

	// Make text out of points/level values
	private Text makeText(String in, double offset, int y){
		Text t = new Text(offset, y, in);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Advent Pro", 15));
		return t;
	}

}
