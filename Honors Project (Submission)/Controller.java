import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

class Controller implements MouseListener, KeyListener
{
    private Model model;
    private View view;
    
    private boolean upPressed, downPressed, leftPressed, rightPressed;


    @SuppressWarnings("Convert2Lambda")
    private Controller() throws Exception {
        model = new Model();
        view = new View(this);
        Thread thread = new Thread( new SpriteMover(model,view) );
        thread.start();


        //Build the menu.
        JMenu menu = new JMenu("Menu");
        JMenu fileMenu = new JMenu("File");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(fileMenu);
        view.setJMenuBar(menuBar);

        //a group of JMenuItems
        JMenuItem pauseButton = new JMenuItem("Pause/Display Instructions");
        menu.add(pauseButton);
        JMenuItem quitButton = new JMenuItem("Quit");
        menu.add(quitButton);

        JMenuItem saveButton = new JMenuItem("Save");
        fileMenu.add(saveButton);
        JMenuItem loadButton = new JMenuItem("Load");
        fileMenu.add(loadButton);

        // Listeners to handle menu events
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.switchPaused();
                view.switchInstructions();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.close();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.saveGame("savedGame.txt");
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.loadGame("savedGame.txt");
            }
        });
    }

    void update(Graphics g) {
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

    private Model.Direction getDirection() {
    	if( upPressed  && !downPressed) {
    		if( !leftPressed && !rightPressed )
    			return Model.Direction.UP;
    		else if( leftPressed && !rightPressed )
    			return Model.Direction.UP_LEFT;
    		else if(!leftPressed)
    			return Model.Direction.UP_RIGHT;
    	}
    	else if( downPressed && !upPressed) {
    		if( !leftPressed && !rightPressed )
    			return Model.Direction.DOWN;
    		else if( leftPressed && !rightPressed )
    			return Model.Direction.DOWN_LEFT;
    		else if(!leftPressed)
    			return Model.Direction.DOWN_RIGHT;
    	}
    	else if( leftPressed && !rightPressed) {
    		return Model.Direction.LEFT;
    	}
    	else if( !leftPressed && rightPressed ) {
    		return Model.Direction.RIGHT;
    	}
    	return Model.Direction.NONE;
    }
   
    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        // System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}
