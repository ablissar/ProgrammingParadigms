import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Sprite> sprites = new ArrayList<>();
	int i = 0;

    Model() throws IOException 
    {
    	// Always add a Bank object
    	// sprites.add( new Bank() );
    }

    public void update(Graphics g) 
    {
    	// Update image for every sprite in list
    	synchronized(sprites)
    	{
			for( Sprite sprite : sprites)
			{
				sprite.updateImage(g);
			}
    	}
    }
    
    public void addSprite(int x, int y)
    {
    	if( (i % 2) == 0)
    	{
    		// Add new RobberCar and position it at the bank (300,300)
    		//sprites.add( new RobberCar() );
    		sprites.get(sprites.size() - 1).setX(300);
    		sprites.get(sprites.size() - 1).setY(300);
    	}
    	else
    	{
    		// Add new CopCar at site of click
    		//sprites.add( new CopCar() );
    		sprites.get(sprites.size() - 1).setX(x);
        	sprites.get(sprites.size() - 1).setY(y);
    	}
    	// Increment i to keep track of whether the next car is a cop or robber
    	i++;
    }
    
    public void updateScene( int width, int height )
    {
    	synchronized(sprites)
    	{
    		// Iterate over every other sprite in the ArrayList
			Iterator<Sprite> iter = sprites.iterator();
			while( iter.hasNext() )
			{
				Sprite sprite = iter.next();
				// Update state for each sprite
				sprite.updateState( width, height );
	    		
	    		synchronized(sprites)
	    		{
		    		for ( Sprite sprite2 : sprites )
		    		{
		    			// Check if the sprites are of different types
		    			// and if they overlap
		    			//if( (sprite instanceof CopCar) && (sprite2 instanceof RobberCar) 
		    					//&& sprite.overlaps(sprite2) )
		    			{
		    				// If the robber isn't already captured, capture it
		    				//if( !((RobberCar)sprite2).isCaptured() )
		    				{
		    					//((RobberCar)sprite2).captured();
		    				}
		    			}
		    		}
	    		}
				
	    		// If the sprite is a robber car and it's escaped,
				// remove it from the list
				//if( sprite instanceof RobberCar )
				{
					//if( ((RobberCar)sprite).isEscaped() )
					{
						iter.remove();
					}
				}
			}
    	}
    }
    
    public void initialize()
    {
    	sprites.clear();
    	//sprites.add( new Bank() );
    	//RobberCar.numCaptured = 0;
    	//RobberCar.numEscaped = 0;
    }
}
