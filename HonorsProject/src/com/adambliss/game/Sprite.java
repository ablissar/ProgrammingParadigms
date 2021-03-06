package com.adambliss.game;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

import com.adambliss.game.Model.Direction;

import java.io.File;
import java.io.IOException;


class Sprite
{
	private int locationX;
	private int locationY;
	private int speed;
	private Image image;

	public Sprite(String jpgName)
	{
		setImage(jpgName);
		locationX = 0;
		locationY = 0;
	}
	
	public int getX() {	return locationX; }
	public int getY() {	return locationY; }
	public void setX(int x) { locationX = x; }
	public void setY(int y) { locationY = y; }
	public void setSpeed(int speedIn) { speed = speedIn; }
	
	public void setImage(String imagePath) 
	{
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
	}
	public Image getImage() { return image; }	
	
	public void updateImage(Graphics g) 
	{
		g.drawImage(getImage(), getX(), getY(), 60, 60, null);
	}
	
	public void updateState( int width, int height ) {    }
	
	public boolean overlaps( Sprite s )
	{
		// If any of these conditions are false, the sprites don't overlap
		return (getX() < s.getX() + 60) && getX() + 60 > s.getX()
				&& getY() < s.getY() + 60 && getY() + 60 > s.getY();
	}
	
	public void moveSprite(Direction dir) {
    	switch(dir) {
    	case UP:
    		setY(getY()-speed);
    		break;
    	case DOWN:
    		setY(getY()+speed);
    		break;
    	case LEFT:
    		setX(getX()-speed);
    		break;
    	case RIGHT:
    		setX(getX()+speed);
    		break;
    	case UP_LEFT:
    		setX( (int) (getX()-(speed / Math.sqrt(2))) );
    		setY( (int) (getY()-(speed / Math.sqrt(2))) );
    		break;
    	case UP_RIGHT:
    		setX( (int) (getX()+(speed / Math.sqrt(2))) );
    		setY( (int) (getY()-(speed / Math.sqrt(2))) );
    		break;
    	case DOWN_LEFT:
    		setX( (int) (getX()-(speed / Math.sqrt(2))) );
    		setY( (int) (getY()+(speed / Math.sqrt(2))) );
    		break;
    	case DOWN_RIGHT:
    		setX( (int) (getX()+(speed / Math.sqrt(2))) );
    		setY( (int) (getY()+(speed / Math.sqrt(2))) );
    		break;
    	case NONE:
    		break;
    	}
    }
	
	public Direction flipDirection (Direction dirIn) {
    	if(dirIn == Direction.UP) return Direction.DOWN;
    	else if (dirIn == Direction.DOWN) return Direction.UP;
    	else if (dirIn == Direction.LEFT) return Direction.RIGHT;
    	else if (dirIn == Direction.RIGHT) return Direction.LEFT;
    	else if (dirIn == Direction.UP_LEFT) return Direction.UP_RIGHT;
    	else if (dirIn == Direction.UP_RIGHT) return Direction.UP_LEFT;
    	else if (dirIn == Direction.DOWN_LEFT) return Direction.DOWN_RIGHT;
    	else if (dirIn == Direction.DOWN_RIGHT) return Direction.DOWN_LEFT;
    	else return Direction.NONE;
    }
}
