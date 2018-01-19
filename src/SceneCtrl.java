import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneCtrl extends Driver{
	
	public static void createScene(Stage stage) {
		myScene = new Scene(root, SIZE, SIZE);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
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
	
		// Initialize toolbar (EVENTUALLY)
		int x = MARGIN;
		for(int i=0; i < NUM_LIVES; i++) {
			Life l = new Life(x, SIZE - MARGIN);
			x += 20;
			lives.add(l);
			root.getChildren().add(l.DISPLAY);
		}
		
		myPaddle = new Paddle(SIZE/2, SIZE - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
		root.getChildren().add(myPaddle.DISPLAY);
	}
	
	


}
