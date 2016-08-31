import javax.swing.JOptionPane;
import java.util.*;

public class Assignment3
{
    public static void main(String[] args)
    {
        String m_carDescription;
        int m_capacity;
        String m_engineDescription;       
        int m_mpg;
        int m_maxSpeed;
        int m_legs;
        Vector<Integer> m_distances = new Vector<Integer>();
        Vector<Double> m_xRatios = new Vector<Double>();
        Vector<Double> m_yRatios = new Vector<Double>();

        try
        {
            m_carDescription = JOptionPane.showInputDialog("Car description?");
            m_capacity = Integer.parseInt(JOptionPane.showInputDialog("Fuel capacity (gallons)?"));
            m_engineDescription = JOptionPane.showInputDialog("Engine description?");
            m_mpg = Integer.parseInt(JOptionPane.showInputDialog("MPG?"));
            m_maxSpeed = Integer.parseInt(JOptionPane.showInputDialog("Max speed (MPH)?"));
        }
        catch(Exception e)
        {
            System.out.println("Invalid data entered. Exiting.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Car description: " + m_carDescription + "\n" +
                "Fuel capacity: " + m_capacity + "\n" +
                "Engine description: " + m_engineDescription + "\n" +
                "MPG: " + m_mpg + "\n" +
                "Max speed: " + m_maxSpeed + "\n");

        try
        {
            m_legs = Integer.parseInt(JOptionPane.showInputDialog("Number of legs?"));
            for(int i = 1; i <= m_legs; i++)
            {
                int distance;
                double xRatio;
                double yRatio;

                distance = Integer.parseInt(
                        JOptionPane.showInputDialog("Distance for leg " + i + "?"));
                xRatio = Double.parseDouble(
                        JOptionPane.showInputDialog("X ratio for leg " + i + "?"));
                yRatio = Double.parseDouble(
                        JOptionPane.showInputDialog("Y ratio for leg " + i + "?"));

                m_distances.addElement(distance);
                m_xRatios.addElement(xRatio);
                m_yRatios.addElement(yRatio);
            }
        }
        catch(Exception e)
        {
            System.out.println("Invalid data entered. Exiting");
            return;
        }
    }
}
