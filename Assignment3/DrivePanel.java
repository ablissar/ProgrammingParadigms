import java.awt.*;
import javax.swing.JPanel;

public class DrivePanel extends JPanel
{
    Point[] m_coordinates = new Point[10];

    public DrivePanel( Point[] coordinates )
    {
        for( int i = 0; i < 10; i++)
        {
            m_coordinates[i] = coordinates[i];
        }
    }

    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        int width = getWidth();
        int height = getHeight();

        for( int i = 0; i < 10; i++)
        {
            if (m_coordinates[i+1] == null)
            {
                break;
            }
            g.drawLine( m_coordinates[i].x, (height - m_coordinates[i].y),
                        m_coordinates[i+1].x, (height - m_coordinates[i+1].y) );
        }
    }
}

