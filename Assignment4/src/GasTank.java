/**
 * GasTank Class 
 * Programming Paradigms - Assignment 2
 *
 * @author Adam Bliss
 * @version 1.0, 25 Aug 2016
 */
public class GasTank 
{
    /**
     * Integer that keeps track of maximum fuel capacity.
     */
    private int m_capacity;

    /**
     * Integer that keeps track of current fuel level.
     */
    private double m_level;

    /**
     * Constructor that take one argument and uses it to set capacity
     * If param is less than 0, capacity is set to 0.
     * @param capacityIn value used to set m_capacity (int)
     * @since 1.0
     */
    public GasTank(int newCapacity)
    {
        if( newCapacity < 0 )
        {
            m_capacity = 0;
        }
        else
        {
            m_capacity = newCapacity;
        }
    }

    /**
     * getCapacity()
     * Function that returns the current max capacity (m_capacity)
     * @return current value of m_capacity (int)
     * @since 1.0
     */
    public int getCapacity()
    {
        return m_capacity;
    }

    /**
     * getLevel()
     * Function that returns current fuel level (m_level)
     * @return current value of m_level (double)
     * @since 1.0
     */
    public double getLevel()
    {
        return m_level;
    }

    /**
     * setLevel()
     * Function that take a double and uses it to set m_level.
     * @param levelIn value of m_level (double) 
     */
    public void setLevel(double levelIn)
    {
        if( levelIn < 0 )
        {
            m_level = 0;
        }
        else if ( levelIn > m_capacity )
        {
            m_level = m_capacity;
        }
        else
        {
            m_level = levelIn;
        }
    }
}
