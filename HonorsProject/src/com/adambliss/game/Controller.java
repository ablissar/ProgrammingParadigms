package com.adambliss.game;
import java.awt.Graphics;

import java.io.IOException;

import com.adambliss.game.Model.Direction;

import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

//import javax.swing.SwingUtilities;

class Controller implements MouseListener, KeyListener
{
    Model model;
    View view;
    
    boolean upPressed, downPressed, leftPressed, rightPressed;

    Controller() throws IOException, Exception {
        model = new Model();
        view = new View(this);
    }

    public void update(Graphics g) {
        model.update(g);
    }

    public void mousePressed(MouseEvent e) {    }

    public void mouseReleased(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {    }

    public void keyTyped(KeyEvent e) 
    {   
    	// On keypress 'n', print the number of robbers who have been captured 
    	// and who have escaped
    	if( e.getKeyChar() == 'n' || e.getKeyChar() == 'N' )
    	{
    		System.out.print("Robbers captured: ");
    		System.out.println("Robbers escaped: ");
    	}
    	
    	// On keypress 'r', reset program (empty sprite list, reset counters for captured/escaped
    	// robbers, redraw image)
    	if( e.getKeyChar() == 'r' || e.getKeyChar() == 'R' )
    	{
    		model.initialize();
    		view.repaint();
    	}
    
    	// On keypress 's', create new thread
    	if( e.getKeyChar() == 's' || e.getKeyChar() == 'S' )
    	{
    		Thread thread = new Thread( new SpriteMover(model, view) );
    		thread.start();
    	}
    }
    public void keyPressed(KeyEvent e) {  
    	int keyCode = e.getKeyCode();
        switch( keyCode ) { 
            case KeyEvent.VK_UP:
            	upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT :
                rightPressed = true;
                break;
        }
        model.movePlayer(getDirection());
        model.updateScene( view.getWidth(), view.getHeight() );
		view.repaint();
    }
    
    public Direction getDirection() {
    	if( upPressed  && !downPressed) {
    		if( !leftPressed && !rightPressed )
    			return Direction.UP;
    		else if( leftPressed && !rightPressed )
    			return Direction.UP_LEFT;
    		else if( !leftPressed && rightPressed )
    			return Direction.UP_RIGHT;
    	}
    	else if( downPressed && !upPressed) {
    		if( !leftPressed && !rightPressed )
    			return Direction.DOWN;
    		else if( leftPressed && !rightPressed )
    			return Direction.DOWN_LEFT;
    		else if( !leftPressed && rightPressed )
    			return Direction.DOWN_RIGHT;
    	}
    	else if( leftPressed && !rightPressed) {
    		return Direction.LEFT;
    	}
    	else if( !leftPressed && rightPressed ) {
    		return Direction.RIGHT;
    	}
    	return Direction.NONE;
    }
    
    public void keyReleased(KeyEvent e) {  
    	int keyCode = e.getKeyCode();
        switch( keyCode ) { 
            case KeyEvent.VK_UP:
            	upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
            	downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
            	leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
            	rightPressed = false;
                break;
         }
    }
    
    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        // System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}
