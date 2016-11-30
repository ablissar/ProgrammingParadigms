package com.adambliss.game;
import java.awt.Graphics;

public class Player extends Sprite{
	
	public Player() {
		super("temp.png");
		setX(0);
		setY(0);
		setSpeed(20);
	}

	public void updateImate( Graphics g ) {
		super.updateImage(g);
	}
	
	public void updateState( int width, int height ) {
		inBounds(width, height);
	}
	
	public void inBounds(int width, int height) {
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
