import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.util.*;

@SuppressWarnings("SameParameterValue")
class Model
{
    private final ArrayList<Token> tokens = new ArrayList<>();
    private Player player;
    private Bucket bucket;
	private int numTokens = 15;
	private boolean paused = false;
	private int highScore;

	void switchPaused() {
		paused = !paused;
	}

	boolean isPaused() {
		return paused;
	}

	int getScore() {
		return highScore;
	}

	public enum Direction {
		UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, NONE
	}

    Model() throws IOException
    {
    	// Add player & bucket
    	player = new Player();
    	bucket = new Bucket();

    	// Add tokens
    	for(int i = 0; i < numTokens; i++) {
    		tokens.add(new Token());
    	}
    }

    void saveGame(String filename) {
    	try {
    		FileWriter fileWriter = new FileWriter(filename);
    		BufferedWriter writer = new BufferedWriter(fileWriter);

    		String output = "";
        	output += player.getX() + " " + player.getY() + " " + player.getNumCaptured() + "\n";
        	output += highScore + "\n";
        	output += bucket.getX() + " " + bucket.getY() + "\n";
        	for( Token token : tokens ) {
        		output += token.getX() + " " + token.getY() + " " + ((token.isRemoved()) ? '0' : '1') + " " + token.getColor() + "\n";
        	}

        	writer.write(output);
    		writer.close();
    	}

    	catch(IOException e) {
    		System.out.println("Could not write to file '" + filename + "'.");
    	}

    }

    void loadGame(String filename) {
    	try {
    		Scanner sc = new Scanner (new File(filename));

    		if(!sc.hasNext()) {
    			sc.close();
    			return;
    		}

    		player.setX(sc.nextInt());
    		player.setY(sc.nextInt());
    		player.setNumCaptured(sc.nextInt());

    		highScore = sc.nextInt();

    		bucket.setX(sc.nextInt());
    		bucket.setY(sc.nextInt());

    		for( Token token : tokens ) {
    			token.setX(sc.nextInt());
    			token.setY(sc.nextInt());
                //noinspection RedundantConditionalExpression
                boolean isRemoved = (sc.nextInt() == 0) ? false : true;
    			if( !isRemoved && !token.isRemoved() ) {
    				token.removeToken();
    			}
    			else if( isRemoved && token.isRemoved() ) {
    				token.returnToken();
    			}
    			token.setColor(sc.next());
    		}

    		sc.close();
    	}
    	catch (IOException e) {
    		System.out.println("Could not read to file '" + filename + "'.");
    	}
    }


    void update(Graphics g)
    {
    	// Update image for every token (and the player)
    	synchronized(tokens)
    	{
        player.updateImage(g);
  			bucket.updateImage(g);
  			for( Token token : tokens)
  			{
  				if( !token.isRemoved() )
  					token.updateImage(g);
  			}
      	}
    }

    void updateScene(int width, int height)
    {
    	synchronized(tokens) {
    		// Update player state
			player.updateState( width, height );

    		for ( Token token : tokens ) {
    			token.updateState( width, height );
    			if( player.overlaps(token) && !token.isRemoved() ) {

    				token.removeToken();
                    switch (token.getColor()) {
                        case "gold":
                            player.setNumCaptured(player.getNumCaptured() + 1);
                            break;
                        case "red":
                            player.setNumCaptured(0);
                            break;
                        case "orange":
                            player.setFlipped();
                            break;
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

    void movePlayer(Direction dir) {
    	if(player.getFlipped()) {
    		player.moveSprite(player.flipDirection(dir));
    		player.swapImage(player.flipDirection(dir));
    	}
    	else {
    		player.moveSprite(dir);
    		player.swapImage(dir);
    	}
    }

    private void reset()
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
