import java.awt.Graphics;

public class Bank extends Sprite 
{
	public Bank() 
	{
		super("bank.png");
		
		// Position is constant at (300, 300)
		setX( 300 );
		setY( 300 );
	}
	
	public void updateImage( Graphics g )
    {
    	super.updateImage( g );
    }
}
