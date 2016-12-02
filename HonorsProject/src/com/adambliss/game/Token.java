package com.adambliss.game;
import com.adambliss.game.Model.Direction;
import java.awt.Graphics;

public class Token extends Sprite {

	private int moveCounter;
	private int randDirChange;
	boolean isRemoved = false;
	static int numRemoved = 0;
	private Direction currentDir;
	
	public Token() {
		super("token.png");
		setX( (int)(Math.random()*600) );
		setY( (int)(Math.random()*600) );
		setSpeed(5);
		moveCounter = 0;
		randDirChange = (int)(Math.random() * 75)+25;
		currentDir = randDir();
	}

	public void addMove() { moveCounter++; }
	
	public void setDir(Direction dir) { 
		currentDir = dir; 
	}
	
	public Direction getDir() { 
		return currentDir; 
	}
	
	public boolean dirChange() { 
		return (moveCounter % randDirChange == 0); 
	}
	
	public void updateImate( Graphics g ) {
		super.updateImage(g);
	}
	
	public void removed() {
		isRemoved = true;
		numRemoved++;
	}
	
	public boolean isRemoved() {
		return isRemoved;
	}
	
	public void inBounds(int width, int height) {
		// Series of checks to keep player in bounds
		if( getX() > width - 60 || getX() < 0 ||
				getY() > height - 60 || getY() < 0) {
			setDir( randDir() );
		}
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
    public Direction randDir() {
    	switch( (int)(Math.random() * 8) ) {
    	case 0:
    		return Direction.UP;
    	case 1:
    		return Direction.DOWN;
    	case 2:
    		return Direction.LEFT;
    	case 3:
    		return Direction.RIGHT;
    	case 4:
    		return Direction.DOWN_LEFT;
    	case 5:
    		return Direction.DOWN_RIGHT;
    	case 6:
    		return Direction.UP_LEFT;
    	case 7:
    		return Direction.UP_RIGHT;
    	}
    	return Direction.NONE;
    }
}
