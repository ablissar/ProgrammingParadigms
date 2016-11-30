package com.adambliss.game;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class Sprite
{
	private int locationX;
	private int locationY;
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
