public class SpriteMover implements Runnable
{
	private Model model;
	private View view;
	
	SpriteMover( Model model, View view ) {
		this.model = model;
		this.view = view;
	}

    @SuppressWarnings("InfiniteLoopStatement")
	public void run() {
		// Infinite loop
		for(;;) {
			// Update model and redraw view
			if(!model.isPaused()) {
				model.updateScene( view.getWidth(), view.getHeight() );
				view.setScore(model.getScore());
			}
			
			// Sleep for 10 ms
			// Note: I don't know why putting something
			// outside the if() statement causes the 
			// resume function to work.
            view.repaint();
			try {
				Thread.sleep(10);
			}
			catch( InterruptedException e) { System.out.println("Error occured with SpriteMover class.");}
		}
	}
}
