package com.adambliss.game;
import java.awt.Graphics;

public class Player extends Sprite{

	public int speed;
	
	public Player() {
		super("temp.png");
		setX(0);
		setY(0);
		speed=5;
	}

	public void updateImate( Graphics g ) {
		super.updateImage(g);
	}
	
	public void updateState( int width, int height ) {
		inBounds(width, height);
	}
}
