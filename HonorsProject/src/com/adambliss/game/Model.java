package com.adambliss.game;
import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Token> tokens = new ArrayList<>();
    private Player player;
    private Bucket bucket;
	int numTokens = 15;
	
	public static enum Direction {
		UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, NONE
	}

    Model() throws IOException 
    {
    	// Add player
    	player = new Player();
    	bucket = new Bucket();
    	
    	// Add tokens
    	for(int i = 0; i < numTokens; i++) {
    		tokens.add(new Token());
    	}
    }

    public void update(Graphics g) 
    {
    	// Update image for every token (and the player)
    	synchronized(tokens)
    	{
			for( Token token : tokens)
			{
				if( !token.isRemoved() )
					token.updateImage(g);
			}
			player.updateImage(g);
			bucket.updateImage(g);
    	}
    }
    
    public void updateScene( int width, int height )
    {
    	synchronized(tokens) {
    		// Update player state
			player.updateState( width, height );
			
    		for ( Token token : tokens ) {
    			token.updateState( width, height );
    			if( player.overlaps(token) )
    				// If the token isn't already removed, remove it
    				if( !token.isRemoved() ) {
    					token.removed();
    					player.setNumCaptured(player.getNumCaptured()+1);
				}
    		}
    		
    		// If player overlaps the bucket, reset numCaptured and report score
    		if( player.overlaps(bucket) ) {
    			System.out.println("Captured " + player.getNumCaptured() + " tokens.");
    			
    		}
    	}
    }
    
    // Function to move player sprite
    // Takes in direction, moves player in given direction.
    // Direction comes from controller (key input)
    public void movePlayer( Direction dir ) {
    	player.moveSprite(dir);
    }
    
    public void initialize()
    {
    	tokens.clear();
    	player = new Player();
    }
}
