import java.awt.Graphics;
import java.util.Random;

public class RobberCar extends Car
{

	private int xRatio;
	private int yRatio;
	
	public RobberCar()
	{
		super("robberCar", 20, new Engine( "robberEngine", 20, 50 ), "red-car.jpg" );
		Random randomnum = new Random();
		xRatio = randomnum.nextInt(11) - 5;
		yRatio = randomnum.nextInt(11) - 5;
		fillUp();
	}

	public void update( Graphics g )
	{
		super.update(g);
		drive( 40, xRatio, yRatio );
	}
}
