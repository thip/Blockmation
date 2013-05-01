package Viewer;

import common.Animation;
import common.FrameViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AnimationManager extends JPanel implements ActionListener, Runnable
{
    private JButton playButton;
    private JButton stopButton;
    private Animation animation;
    private FrameViewer frameViewer;
    private int currentFrame;
    private boolean playing;


    public AnimationManager ()
    {
        playing = false;


        playButton = new JButton ( "Play" );
        stopButton = new JButton ( "Stop" );


        playButton.addActionListener ( this );
        stopButton.addActionListener ( this );


        this.add ( playButton );

        this.add ( stopButton );


    }

    void play ()
    {
        playing = true;

        Thread runningThread = new Thread ( this );
        runningThread.start ();

    }

    void stop ()
    {
        playing = false;
        currentFrame = 0;
        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );

    }

    public void operation ()
    {
        throw new UnsupportedOperationException ();
    }

    public void setAnimation ( Animation animation )
    {
        this.animation = animation;

        currentFrame = 0;

        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );

    }

    public void registerFrameViewer ( FrameViewer frameViewer )
    {
        this.frameViewer = frameViewer;
    }

    @Override
    public void run ()
    {
        if ( animation != null )
        {
            while ( playing )
            {
                if ( currentFrame != animation.getLength () - 1 )
                {
                    try
                    {
                        currentFrame++;

                        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
                        Thread.sleep ( 200 );
                    } catch ( InterruptedException e )
                    {
                        System.err.print ( e.getStackTrace () );
                    }
                } else
                {
                    currentFrame = 0;
                    frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
                }
            }
        }
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {


        if ( e.getSource () == playButton ) play ();

        if ( e.getSource () == stopButton ) stop ();

    }
}