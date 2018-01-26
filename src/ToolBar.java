import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * ToolBar object contains spacing/elements of toolbar to be displayed on screen
 * Dependencies: Driver
 * 
 * @author Katherine Van Dyk
 *
 */
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
	 * Constructor for Toolbar
	 * 
	 * @param points		Number of points to be displayed on screen
	 * @param level		Current level to be displayed on screen
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
	
	/**
	 * Returns rectangle (background of toolbar)
	 * 
	 * @return	Rectangle, background
	 */
	public Rectangle getBar() {
		return this.DISPLAY;
	}
	
	/**
	 * Returns points text 
	 * 
	 * @return Text		Current amt of points
	 */
	public Text getPointsLabel() {
		return this.POINTS_LABEL;
	}
	
	/**
	 * Returns "LIVES:" label
	 * 
	 * @return Text		Label
	 */
	public Text getLivesLabel() {
		return this.LIVES_LABEL;
	}
	
	/**
	 * Returns offset needed to display curr lives in right position
	 * 
	 * @return int		Spacing for lives display
	 */
	public int getOffset() {
		return this.LIVES_OFFSET;
	}
	
	/**
	 * Returns "LEVEL:" label
	 * 
	 * @return Text 		Label	
	 */
	public Text getLevelLabel() {
		return this.LEVEL_LABEL;
	}
	
	/**
	 * Returns current number of pts txt
	 * 
	 * @return	Text		Current num of pts
	 */
	public Text getPoints() {
		return this.NUM_POINTS_TXT;
	}

	/**
	 * Returns current level
	 * 
	 * @return Level		Current level
	 */
	public Text getLevel() {
		return this.CURR_LEVEL_TXT;
	}
	
	/**
	 * Updates level text
	 * 
	 * @param level		Level to display
	 */
	public void updateLevel(int level) {
		this.CURR_LEVEL_TXT = makeText(Integer.toString(level), LEVEL_OFFSET, YOFFSET);
	}
	
	/**
	 * Updates points text
	 * 
	 * @param points		Points to display
	 */
	public void updatePoints(int points) {
		this.NUM_POINTS_TXT = makeText(Integer.toString(points), POINTS_OFFSET, YOFFSET);
	}

	/**
	 * makes Text object in desired position
	 * 
	 * @param in			Text to be displayed
	 * @param offset		X-offset of text
	 * @param y			Y-position of text
	 * @return Text		Text object to be displayed
	 */
	private Text makeText(String in, double offset, int y){
		Text t = new Text(offset, y, in);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Advent Pro", 15));
		return t;
	}

}
