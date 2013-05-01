package Designer;

import common.Animation;
import common.AnimationFrame;
import common.FrameViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameManager extends JPanel implements ActionListener, Runnable
{
    private JButton playButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JButton lastButton;
    private JButton insertNextButton;
    private JButton insertLastButton;
    private JButton deleteFrameButton;

    private Animation animation;

    private FrameViewer frameViewer;


    private Thread runningThread;

    private Editor editor;

    private int currentFrame;
    private boolean playing;


    public void registerEditor( Editor editor)
    {
        this.editor = editor;
    }

    public FrameManager (  )
    {
        playing = false;



        playButton = new JButton ( "Play" );
        stopButton = new JButton ( "Stop" );
        pauseButton = new JButton ( "Pause" );
        nextButton = new JButton ( ">" );
        lastButton = new JButton ( "<" );
        insertNextButton = new JButton ( ">+>" );
        insertLastButton = new JButton ( "<+<" );
        deleteFrameButton = new JButton ( "-" );

        nextButton.addActionListener ( this );
        lastButton.addActionListener ( this );
        playButton.addActionListener ( this );
        stopButton.addActionListener ( this );
        pauseButton.addActionListener ( this );
        insertNextButton.addActionListener ( this );
        insertLastButton.addActionListener ( this );
        deleteFrameButton.addActionListener ( this );


        this.add ( playButton );
        this.add ( lastButton );
        this.add ( insertLastButton );
        this.add ( pauseButton );
        this.add ( deleteFrameButton);
        this.add (insertNextButton );
        this.add ( nextButton );
        this.add ( stopButton );

        currentFrame = 0;


    }

    public void play ()
    {
        playing = true;
        nextButton.setEnabled ( false );
        lastButton.setEnabled ( false );
        runningThread = new Thread ( this );
        runningThread.start ();

    }

    public void stop ()
    {
        pause ();
        currentFrame = 0;
        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );

    }

    public void pause ()
    {
        playing = false;
        nextButton.setEnabled ( true );
        lastButton.setEnabled ( true );
    }



    public void removeFrame ()
    {
        throw new UnsupportedOperationException ();
    }

    public void nextFrame ()
    {

        currentFrame++;
        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
        updateButtons ();
    }

    public void lastFrame ()
    {
        currentFrame--;
        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
        updateButtons ();
    }

    public void operation ()
    {
        throw new UnsupportedOperationException ();
    }

    public void updateButtons ()
    {
        nextButton.setEnabled ( true );
        lastButton.setEnabled ( true );

        if ( currentFrame == animation.getLength () - 1 )
        {
            nextButton.setEnabled ( false );
        }
        if ( currentFrame == 0 )
        {
            lastButton.setEnabled ( false );
        }

    }

    public void setAnimation ( Animation animation )
    {
        this.animation = animation;

        currentFrame = 0;

        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
        updateButtons ();
    }

    public void registerFrameViewer ( FrameViewer frameViewer )
    {
        this.frameViewer = frameViewer;
    }

    @Override
    public void run ()
    {
        // while (true ){
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

                try
                {
                    currentFrame = 0;
                    frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
                    Thread.sleep ( 200 );
                } catch ( InterruptedException e ) {
                    System.err.print ( e.getStackTrace () );
                }
            }
        }
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource () == nextButton ) nextFrame ();

        if ( e.getSource () == lastButton ) lastFrame ();

        if ( e.getSource () == playButton ) play ();

        if ( e.getSource () == stopButton ) stop ();

        if ( e.getSource () == pauseButton ) pause ();

        if ( e.getSource () == insertLastButton ) addFrameBefore ();

        if ( e.getSource () == insertNextButton ) addFrameAfter ();

        if ( e.getSource () == deleteFrameButton ) deleteFrame ();
    }

    private void deleteFrame ()
    {
        if (animation.getLength () > 1)
        {
        animation.deleteFrame( currentFrame );
        if ( currentFrame != 0 ){ currentFrame--; }
        else {currentFrame++; }

        frameViewer.displayFrame ( animation.getFrame ( currentFrame ) );
        updateButtons ();

        } else {
            JOptionPane.showMessageDialog ( null, "Animation must have at least one frame!" );
        }


    }

    private void addFrameAfter ()
    {

        animation.insertNewFrame( currentFrame +1,  editor.getBackgroundColour ());
        nextFrame ();
    }

    private void addFrameBefore ()
    {
        animation.insertNewFrame( currentFrame,  editor.getBackgroundColour ());
        frameViewer.displayFrame ( animation.getFrame ( currentFrame ));
        updateButtons ();
    }

    public AnimationFrame getCurrentFrame ()
    {
        return animation.getFrame ( currentFrame );
    }

    public Animation getAnimation ()
    {
        return animation;
    }
}