package Viewer;


import common.Animation;
import common.AnimationFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class FileManager implements ActionListener{

    private JFileChooser fileChooser;

	private File file;

    private AnimationManager animationManager;

    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveAs;
    private JMenuItem save;

    public FileManager (  JMenu menu )
    {





        openFile = new JMenuItem ( "Open" );


        openFile.addActionListener ( this );

        fileChooser = new JFileChooser( );


        menu.add ( openFile ) ;

        menu.add ( new JPopupMenu.Separator () );
    }

    void openFile ( File selectedFile ) throws FileNotFoundException, IOException {



        FileReader fileReader = new FileReader ( selectedFile );
        BufferedReader bufferedReader = new BufferedReader ( fileReader );

        int numberOfFrames = Integer.parseInt ( bufferedReader.readLine () );
        int frameDimension = Integer.parseInt ( bufferedReader.readLine () );

        Animation animation = new Animation (frameDimension);

        String frameString;

        for ( int i = 0; i < numberOfFrames; i++ )
        {
            frameString = "";
            for ( int j = 0; j < frameDimension; j++ )
            {
                 frameString += bufferedReader.readLine ();
            }

            animation.addFrame ( new AnimationFrame (frameDimension, frameString ));
        }

        animationManager.setAnimation ( animation );

        bufferedReader.close ();
        fileReader.close ();
	}


    @Override
    public void actionPerformed ( ActionEvent event )
    {
        if ( event.getSource () == openFile )
        {
            if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION)
            {
                try {
                    openFile ( fileChooser.getSelectedFile () );
                } catch ( FileNotFoundException e) {
                    JOptionPane.showMessageDialog ( null, "File not found: " + e.getMessage ()  );
                } catch ( Exception e) {
                    System.out.print (    e.getStackTrace ()  );

               }

            }

        }
    }

    public void registerAnimationManager ( AnimationManager animationManager )
    {
        this.animationManager = animationManager;
    }
}