import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Sprite
{
	private int locationX;
	private int locationY;
	private int speed;
	private Image image;

	Sprite(String jpgName)
	{
		setImage(jpgName);
		locationX = 0;
		locationY = 0;
	}
	
	int getX() {	return locationX; }
	int getY() {	return locationY; }
	void setX(int x) { locationX = x; }
	void setY(int y) { locationY = y; }
	void setSpeed(int speedIn) { speed = speedIn; }
	
	void setImage(String imagePath)
	{
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
	}
	private Image getImage() { return image; }
	
    void updateImage(Graphics g)
	{
		g.drawImage(getImage(), getX(), getY(), 60, 60, null);
	}
	
	void updateState( int width, int height ) {    }
	
	boolean overlaps( Sprite s )
	{
		// If any of these conditions are false, the sprites don't overlap
		return (getX() < s.getX() + 60) && getX() + 60 > s.getX()
				&& getY() < s.getY() + 60 && getY() + 60 > s.getY();
	}
	
	void moveSprite(Model.Direction dir) {
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
	
	Model.Direction flipDirection (Model.Direction dirIn) {
    	if(dirIn == Model.Direction.UP) return Model.Direction.DOWN;
    	else if (dirIn == Model.Direction.DOWN) return Model.Direction.UP;
    	else if (dirIn == Model.Direction.LEFT) return Model.Direction.RIGHT;
    	else if (dirIn == Model.Direction.RIGHT) return Model.Direction.LEFT;
    	else if (dirIn == Model.Direction.UP_LEFT) return Model.Direction.UP_RIGHT;
    	else if (dirIn == Model.Direction.UP_RIGHT) return Model.Direction.UP_LEFT;
    	else if (dirIn == Model.Direction.DOWN_LEFT) return Model.Direction.DOWN_RIGHT;
    	else if (dirIn == Model.Direction.DOWN_RIGHT) return Model.Direction.DOWN_LEFT;
    	else return Model.Direction.NONE;
    }
}
