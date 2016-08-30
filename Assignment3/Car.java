/**
 * Car class
 * Programming Paradigms - Assignment 2
 *
 * @author Adam Bliss
 * @version 1.0
 */

import java.text.DecimalFormat;

public class Car
{
    /**
     * String description of car
     * Ex: "Sweet Ride"
     */
    private String m_description;

    /**
     * Integer values for x and y coordinates of car
     */
    private int m_xPos;
    private int m_yPos;

    /**
     * GasTank object
     */
    private GasTank m_tank;

    /**
     * Engine object
     */
    private Engine m_engine;

    /**
     * Constructor that takes in description string,
     * max fuel capacity, and Engine object reference
     *
     * If string length is 0, description is set to "Generic car"
     * If Engine reference is null, new Engine is created w/ values set to 0
     *
     * @param descriptionIn String description of car
     * @param capacityIn Int max fuel capacity
     * @param engineIn Reference to Engine object
     * @since 1.0
     */
    public Car(String descriptionIn,
                int capacityIn,
                Engine engineIn)
    {
        /**
         * Set description
         */
        if( 0 == descriptionIn.length() )
        {
            m_description = "Generic car";
        }
        else
        {
            m_description = descriptionIn;
        }

        /**
         * Set max fuel capacity
         */
        m_tank = new GasTank(capacityIn); 

        /**
         * Set engine
         */
        if ( null == engineIn )
        {
            m_engine = new Engine("", 0, 0);
        }
        else
        {
            m_engine = engineIn;
        }
    }

    /**
     * getDescription()
     * Returns m_description, as well as engine description,
     * fuel level and capacity, and location
     * @return description of car (String)
     */
    public String getDescription()
    {
        String out = m_description;
        out += " (engine: " + m_engine.getDescription() + "), ";
        out += "fuel: " + String.format("%.2f", m_tank.getLevel()) + "/" + m_tank.getCapacity() + ", ";
        out += "location: (" + m_xPos + "," + m_yPos + ")";
        return out;
    }

    /**
     * getX()
     * Returns m_xPos
     * @return x position of car (int)
     */
    public int getX()
    {
        return m_xPos;
    }

    /**
     * getY()
     * Returns m_yPos
     * @return y position of car (int)
     */
    public int getY()
    {
        return m_yPos;
    }

    /**
     * getFuelLevel()
     * Returns m_tank.getLevel()
     * @return current fuel level of gas tank (double)
     */
    public double getFuelLevel()
    {
        return m_tank.getLevel();
    }

    /**
     * getMPG()
     * Returns m_engine.getMPG()
     * @return mpg of car's engine (int)
     */
    public int getMPG()
    {
        return m_engine.getMPG();
    }

    /**
     * fillUp()
     * Causes gas tank to be filled to max capacity
     */
    public void fillUp()
    {
        m_tank.setLevel(m_tank.getCapacity());
    }

    /**
     * getMaxSpeed()
     * Returns m_engine.getMaxSpeed()
     * @return max speed of car's engine (int)
     */
    public int getMaxSpeed()
    {
        return m_engine.getMaxSpeed();
    }
    
    /**
     * drive()
     * Moves car given distance in a direction specified by
     * the two ratio values, subtracts appropriate amount of fuel
     *
     * If car runs out of gas, print travled distance to console
     *
     * @param distance distance to travel (int)
     * @param xRatio x component of direction (double)
     * @param yRatio y component of direction (double)
     * @return actual distance traveled (double)
     */
    public double drive(int distance,
                        double xRatio,
                        double yRatio)
    {
        double milesInTank = (m_tank.getLevel() * m_engine.getMPG());
        double distanceGallons = ((double)distance / m_engine.getMPG());
        double hypotenuse; 
        double travelFactor;
        double xDistance;
        double yDistance;

        if( distanceGallons > m_tank.getLevel() )
        {
            distance =(int)(milesInTank);
            System.out.println("Ran out of gas after " +
                    String.format("%.2f", milesInTank) + " miles.");
            m_tank.setLevel(0);
        }
        else
        {
            m_tank.setLevel(m_tank.getLevel() - distanceGallons);
        }

        hypotenuse = Math.sqrt( Math.pow(xRatio, 2) + Math.pow(yRatio, 2) );
        travelFactor = (double)distance / (double)hypotenuse;
        xDistance = xRatio * travelFactor;
        yDistance = yRatio * travelFactor;
        m_xPos += xDistance;
        m_yPos += yDistance;

        return (double)distance;
    }
}

