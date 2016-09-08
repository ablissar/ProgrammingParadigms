import javax.swing.JPanel;
import java.awt.Graphics;

public class DrivePanel extends JPanel
{
    int m_currentXPos = 0;
    int m_currentYPos = 0;
    int m_xPos = 0;
    int m_yPos = 0;

    public DrivePanel( int currentXPos,
                       int currentYPos,
                       int xPos,
                       int yPos)
    {
        m_currentXPos = currentXPos;
        m_currentYPos = currentYPos;
        m_xPos = xPos;
        m_yPos = yPos;
    }

    public void paintComponent( Graphics g)
    {
        super.paintComponent( g );

        int width = getWidth();
        int height = getHeight();

        g.drawLine(m_currentXPos, m_currentYPos, m_xPos, m_yPos);
    }    
}
