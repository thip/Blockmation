package common;

import Designer.ColourPanel;

import javax.swing.*;
import java.awt.*;

public class FrameViewer extends JPanel
{
    private AnimationFrame frame;
    private int xOffset;
    private int yOffset;
    private boolean showGrid;


    public FrameViewer ()
    {

        xOffset = 20;
        yOffset = 20;


        setBackground ( new Color ( 255, 252, 246 ) );

        frame = new AnimationFrame ( 10, 'w' );
    }

    public void showGrid ()
    {
        showGrid = true;
    }

    public void hideGrid ()
    {
        showGrid = false;
    }

    @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        drawgrid ( g );

    }

    public void drawgrid ( Graphics g )
    {


        int dimension = frame.getDimension ();

        for ( int i = 0; i < dimension; i++ )
        {
            for ( int j = 0; j < dimension; j++ )
            {

                g.setColor ( ColourPanel.getColor ( frame.getCell ( i, j ) ) );

                g.fillRect ( xOffset + i * 10, yOffset + j * 10, 10, 10 );

                if ( showGrid )
                {
                    g.setColor ( new Color ( 24, 23, 50 ) );
                    g.drawLine ( xOffset + i * 10, yOffset + j * 10, xOffset + i * 10, yOffset + j * 10 );
                }

            }

        }

        g.setColor ( Color.black );
        g.drawRect ( xOffset, yOffset, dimension * 10, dimension * 10 );

    }


    //private Editor editor;

    public void displayFrame ( AnimationFrame frame )
    {
        this.frame = frame;
        this.repaint ();
    }

    public int getXOffset ()
    {
        return xOffset;
    }



    public void setXOffset ( int xOffset )
    {
        this.xOffset = xOffset;
    }

    public int getYOffset ()
    {
        return yOffset;
    }

    public void setYOffset ( int yOffset )
    {
        this.yOffset = yOffset;
    }
}