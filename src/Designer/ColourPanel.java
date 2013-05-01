package Designer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: thip
 * Date: 30/04/2013
 * Time: 15:23
 */
public class ColourPanel extends JPanel implements MouseListener
{

    private char selectedColour;
    private char backGroundColour;
    private int cellSize;
    private int rowCapacity;
    private char[] colours;

    private Editor editor;

    public ColourPanel ( Editor editor )
    {

        this.editor = editor;

        selectedColour = 'n';
        backGroundColour = 'w';

        cellSize = 20;
        rowCapacity = 4;

        colours = new char[]
                {
                        'b', //Blue
                        'r', //red
                        'g', //green
                        'l', //Light Grey
                        'd', //Dark Grey
                        'w', //white
                        'n' //Black
                };

        setPreferredSize ( new Dimension ( rowCapacity * cellSize + 8 , 0) );
        this.addMouseListener ( this );
    }

    public static Color getColor ( char colour )
    {
        Color color;
        switch ( colour )
        {

            case 'b':
                return Color.blue;
            case 'r':
                return Color.red;
            case 'g':
                return Color.green;
            case 'l':
                return Color.lightGray;
            case 'd':
                return Color.darkGray;
            case 'w':
                return Color.white;
            case 'n':
                return Color.black;

            default:
                return Color.magenta;   //do this if color not recognised
        }

    }

    public char getSelectedColour ()
    {
        return selectedColour;
    }

    public char getBackGroundColour ()
    {
        return backGroundColour;
    }

   @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        int row = 0;

        for ( int i = 0; i < colours.length; i++ )
        {
            char colour = colours[i];

            int place = i % rowCapacity;
            row = (int) Math.floor ( i / rowCapacity );

            g.setColor ( getColor ( colour ) );
            g.fillRect ( 4+ place * cellSize, 4+ row * cellSize, cellSize, cellSize );

        }

        g.setColor ( getColor ( backGroundColour ) );
        g.fillRect ( 4, 4+ ( row + 1 ) * cellSize + 10, cellSize * rowCapacity, cellSize * rowCapacity );

        g.setColor ( getColor ( selectedColour ) );
        g.fillRect ( 4, 4+( row + 1 ) * cellSize + 10, cellSize * ( rowCapacity - 1 ), cellSize * ( rowCapacity - 1 ) );

    }

    @Override
    public void mouseClicked ( MouseEvent e )
    {
        int x = (int) Math.ceil ( (e.getX () -4) / cellSize );
        int y = (int) Math.ceil ( (e.getY () -4) / cellSize );

        int index = ( y * rowCapacity ) + x;


        if ( index < colours.length )
        {
            char tempColour = colours[index];

            if ( e.getButton () == MouseEvent.BUTTON1 ) selectedColour = tempColour;
            if ( e.getButton () == MouseEvent.BUTTON3 )
            {
                backGroundColour = tempColour;
                editor.setBackground ();
            }
        }


        repaint ();
    }


    @Override
    public void mousePressed ( MouseEvent e )
    {
    }

    @Override
    public void mouseReleased ( MouseEvent e )
    {
    }

    @Override
    public void mouseEntered ( MouseEvent e )
    {
    }

    @Override
    public void mouseExited ( MouseEvent e )
    {
    }
}
