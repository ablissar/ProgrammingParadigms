package com.adambliss.game;
import java.awt.Graphics;
import java.util.Random;

public class Token extends Sprite {

	private int direction;
	boolean isRemoved = false;
	static int numRemoved = 0;
	
	public Token(int x, int y) {
		super("token.jpeg");
		setX(x);
		setY(y);
	}

	public void updateImate( Graphics g ) {
		super.updateImage(g);
	}
	
	public void updateState( int width, int height ) {
		// Move token
		Random rand = new Random();
		direction = rand.nextInt(4);
		switch(direction) {
		case 0:
			setX(getX()+1);
		case 1:
			setX(getX()-1);
		case 2:
			setY(getY()+1);
		case 3:
			setY(getY()-1);
		}
		inBounds(width, height);
	}
	
	public void removed() {
		isRemoved = true;
		numRemoved++;
	}
	
	public boolean isRemoved() {
		return isRemoved;
	}
}
