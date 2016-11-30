package com.adambliss.game;
import com.adambliss.game.Model.Direction;
import java.awt.Graphics;

public class Token extends Sprite {

	private int moveCounter;
	private double randDirChange;
	boolean isRemoved = false;
	static int numRemoved = 0;
	private Direction currentDir;
	
	public Token(int x, int y) {
		super("token.jpeg");
		setX(x);
		setY(y);
		moveCounter = 0;
		randDirChange = Math.random() * 50;
	}

	public void addMove() {
		moveCounter++;
	}
	
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
	
	public void updateState( int width, int height ) {
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
