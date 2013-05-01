package common;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: thip
 * Date: 28/04/2013
 * Time: 23:55
 */
public class Animation
{
    private ArrayList<AnimationFrame> frames;
    private int frameDimension;

    /**
     *
     * @param frameDimension
     */
    public Animation ( int frameDimension )
    {
        this.frameDimension = frameDimension;
        frames = new ArrayList<AnimationFrame> ();
    }

    /**
     *
     * @param frame
     */
    public void addFrame ( AnimationFrame frame )
    {
        frames.add ( frame );
    }

    /**
     *
     * @param index
     */
    public void deleteFrame ( int index )
    {
        frames.remove ( index );
    }

    /**
     *
     * @param position
     * @param backGroundColour
     */
    public void insertNewFrame ( int position, char backGroundColour )
    {
        frames.add ( position, new AnimationFrame ( frameDimension, backGroundColour ) );
    }

    /**
     *
     * @param frameNumber
     * @return
     */
    public AnimationFrame getFrame ( int frameNumber )
    {
        return frames.get ( frameNumber );
    }

    public int getLength ()
    {
        return frames.size ();
    }

    @Override
    public String toString ()
    {
        String myString =
                frames.size () + "\n"
                        + frameDimension + "\n";

        for ( AnimationFrame frame : frames )
        {
            myString += frame.toString ();
        }
        return myString;
    }
}

