import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SceneCtrl extends Driver{

	static String INSTRUCTIONS = "WELCOME TO BREAKOUT!";
	static String LOSE = "YOU LOST:(";
	static String WIN = "YOU WON!!:)";
	static String LIVES = "LIVES:";
	static String POINTS = "POINTS:";
	static String LEVEL = "LEVEL:";
			
	public static void createScene(Stage stage) {
		myScene = new Scene(root, SIZE, SIZE);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
	}
	
	public static void createStartScreen() {
		Text t = new Text(MARGIN, MARGIN, INSTRUCTIONS);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		root.getChildren().add(t);
		myScene.setOnKeyPressed(f -> checkKey(f.getCode()));
		
	}
	
	public static void createLoseScreen() {
		root.getChildren().clear();
		Text t = new Text(MARGIN, MARGIN, LOSE);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		root.getChildren().add(t);
	}
	
	public static void createWinScreen() {
		root.getChildren().clear();
		Text t = new Text(MARGIN, MARGIN, WIN);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		root.getChildren().add(t);
		
	}
	
	private static void checkKey(KeyCode f) {
		if(f == KeyCode.ENTER) {
			root.getChildren().clear();
			CURR_LEVEL++;
			updateLevel();
			addObjects();
			myScene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));
		}
	}
	
	public static void createToolbar() {
		int x = MARGIN/2;
		int y = SIZE - 20;

		//Create bar
		Rectangle bar = new Rectangle(0, SIZE - MARGIN, SIZE, MARGIN);
		root.getChildren().add(bar);
		root.getChildren().add(makeText(LIVES, x, y));
		
		x += MARGIN;
		for(int i=0; i < NUM_LIVES; i++) {
			Life l = new Life(x, SIZE - 30);
			x += 20;
			lives.add(l);
			root.getChildren().add(l.DISPLAY);
		}
		
		x += MARGIN/2;
		root.getChildren().add(makeText(POINTS, x, SIZE - 20));
		x += MARGIN*3/2;
		NUM_POINTS_TXT = makeText(Integer.toString(NUM_POINTS), x, SIZE - 20);
		root.getChildren().add(NUM_POINTS_TXT);
		x += MARGIN;
		root.getChildren().add(makeText(LEVEL, x, SIZE - 20));	
		x += MARGIN*3/2;
		CURR_LEVEL_TXT = makeText(Integer.toString(CURR_LEVEL), x, SIZE - 20);
		root.getChildren().add(CURR_LEVEL_TXT);
	}
	
	public static void updatePointsText() {
		root.getChildren().remove(NUM_POINTS_TXT);
		NUM_POINTS_TXT.setText(Integer.toString(NUM_POINTS));
		root.getChildren().add(NUM_POINTS_TXT);
	}
	
	public static void updateLevelText() {
		root.getChildren().remove(CURR_LEVEL_TXT);
		CURR_LEVEL_TXT.setText(Integer.toString(CURR_LEVEL));
		root.getChildren().add(CURR_LEVEL_TXT);
	}

	public static void setBackgroundImage(javafx.scene.image.Image image) {
		ImagePattern pattern = new ImagePattern(image);
		myScene.setFill(pattern); 
	}
	
	public static void playAnimation() {		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public static void addObjects() {
		// Initialize main bouncer
		bouncers.add(new Bouncer (MOVER_SPEED));
		bouncers.get(0).reset(SIZE, SIZE);
		root.getChildren().add(bouncers.get(0).DISPLAY);
		myPaddle = new Paddle(SIZE/2, SIZE - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
		root.getChildren().add(myPaddle.DISPLAY);
		createToolbar();
	}
	
	public static Text makeText(String in, int x, int y){
		Text t = new Text(x, y, in);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Advent Pro", 15));
		return t;
	}
	

}
