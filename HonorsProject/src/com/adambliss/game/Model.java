package com.adambliss.game;
import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Token> tokens = new ArrayList<>();
    private Player player;
	int numTokens = 15;
	
	public static enum Direction {
		UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, NONE
	}

    Model() throws IOException 
    {
    	// Add player
    	player = new Player();
    	
    	// Add tokens
    	for(int i = 0; i < numTokens; i++) {
    		tokens.add(new Token());
    		//System.out.println(tokens.get(tokens.size()-1).getX());
    		//System.out.println(tokens.get(tokens.size()-1).getY());
    	}
    }

    public void update(Graphics g) 
    {
    	// Update image for every token (and the player)
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
    
    // Function to move player sprite
    // Takes in direction, moves player in given direction.
    // Direction comes from controller (key input)
    public void movePlayer( Direction dir ) {
    	player.moveSprite(dir);
    }
    
    // Function to move token sprites
    public void moveTokens() {
    	// For each token, if the token is ready to change direction,
    	// set its direction to something random.
    	// Move it in that new direction, then increment the move counter.
    	for( Token token : tokens ) {
    		if( token.dirChange() ) {
    			token.setDir( randDir() );
    			token.moveSprite( token.getDir() );
    			token.addMove();
    		}
    		// Otherwise, move it in the same direction and increment counter.
    		else {
    			token.moveSprite( token.getDir() );
    			token.addMove();
    		}
    	}
    }
    
    // Generates a random direction for tokens to use
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
    	player = new Player();
    }
}
