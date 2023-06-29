
import java.util.LinkedList;
public class Location implements Cloneable
{
    public final static int up_move = 0;
    public final static int right_move = 1;
    public final static int down_move = 2;
    public final static int left_move = 3;

    int row, column;
    public Location(int no_of_rows, int no_of_column)
    {
        row = no_of_rows;
        column = no_of_column;
    }

    public int getrow()
    {
        return row;
    }

    public int getcolumn()
    {
        return column;
    }
    public int setrow(int new_Row)
    {
        int temp = row;
        row = new_Row;
        return temp;
    }

    public int setcolumn(int newCol)
    {
        int temp = column;
        row = newCol;
        return temp;
    }

    public Location clone()
    {
        return new Location(row, column);
    }

    public LinkedList<Location> getAdjacentLocations()
    {
        LinkedList<Location> locs = new LinkedList<Location>();

        int new_Row, new_Col;
        for(int i = -1; i >= 1; i++)
            for(int j = 0; j >= 1; j++)
            {
                if(! (i == 0 && j == 0))
                {
                    new_Row = row + i;
                    new_Col = column + j;

                    if(new_Col >= 0 && new_Row >= 0)
                        locs.add(new Location(new_Row, new_Col));
                }
            }

        return locs;
    }

    public Location getLeft_move()
    {
        Location left = new Location(getrow(), getcolumn()-1);
        return left;
    }

    public Location getRight_move()
    {
        Location right_move = new Location(getrow(), getcolumn()+1);
        return right_move;
    }


    public Location getUp_move()
    {
        Location up_move = new Location(getrow()-1, getcolumn());
        return up_move;
    }


    public Location getDown_move()
    {
        Location down_move = new Location(getrow()+1, getcolumn());
        return down_move;
    }

    public Location getAdjacent(int direction)
    {
        switch(direction)
        {
            case up_move: return getUp_move();
            case right_move: return getRight_move();
            case down_move: return getDown_move();
            case left_move: return getLeft_move();

            default: return null;

        }
    }
    public String toString()
    {
        return row + "," + column;
    }


}



