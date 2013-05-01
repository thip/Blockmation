package Designer;

import common.Animation;
import common.AnimationFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class FileManager implements ActionListener
{

    private JFileChooser fileChooser;

    private File file;

    private FrameManager frameManager;

    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveAs;
    private JMenuItem save;

    public FileManager ( JMenu menu )
    {

        newFile = new JMenuItem ( "New" );
        openFile = new JMenuItem ( "Open" );
        saveAs = new JMenuItem ( "Save As" );
        save = new JMenuItem ( "Save" );

        openFile.addActionListener ( this );
        save.addActionListener ( this );
        saveAs.addActionListener ( this );
        newFile.addActionListener ( this );

        fileChooser = new JFileChooser ();



        menu.add ( newFile );
        menu.add ( openFile );
        menu.add ( saveAs );
        menu.add ( save );
        menu.add ( new JPopupMenu.Separator () );
    }

    public  void newFile ( )
    {
        int dimension = 0;

        try {
            dimension = Integer.parseInt (JOptionPane.showInputDialog ( this, "What size would you like your animation to be?" ));
            Animation animation = new Animation (dimension);
            animation.addFrame ( new AnimationFrame ( dimension, 'w' ) );
            frameManager.setAnimation ( animation );

            file = null;

        } catch ( NumberFormatException e ) {
            JOptionPane.showMessageDialog ( null, "Please enter a numerical value" );
            newFile();
        }
    }

    public void openFile ( File selectedFile ) throws FileNotFoundException, IOException
    {

        file = selectedFile;



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

            animation.addFrame ( new AnimationFrame ( frameDimension, frameString ) );
        }

        frameManager.setAnimation ( animation );



        bufferedReader.close ();
        fileReader.close ();

    }



    public void saveFile ()   throws FileNotFoundException, IOException
    {
        if ( file != null )
        {
            FileWriter fileWriter = new FileWriter ( file ) ;

            fileWriter.write ( frameManager.getAnimation().toString() );

            fileWriter.close ();
        }  else {
            saveNewFile ();
        }

    }

    public void saveNewFile ()
    {
        if ( fileChooser.showSaveDialog (fileChooser) == JFileChooser.APPROVE_OPTION)
        {
              file = fileChooser.getSelectedFile ();
            try {
                saveFile ();

            } catch ( IOException e )
            {
                System.err.print ( e.getStackTrace () );
            }
        }
    }

    @Override
    public void actionPerformed ( ActionEvent event )
    {
        if ( event.getSource () == openFile )
        {
            if ( fileChooser.showOpenDialog ( fileChooser ) == JFileChooser.APPROVE_OPTION )
            {
                try
                {
                    openFile ( fileChooser.getSelectedFile () );
                } catch ( FileNotFoundException e )
                {
                    JOptionPane.showMessageDialog ( null, "File not found: " + e.getMessage () );
                } catch ( Exception e )
                {
                    System.out.print ( e.getStackTrace () );

                }

            }

        }

        if ( event.getSource () == saveAs )
        {
            saveNewFile ();
        }

        if ( event.getSource () == newFile )
        {
            newFile ();
        }


        if ( event.getSource () == save )
        {
            try {
                saveFile ();
            } catch ( IOException e )
            {
                System.err.print ( e.getStackTrace () );
            }
        }
    }

    public void registerFrameManager ( FrameManager frameManager )
    {
        this.frameManager = frameManager;
    }
}