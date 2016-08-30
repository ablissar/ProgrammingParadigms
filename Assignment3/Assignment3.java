import javax.swing.JOptionPane;

public class Assignment3
{
    public static void main(String[] args)
    {
        String m_carDescription;
        int m_capacity;
        String m_engineDescription;       
        int m_mpg;
        int m_maxSpeed;

        m_carDescription = JOptionPane.showInputDialog("Car description?");
        m_capacity = Integer.parseInt(JOptionPane.showInputDialog("Fuel capacity?"));
        m_engineDescription = JOptionPane.showInputDialog("Engine description?");
        m_mpg = Integer.parseInt(JOptionPane.showInputDialog("MPG?"));
        m_maxSpeed = Integer.parseInt(JOptionPane.showInputDialog("Max speed?"));

        JOptionPane.showMessageDialog(null,
                m_carDescription + "\n" +
                "Fuel capacity: " + m_capacity + "\n" +
                m_engineDescription + "\n" +
                "MPG: " + m_mpg + "\n" +
                "Max speed: " + m_maxSpeed + "\n");
    }
}
