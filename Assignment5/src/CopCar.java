import java.awt.Graphics;
import java.util.Random;

public class CopCar extends Car 
{
	private static int xRatio;
	private static int yRatio;
	
	public CopCar() 
	{
		super("copCar", 30, new Engine( "copEngine", 30, 25 ), "cop-car.jpg" );
		Random randomnum = new Random();
		xRatio = randomnum.nextInt(11) - 5;
		yRatio = randomnum.nextInt(11) - 5;
		fillUp();
	}
	
	public void updateImage( Graphics g )
	{
		super.updateImage(g);
	}
	
	public void updateState()
	{
		drive( 20, xRatio, yRatio );
	}
}
