import java.awt.Graphics;

import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

class Controller implements MouseListener, KeyListener
{
    Model model;
    View view;

    Controller() throws IOException, Exception {
        model = new Model();
        view = new View(this);
    }

    public void update(Graphics g) {
        model.update(g);
    }

    public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			// Gets here is left mouse button was clicked
			view.repaint();
		} else if (SwingUtilities.isRightMouseButton(e))  {
			// Gets here if right mouse button was clicked
			model.updateScene( view.getWidth(), view.getHeight() );
			view.repaint();
		}
    }

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
    	
    	// On keypress 'r', reset program (empty sprite list, reset counters for caputred/escaped
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
    public void keyPressed(KeyEvent e) {    }
    public void keyReleased(KeyEvent e) {    }
    
    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        //System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}
