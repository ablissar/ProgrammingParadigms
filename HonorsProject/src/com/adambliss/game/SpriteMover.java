package com.adambliss.game;

public class SpriteMover implements Runnable
{
	Model model;
	View view;
	
	public SpriteMover( Model model, View view ) {
		this.model = model;
		this.view = view;
	}

	public void run() {
		// Infinite loop
		for(;;) {
			// Update model and redraw view
			if(!model.isPaused()) {
				model.updateScene( view.getWidth(), view.getHeight() );
				view.setScore(model.getScore());
				view.repaint();
				
			}
			
			// Sleep for 10 ms
			// Note: I don't know why putting something
			// outside the if() statement causes the 
			// resume function to work.
			try {
				Thread.sleep(10);
			}
			catch( InterruptedException e) {}
		}
	}
}
