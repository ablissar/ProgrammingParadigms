import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Token> tokens = new ArrayList<>();
    Player player = new Player();
	int i = 0;

    Model() throws IOException 
    {
    	// Reset player 
    	player.setX(0);
    	player.setY(0);
    }

    public void update(Graphics g) 
    {
    	// Update image for every sprite in list
    	synchronized(tokens)
    	{
			for( Token token : tokens)
			{
				token.updateImage(g);
			}
			player.updateImage(g);
    	}
    }
    
    public void updateScene( int width, int height )
    {
    	synchronized(tokens) {
    		// Update player state
			player.updateState( width, height );

	    		for ( Token token : tokens ) {
	    			if( player.overlaps(token) )
	    				// If the token isn't already removed, remove it
	    				if( !token.isRemoved() ) {
	    					token.removed();
    				}
    			}
    	}
    }
    
    public void initialize()
    {
    	tokens.clear();
    	//sprites.add( new Bank() );
    	//RobberCar.numCaptured = 0;
    	//RobberCar.numEscaped = 0;
    }
}
