package Designer;

import common.Animation;
import common.AnimationFrame;
import common.FrameViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Designer extends JFrame implements ActionListener
{

    private JMenuItem exitItem;

    Thread run;

    public Designer ()
    {
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        setTitle ( "Blockmation - Designer" );

        JMenuBar menuBar = new JMenuBar ();
        JMenu fileMenu = new JMenu ( "File" );
        exitItem = new JMenuItem("Exit");

        menuBar.add ( fileMenu );


        FrameViewer frameViewer = new FrameViewer ();
        frameViewer.showGrid ();
        Editor editor = new Editor ();
        FrameManager frameManager = new FrameManager ();
        FileManager fileManager = new FileManager ( fileMenu );

        fileMenu.add ( exitItem );


        editor.registerFrameViewer ( frameViewer );
        editor.registerFrameManager ( frameManager );
        frameManager.registerFrameViewer ( frameViewer );
        frameManager.registerEditor ( editor );
        fileManager.registerFrameManager ( frameManager );

        this.setJMenuBar ( menuBar );
        this.add ( frameManager, BorderLayout.SOUTH );

        this.add ( frameViewer, BorderLayout.CENTER );


        this.add ( editor, BorderLayout.WEST );


        setSize ( 800, 600 );
        setVisible ( true );


        Animation temp = new Animation (20);
        temp.addFrame ( new AnimationFrame ( 20, 'w' ) );


        frameManager.setAnimation ( temp );

        exitItem.addActionListener ( this );


    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource () == exitItem)
        {
            System.exit ( 0 );
        }
    }
}