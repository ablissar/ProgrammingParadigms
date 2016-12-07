package com.adambliss.game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.adambliss.game.Model.Direction;

//import javax.swing.SwingUtilities;

class Controller implements MouseListener, KeyListener
{
    Model model;
    View view;
    
    boolean upPressed, downPressed, leftPressed, rightPressed;
    
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;

    Controller() throws IOException, Exception {
        model = new Model();
        view = new View(this);
        Thread thread = new Thread( new SpriteMover(model,view) );
        thread.start();
        
        /*
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                                 KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem); */
    }

    public void update(Graphics g) {
        model.update(g);
    }

    public void mousePressed(MouseEvent e) {    }

    public void mouseReleased(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {    }

    public void keyTyped(KeyEvent e)  {   
    	// Pause/unpause the game
    	if( e.getKeyChar() == 'p' || e.getKeyChar() == 'P' ) {
    		model.switchPaused();
    		view.switchInstructions();
    	}
    	
    	// Save the game
    	if( e.getKeyChar() == 's' || e.getKeyChar() == 'P' ) {
    		if(model.isPaused()) {
    			model.saveGame("savedGame.txt");
    		}
    	}
    	
    	// Load last saved game
    	if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L' ) {
    		model.loadGame("savedGame.txt");
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
        if( !model.isPaused() ) {
	        model.movePlayer(getDirection());
	        model.updateScene( view.getWidth(), view.getHeight() );
			view.repaint();
        }
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
   
    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        // System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}
