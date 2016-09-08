import javax.swing.JOptionPane;
import java.util.*;

public class Assignment3
{
    public static void main(String[] args)
    {
        boolean valid;
        String carDescription;
        int capacity;
        String engineDescription;       
        int mpg;
        int maxSpeed;
        int legs;
        int[] distances = new int[10];
        double[] xRatios = new double[10];
        double[] yRatios = new double[10];

        try
        {
            carDescription = JOptionPane.showInputDialog("Car description?");
            do
            {
                capacity = Integer.parseInt(JOptionPane.showInputDialog("Fuel capacity (gallons)?"));
                valid = (capacity > 0) ? true : false;
            }while(!valid);
            engineDescription = JOptionPane.showInputDialog("Engine description?");
            do
            {
                mpg = Integer.parseInt(JOptionPane.showInputDialog("MPG?"));
                valid = (mpg> 0) ? true : false;
            }while(!valid);
            do
            {
                maxSpeed = Integer.parseInt(JOptionPane.showInputDialog("Max speed (MPH)?"));
                valid = (maxSpeed> 0) ? true : false;
            }while(!valid);
        }
        catch(Exception e)
        {
            System.out.println("Invalid data entered. Exiting.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Car description: " + carDescription + "\n" +
                "Fuel capacity: " + capacity + "\n" +
                "Engine description: " + engineDescription + "\n" +
                "MPG: " + mpg + "\n" +
                "Max speed: " + maxSpeed + "\n");

        try
        {
            do
            {
                legs = Integer.parseInt(JOptionPane.showInputDialog("Number of legs?"));
                valid = (legs > 0) ? true : false;
            }while(!valid);

            for(int i = 1; i <= legs; i++)
            {
                int distance;
                double xRatio;
                double yRatio;

                do
                {
                    distance = Integer.parseInt(
                            JOptionPane.showInputDialog("Distance for leg " + i + "?"));
                    valid = (distance> 0) ? true : false;
                }while(!valid);

                xRatio = Double.parseDouble(
                        JOptionPane.showInputDialog("X ratio for leg " + i + "?"));
                yRatio = Double.parseDouble(
                        JOptionPane.showInputDialog("Y ratio for leg " + i + "?"));

                distances[i] = distance;
                xRatios[i] = xRatio;
                yRatios[i] = yRatio;
            }
        }
        catch(Exception e)
        {
            System.out.println("Invalid data entered. Exiting");
            return;
        }
        Engine engine1 = new Engine( engineDescription, mpg, maxSpeed );
        Car car1 = new Car( carDescription, capacity, engine1 );
        car1.fillUp();

        for (int i = 1; i <= legs; i++)
        {

        }
    }
}

