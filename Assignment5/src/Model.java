import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Sprite> sprites = new ArrayList<>();
	int i = 0;

    Model() throws IOException 
    {
    	sprites.add( new Bank() );
    }

    public void update(Graphics g) 
    {
		for( Sprite sprite : sprites)
		{
			sprite.updateImage(g);
		}
    }
    
    public void addSprite(int x, int y)
    {
    	if( (i % 2) == 0)
    	{
    		sprites.add( new RobberCar() );
    		sprites.get(sprites.size() - 1).setX(300);
    		sprites.get(sprites.size() - 1).setY(300);
    	}
    	else
    	{
    		sprites.add( new CopCar() );
    		sprites.get(sprites.size() - 1).setX(x);
        	sprites.get(sprites.size() - 1).setY(y);
    	}
    	i++;
    }
    
    public void updateScene( int width, int height )
    {
    	for( Sprite sprite : sprites )
    	{
    		sprite.updateState( width, height );
    		
    		// Iterate over every other sprite in the ArrayList
    		for ( Sprite sprite2 : sprites )
    		{
    			// Check if the sprites are of different types
    			// and if they overlap
    			if( (sprite instanceof CopCar)
    					&& (sprite2 instanceof RobberCar) 
    					&& sprite.overlaps(sprite2))
    			{
    				// If the robber isn't already captured, capture it
    				if( !((RobberCar)sprite2).isCaptured() )
    				{
    					((RobberCar)sprite2).captured();
    				}
    			}
    		}
    		
    		// If the sprite is a robber car and it's escaped,
    		// remove it from the list
    		Iterator<Sprite> iter = sprites.iterator();
    		while( iter.hasNext() )
    		{
    			Sprite s = iter.next();
    			if( s instanceof RobberCar )
    			{
    				if( ((RobberCar)s).isEscaped() )
    				{
    					iter.remove();
    				}
    			}
    		}
    	}
    }
}
