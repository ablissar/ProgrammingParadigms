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
			if(!model.getPaused()) {
				model.updateScene( view.getWidth(), view.getHeight() );
				view.repaint();
				// Sleep for 2 ms
				try {
					Thread.sleep(10);
				}
				catch( InterruptedException e) {}
			}
		}
	}
}
