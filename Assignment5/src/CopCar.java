import java.awt.Graphics;
import java.util.Random;

public class CopCar extends Car 
{
	private static int xRatio;
	private static int yRatio;
	
	private boolean posX = true;
	private boolean posY = true;
	
	public CopCar() 
	{
		super("copCar", 5000, new Engine( "copEngine", 30, 25 ), "cop-car.jpg" );
		Random randomnum = new Random();
		xRatio = randomnum.nextInt(11) - 5;
		yRatio = randomnum.nextInt(11) - 5;
		fillUp();
	}
	
	public void updateImage( Graphics g )
	{
		super.updateImage(g);
	}
	
	public void updateState( int width, int height )
	{
		// Use the width and height values for the window to set posX and posY
		if( getX() > width - 40 || getX() < 5 )
		{
			posX = !posX;
		}
		if( getY() > height - 75 || getY() < 10 ) 
		{
			posY = !posY;
		}

		// Use posX and posY variables to determine whether the movement should be positive or negative
		if( posX )
		{
			if( posY ) drive( 2, xRatio, yRatio );
			else drive( 2, xRatio, -yRatio );
		}
		else
		{
			if( posY ) drive( 2, -xRatio, yRatio );
			else drive( 2, -xRatio, -yRatio );
		}
	}
}
