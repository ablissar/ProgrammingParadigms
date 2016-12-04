package com.adambliss.game;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.util.*;


class Model
{
    private ArrayList<Token> tokens = new ArrayList<>();
    private Player player;
    private Bucket bucket;
	private int numTokens = 15;
	private boolean paused = false;
	private int highScore;
	
	public void switchPaused () {
		paused = !paused;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public int getScore() {
		return highScore;
	}
	
	public boolean getFlipped() {
		return player.getFlipped();
	}
	
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

    public void saveGame( String filename ) {
    	try {
    		FileWriter fileWriter = new FileWriter(filename);
    		BufferedWriter writer = new BufferedWriter(fileWriter);
    		
    		String output = "";
        	output += player.getX() + " " + player.getY() + " " + player.getNumCaptured() + "\n";
        	output += highScore + "\n";
        	output += bucket.getX() + " " + bucket.getY() + "\n";
        	for( Token token : tokens ) {
        		output += token.getX() + " " + token.getY() + " " + ((token.isRemoved()) ? '0' : '1') + "\n";
        	}
        	
        	writer.write(output);	
    		writer.close();
    	}
    	
    	catch(IOException e) {
    		System.out.println("Could not write to file '" + filename + "'.");
    	}
    	
    }
    
    public void loadGame( String filename ) {
    	try {
    		Scanner sc = new Scanner (new File(filename));

    		player.setX(sc.nextInt());
    		player.setY(sc.nextInt());
    		player.setNumCaptured(sc.nextInt()); 
    		
    		highScore = sc.nextInt();
    		
    		bucket.setX(sc.nextInt());
    		bucket.setY(sc.nextInt());
    		
    		for( Token token : tokens ) {
    			token.setX(sc.nextInt());
    			token.setY(sc.nextInt());
    			if (sc.hasNextInt()) {
	    			if( sc.nextInt() == 0 && !token.isRemoved() ) {
	    				token.removeToken();
	    			}
	    			else if( sc.nextInt() == 1 && token.isRemoved() ) {
	    				token.returnToken();
	    			}
    			}
    		} 
    		
    		sc.close();
    	}
    	catch (IOException e) {
    		System.out.println("Could not read to file '" + filename + "'.");
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
    			if( player.overlaps(token) && !token.isRemoved() ) {
    		
    				token.removeToken();
    				if( token.getColor() == "gold" ) {
    					player.setNumCaptured(player.getNumCaptured()+1);
    				}
    				else if (token.getColor() == "red" ) {
    					player.setNumCaptured(0);
    				}
    				else if (token.getColor() == "orange" ) {
    					player.setFlipped(true);
    				}
				}
    		}
    		
    		// If player overlaps the bucket, reset numCaptured and report score
    		if( player.overlaps(bucket) ) {
    			//System.out.println("Captured " + player.getNumCaptured() + " tokens.");
    			reset();
    			
    		}
    	}
    }
    
    // Function to move player sprite
    // Takes in direction, moves player in given direction.
    // Direction comes from controller (key input)
    public void movePlayer( Direction dir ) {
    	player.moveSprite(dir);
    }
    
    public void reset()
    {
    	// Calculate score
    	if(player.getNumCaptured() > highScore) {
    		highScore = player.getNumCaptured();
    	}
    	
    	// Add player
    	player = new Player();
    	
    	// Add tokens
    	tokens.clear();
    	for(int i = 0; i < numTokens; i++) {
    		tokens.add(new Token());
    	}
    }
}
