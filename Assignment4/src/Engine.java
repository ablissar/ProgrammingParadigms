/**
 * Engine class
 * Programming Paradigms - Assignment 2
 *
 * @author Adam Bliss
 * @version 1.0, 25 Aug 2016
 */
public class Engine
{
    /**
     * String that holds description of engine
     * Ex: "V8"
     */
    private String m_description;

    /**
     *Int that stores miles per gallon (mpg)
     */
    private int m_mpg;

    /**
     * Int that stores maximum speed
     */
    private int m_maxSpeed;

    /**
     * Constructor that initializes all member variables
     * If String has length 0, set description to "Generic engine"
     * If either int is negative, set value to 0
     * @since 1.0
     */
    public Engine(String descriptionIn,
                    int mpgIn,
                    int maxSpeedIn)
    {
        /**
         * Set description
         */
        if(descriptionIn.length() == 0)
        {
            m_description = "Generic engine";
        }
        else
        {
            m_description = descriptionIn;
        }

        /**
         * Set mpg
         */
        if( 0 == mpgIn )
        {
            m_mpg = 0;
        }
        else
        {
            m_mpg = mpgIn;
        }

        /**
         * Set max speed
         */
        if( 0 == maxSpeedIn )
        {
            m_maxSpeed = 0;
        }
        else
        {
            m_maxSpeed = maxSpeedIn;
        }
    }

    /**
     * getDescription()
     * Returns string value of m_description, as well as info
     * about the mpg and max speed
     * @return string containing description, mpg, and max speed
     * @since 1.0
     */
    public String getDescription()
    {
        String out = m_description;
        out += " (MPG: " + m_mpg + ", Max speed: " + m_maxSpeed + ")";
        return out;
    }

    /**
     * getMPG()
     * Returns m_mpg
     * @return value of m_mpg
     * @since 1.0
     */
    public int getMPG()
    {
        return m_mpg;
    }

    /**
     * getMaxSpeed()
     * Returns m_maxSpeed
     * @return value of m_maxSpeed
     * @since 1.0
     */
    public int getMaxSpeed()
    {
        return m_maxSpeed;
    }
}
