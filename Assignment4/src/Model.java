import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;


class Model
{
    private ArrayList<Sprite> sprites = new ArrayList<>();

    Model() throws IOException 
    {
		sprites.add( new Sprite("smiley.jpg") );
    }

    public void update(Graphics g) 
    {
		for( Sprite sprite : sprites)
		{
			sprite.update(g);
		}
    }
    
    public void addSprite(int x, int y)
    {
    	sprites.add( new Sprite("smiley.jpg") );
    	sprites.get(sprites.size() - 1).setX(x);
    	sprites.get(sprites.size() - 1).setY(y);
    }
}
