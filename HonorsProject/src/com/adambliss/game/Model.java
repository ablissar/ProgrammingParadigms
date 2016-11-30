package com.adambliss.game;
import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Token> tokens = new ArrayList<>();
    private Player player = new Player();
	int i = 0;
	
	public static enum Direction {
		UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, NONE
	}

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
    
    public void movePlayer( Direction dir ) {
    	player.moveSprite(dir);
    }
    
    public void moveTokens() {
    	for( Token token : tokens ) {
    		if( token.dirChange() ) {
    			token.setDir( randDir() );
    			token.moveSprite( token.getDir() );
    			token.addMove();
    		}
    		else {
    			token.moveSprite( token.getDir() );
    			token.addMove();
    		}
    	}
    }
    
    public Direction randDir() {
    	switch((int)Math.random() * 8) {
    	case 0:
    		return Direction.UP;
    	case 1:
    		return Direction.DOWN;
    	case 2:
    		return Direction.LEFT;
    	case 3:
    		return Direction.RIGHT;
    	case 4:
    		return Direction.DOWN_LEFT;
    	case 5:
    		return Direction.DOWN_RIGHT;
    	case 6:
    		return Direction.UP_LEFT;
    	case 7:
    		return Direction.UP_RIGHT;
    	}
    	return Direction.NONE;
    }
    
    public void initialize()
    {
    	tokens.clear();
    	//sprites.add( new Bank() );
    	//RobberCar.numCaptured = 0;
    	//RobberCar.numEscaped = 0;
    }
}
