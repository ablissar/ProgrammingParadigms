
public class SpriteMover implements Runnable
{
	Model model;
	View view;
	
	public SpriteMover( Model model, View view )
	{
		this.model = model;
		this.view = view;
	}

	public void run() 
	{
		// Infinite loop
		for(;;)
		{
			// Update model and redraw view
			model.updateScene( view.getWidth(), view.getHeight() );
			view.repaint();
			// Sleep for 2 ms
			try
			{
				Thread.sleep(2);
			}
			catch( InterruptedException e) {}
		}
	}
}
