package Viewer;


import common.Animation;
import common.AnimationFrame;
import common.FrameViewer;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame
{

    private JMenuBar menuBar;
    private JMenu fileMenu;

	private FrameViewer frameViewer;
    private AnimationManager animationManager;
    private FileManager fileManager;






    public Viewer ()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Blockmation - Viewer");

        animationManager = new AnimationManager ( );

        menuBar = new JMenuBar ();
        fileMenu = new JMenu ( "File" );

        fileManager = new FileManager(fileMenu);
        fileManager.registerAnimationManager ( animationManager );

        menuBar.add ( fileMenu );


        frameViewer = new FrameViewer ();
        animationManager.registerFrameViewer ( frameViewer );







        this.setJMenuBar ( menuBar );

        frameViewer.setBorder(BorderFactory.createLineBorder(Color.black,6));
        this.add(frameViewer, BorderLayout.CENTER);

        this.add(animationManager, BorderLayout.SOUTH);







        setSize(800, 600);
        setVisible(true);


        Animation temp = new Animation ( 20 );
        temp.addFrame ( new AnimationFrame ( 20, 'w' ) );







    }

}