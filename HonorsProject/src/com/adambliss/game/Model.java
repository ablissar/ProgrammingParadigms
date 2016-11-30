package com.adambliss.game;
import java.awt.Graphics;
import java.io.IOException;
import java.util.*;


class Model
{
    private ArrayList<Token> tokens = new ArrayList<>();
    Player player = new Player();
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
    
    public void movePlayer(Direction dir) {
    	switch(dir) {
    	case UP:
    		player.setY(player.getY()-player.speed);
    		break;
    	case DOWN:
    		player.setY(player.getY()+player.speed);
    		break;
    	case LEFT:
    		player.setX(player.getX()-player.speed);
    		break;
    	case RIGHT:
    		player.setX(player.getX()+player.speed);
    		break;
    	case UP_LEFT:
    		player.setX( (int) (player.getX()-(player.speed / Math.sqrt(2))) );
    		player.setY( (int) (player.getY()-(player.speed / Math.sqrt(2))) );
    		break;
    	case UP_RIGHT:
    		player.setX( (int) (player.getX()+(player.speed / Math.sqrt(2))) );
    		player.setY( (int) (player.getY()-(player.speed / Math.sqrt(2))) );
    		break;
    	case DOWN_LEFT:
    		player.setX( (int) (player.getX()-(player.speed / Math.sqrt(2))) );
    		player.setY( (int) (player.getY()+(player.speed / Math.sqrt(2))) );
    		break;
    	case DOWN_RIGHT:
    		player.setX( (int) (player.getX()+(player.speed / Math.sqrt(2))) );
    		player.setY( (int) (player.getY()+(player.speed / Math.sqrt(2))) );
    		break;
    	case NONE:
    		break;
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
