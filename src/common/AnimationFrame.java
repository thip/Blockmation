package common;

/**
 * Created with IntelliJ IDEA.
 * User: thip
 * Date: 28/04/2013
 * Time: 23:55
 */
public class AnimationFrame
{

    private int dimension;
    private char[][] grid;

    public AnimationFrame ( int dimension, String frameString )
    {
        this.dimension = dimension;
        grid = new char[dimension][dimension];

        for ( int i = 0; i < dimension; i++ )
        {
            for ( int j = 0; j < dimension; j++ )
            {
                //j is rows, get index of char by multiplying rows by dimension and then adding the point in the line we're at + the number of new lines we will have encountered.
                grid[i][j] = frameString.charAt ( ( j * ( dimension ) ) + i );//+ j );

            }


        }
    }

    public AnimationFrame ( int dimension, char backGroundColour )
    {

        this.dimension = dimension;

        grid = new char[dimension][dimension];

        for ( int i = 0; i < dimension; i++ )
        {
            for ( int j = 0; j < dimension; j++ )
            {
                //j is rows, get index of char by multiplying rows by dimension and then adding the point in the line we're at + the number of new lines we will have encountered.
                grid[i][j] = backGroundColour;

            }


        }
    }

    public void setPixel ( int x, int y, char colour )
    {
        if ( x <= dimension && y <= dimension && x >= 0 && y >= 0 ) grid[x][y] = colour;
    }

    public void replace ( char colour1, char colour2 )
    {
        for ( int i = 0; i < grid.length; i++ )
        {
            char[] chars = grid[i];
            for ( int j = 0; j < chars.length; j++ )
            {
                if ( chars[j] == colour1 ) chars[j] = colour2;

            }

        }

    }

    public char getCell ( int i, int j )
    {
        return grid[i][j];
    }

    public int getDimension ()
    {
        return dimension;
    }

    @Override
    public String toString ()
    {
        String myString = "";

        for ( int i = 0; i < dimension; i++ )
        {

            for ( int j = 0; j < dimension; j++ )
            {
                myString += grid[j][i];

            }
            myString += "\n";
        }


        return myString;
    }
}
