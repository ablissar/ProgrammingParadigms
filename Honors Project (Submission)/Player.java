import java.awt.Graphics;

class Player extends Sprite{
	
	private int numCaptured;
	private boolean flipped;
	
	Player() {
		super("rightPlayer.png");
		setX(0);
		setY(0);
		setSpeed(20);
		numCaptured = 0;
	}

	void setNumCaptured(int numCapturedIn) {
		numCaptured = numCapturedIn;
	}
	
	int getNumCaptured() {
		return numCaptured;
	}

	void setFlipped() {
		flipped = true;
	}
	
	boolean getFlipped() {
		return flipped;
	}
	
	void updateImage( Graphics g ) {
		super.updateImage(g);
	}
	
	void updateState( int width, int height ) {
		inBounds(width, height);
	}
	
	void swapImage(Model.Direction dir) {
		if( dir == Model.Direction.LEFT || dir == Model.Direction.UP_LEFT || dir == Model.Direction.DOWN_LEFT) {
    		setImage("leftPlayer.png");
    	}
    	else {
    		setImage("rightPlayer.png");
    	}
	}

	private void inBounds(int width, int height) {
		// Series of checks to keep player in bounds
		if( getX() > width - 60 ) {
			setX(width - 60);
		}
		if( getX() < 0 ) {
			setX(0);
		}
		if( getY() > height - 60 ) {
			setY(height - 60);
		}
		if( getY() < 0 ) {
			setY(0);
		}
	}
}
