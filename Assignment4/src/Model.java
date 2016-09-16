import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;


class Model
{
    private ArrayList<Sprite> sprites = new ArrayList<>();

    Model() throws IOException 
    {
		sprites.add( new Sprite("smiley.jpg") );
		sprites.add( new Sprite("smiley.jpg") );
		sprites.add( new Sprite("smiley.jpg") );
		
		sprites.get(1).setX(100);
		sprites.get(1).setY(100);
		
		sprites.get(2).setX(200);
		sprites.get(2).setY(200);
    }

    public void update(Graphics g) 
    {
		for( Sprite sprite : sprites)
		{
			sprite.update(g);
		}
    }
}
