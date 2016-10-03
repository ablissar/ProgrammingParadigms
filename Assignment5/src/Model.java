import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;


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
			sprite.updateState();
		}
    }
    
    public void addSprite(int x, int y)
    {
    	if( (i % 2) == 0)
    	{
    		sprites.add( new RobberCar() );
    	}
    	else
    	{
    		sprites.add( new CopCar() );
    	}
    	
    	sprites.get(sprites.size() - 1).setX(x);
    	sprites.get(sprites.size() - 1).setY(y);
    	i++;
    }
    
    public ArrayList<Sprite> getSprites()
    {
    	return sprites;
    }
}
