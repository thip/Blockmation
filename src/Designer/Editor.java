package Designer;

import common.AnimationFrame;
import common.FrameViewer;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

class Editor extends JPanel implements MouseInputListener
{


    private FrameViewer frameViewer;
    private FrameManager frameManager;
    private AnimationFrame workingFrame;
    private ColourPanel colourPanel;
    private char backgroundColour;
    private boolean dragging;
    private int yInitial;
    private int xInitial;
    private int initialXOffset;
    private int initialYOffset;

    public Editor ()
    {
        backgroundColour = 'k';

        dragging = false;


        colourPanel = new ColourPanel ( this );

        backgroundColour = 'w';

        this.setLayout ( new GridBagLayout () );


        GridBagConstraints c = new GridBagConstraints ();

        c.fill = GridBagConstraints.VERTICAL;

        c.weighty = 0.5;


        this.add ( colourPanel, c );
    }

    public char getBackgroundColour ()
    {
        return backgroundColour;
    }

    public void setBackground ()
    {
        workingFrame = frameManager.getCurrentFrame ();
        workingFrame.replace ( backgroundColour, colourPanel.getBackGroundColour () );
        backgroundColour = colourPanel.getBackGroundColour ();
        frameViewer.repaint ();
    }

    public void registerFrameManager ( FrameManager frameManager )
    {
        this.frameManager = frameManager;
    }

    void drawPixel ( int x, int y )
    {

        workingFrame.setPixel ( x, y, colourPanel.getSelectedColour () );

    }

    public void setColour ()
    {
        throw new UnsupportedOperationException ();
    }

    public void setBackGroundColor ()
    {
        throw new UnsupportedOperationException ();
    }

    void erasePixel ( int x, int y )
    {

        workingFrame.setPixel ( x, y, colourPanel.getBackGroundColour () );

    }

    public void drawShape ()
    {
        throw new UnsupportedOperationException ();
    }

    public void drawLine ()
    {
        throw new UnsupportedOperationException ();
    }

    public void registerFrameViewer ( FrameViewer frameViewer )
    {
        this.frameViewer = frameViewer;
        frameViewer.addMouseListener ( this );
        frameViewer.addMouseMotionListener ( this );
    }

    @Override
    public void mouseClicked ( MouseEvent e )
    {
        workingFrame = frameManager.getCurrentFrame ();

        int x = e.getX () - frameViewer.getXOffset ();
        int y = e.getY () - frameViewer.getYOffset ();

        x = (int) Math.ceil ( x / 10 );
        y = (int) Math.ceil ( y / 10 );

        if ( e.getButton () == MouseEvent.BUTTON1 )
        {
            drawPixel ( x, y );
        }
        if ( e.getButton () == MouseEvent.BUTTON3 )
        {
            erasePixel ( x, y );
        }

        frameViewer.repaint ();

    }

    @Override
    public void mousePressed ( MouseEvent e )
    {
        if ( ( e.getButton () == MouseEvent.BUTTON2 ) || ( e.getButton () == MouseEvent.BUTTON1 && e.isMetaDown () ) )
        {
            yInitial = e.getY ();
            xInitial = e.getX ();
            initialXOffset = frameViewer.getXOffset ();
            initialYOffset = frameViewer.getYOffset ();
        }
    }

    @Override
    public void mouseReleased ( MouseEvent e )
    {

    }

    @Override
    public void mouseEntered ( MouseEvent e )
    {
        //throw new UnsupportedOperationException ();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited ( MouseEvent e )
    {
        //throw new UnsupportedOperationException ();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged ( MouseEvent e )
    {
        if ( ( e.getButton () == MouseEvent.BUTTON2 ) || ( e.getButton () == MouseEvent.BUTTON1 && e.isMetaDown () ) )
        {


            frameViewer.setYOffset ( initialYOffset + ( e.getY () - yInitial ) );
            frameViewer.setXOffset ( initialXOffset + ( e.getX () - xInitial ) );
            frameViewer.repaint ();
        } else
        {
            workingFrame = frameManager.getCurrentFrame ();

            int x = e.getX () - frameViewer.getXOffset ();
            int y = e.getY () - frameViewer.getYOffset ();

            x = (int) Math.ceil ( x / 10 );
            y = (int) Math.ceil ( y / 10 );

            if ( e.getButton () == MouseEvent.BUTTON1 )
            {
                drawPixel ( x, y );
            }
            if ( e.getButton () == MouseEvent.BUTTON3 )
            {
                erasePixel ( x, y );
            }

            frameViewer.repaint ();
        }
    }

    @Override
    public void mouseMoved ( MouseEvent e )
    {


    }
}
