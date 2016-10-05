import java.awt.Graphics;
import java.util.Random;

public class RobberCar extends Car
{

	private int xRatio;
	private int yRatio;
	
	private boolean isCaptured = false;
	static int numCaptured = 0;
	
	public RobberCar()
	{
		super("robberCar", 5000, new Engine( "robberEngine", 20, 50 ), "red-car.jpg" );
		Random randomnum = new Random();
		xRatio = randomnum.nextInt(11) - 5;
		yRatio = randomnum.nextInt(11) - 5;
		fillUp();
	}

	public void updateImage( Graphics g )
	{
		super.updateImage(g);
	}
	
	public void updateState( int height, int width )
	{
		if (!isCaptured)
		{
			drive( 40, xRatio, yRatio );
		}
	}
	
	public void captured()
	{
		isCaptured = true;
		numCaptured++;
		setImage("jail.jpg");
	}
	
	public boolean isCaptured()
	{
		return isCaptured;
	}
}
