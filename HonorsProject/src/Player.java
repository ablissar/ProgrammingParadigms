import java.awt.Graphics;

public class Player extends Sprite{

	public Player() {
		super("temp.png");
		setX(0);
		setY(0);
	}

	public void updateImate( Graphics g ) {
		super.updateImage(g);
	}
	
	public void updateState( int width, int height ) {
		inBounds(width, height);
	}
}
