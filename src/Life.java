import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Life extends Driver {
	
	ImageView DISPLAY;
	double X;
	double Y;
	
	// Constructor 
	public Life(int x, int y) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(LIFE_IMG));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.X = x;
		this.Y = y;
	}
	
	public void removeLife() {
		root.getChildren().remove(this.DISPLAY);
	}
	

}
