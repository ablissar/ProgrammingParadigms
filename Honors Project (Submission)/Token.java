import java.awt.Graphics;

public class Token extends Sprite {

	private int moveCounter;
	private int randDirChange;
	private boolean isRemoved = false;
	private Model.Direction currentDir;
	private String color;
	
	Token() {
        super("token.png");

        if (0 == (int) (Math.random() * 10)) {
            setImage("redToken.png");
            color = "red";
        } else if (1 == (int) (Math.random() * 10)) {
            setImage("orangeToken.png");
            color = "orange";
        } else {
            color = "gold";
        }

        setX((int) (Math.random() * 600));
        setY((int) (Math.random() * 600));
        setSpeed(5);
        moveCounter = 0;
        randDirChange = (int) (Math.random() * 75) + 25;
        currentDir = randDir();

    }

    private void addMove() { moveCounter++; }
	
	private void setDir(Model.Direction dir) {
		currentDir = dir; 
	}
	
	private Model.Direction getDir() {
		return currentDir; 
	}
	
	String getColor() {
		return color;
	}

	void setColor(String colorIn) { color = colorIn;}
	
	private boolean dirChange() {
		return (moveCounter % randDirChange == 0); 
	}
	
	public void updateImage( Graphics g ) {
		super.updateImage(g);
	}
	
	void removeToken() {
		isRemoved = true;
	}
	
	void returnToken() {
		isRemoved = false;
	}
	
	boolean isRemoved() {
		return isRemoved;
	}
	
	private void inBounds(int width, int height) {
		// Series of checks to keep player in bounds
		if( getX() >= width - 60 ) {
			setX(width - 60);
		}
		else if( getX() <= 0 ) {
			setX(0);
		}
		else if( getY() >= height - 60 ) {
			setY(height - 60);
		}
		else if( getY() <= 0 ) {
			setY(0);
		}
		else return;
		setDir(randDir());
	}
	
	public void updateState( int width, int height ) {
		inBounds(width, height);
	
    	// For each token, if the token is ready to change direction,
    	// set its direction to something random.
    	// Move it in that new direction, then increment the move counter.
		if( dirChange() ) {
			setDir( randDir() );
			moveSprite( getDir() );
			addMove();
		}
		// Otherwise, move it in the same direction and increment counter.
		else {
			moveSprite( getDir() );
			addMove();
		}
	}
	
	// Generates a random direction for tokens to use
    private Model.Direction randDir() {
    	switch( (int)(Math.random() * 8) ) {
    	case 0:
    		return Model.Direction.UP;
    	case 1:
    		return Model.Direction.DOWN;
    	case 2:
    		return Model.Direction.LEFT;
    	case 3:
    		return Model.Direction.RIGHT;
    	case 4:
    		return Model.Direction.DOWN_LEFT;
    	case 5:
    		return Model.Direction.DOWN_RIGHT;
    	case 6:
    		return Model.Direction.UP_LEFT;
    	case 7:
    		return Model.Direction.UP_RIGHT;
    	}
    	return Model.Direction.NONE;
    }
}
