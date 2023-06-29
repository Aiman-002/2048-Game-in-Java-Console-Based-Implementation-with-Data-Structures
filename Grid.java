

import java.util.*;
public class Grid implements Cloneable
{
    private int[][] Game_board;

    public Grid (int r, int c) {
        Game_board = new int[r][c];
    }

    public Grid(int[][] a)
    {
        Game_board = a;
    }

    public int get_location(Location loc)
    {
        return Game_board[loc.getrow()][loc.getcolumn()];
    }

    public int set_location(Location loc, int newinput)
    {
        int temp = get_location(loc);
        Game_board[loc.getrow()][loc.getcolumn()] = newinput;
        return temp;
    }

    public boolean equals(Grid g)
    {
        return (Arrays.deepEquals(Game_board, g.getArray()));
    }

    public void setArray(int[][] newBoard)
    {
        Game_board = newBoard;
    }
    public int[][] getArray()
    {
        return Game_board;
    }
    public int getno_of_rows()
    {
        return Game_board.length;
    }
    public int getno_of_columns()
    {
        return Game_board[0].length;
    }
    public boolean isEmpty(Location loc)
    {
        return (get_location(loc) == 0);
    }
    public void moves(Location from, Location to)
    {
        set_location(to, get_location(from));
        set_location(from, 0);
    }

    public List<Location> getEmptyLocations()
    {
        LinkedList<Location> empty = new LinkedList<Location>();
        for(int row = 0; row < getno_of_rows(); row++)
            for(int col = 0; col < getno_of_columns(); col++)
                if(Game_board[row][col] == 0)
                    empty.add(new Location(row,col));

        return empty;
    }
    public List<Location> getLocations_Traversal(int direction)
    {
        List<Location> l = toList();

        if (direction == Location.right_move || direction == Location.down_move)
            Collections.reverse(l);

        return l;
    }

    public List<Location> toList()
    {
        ArrayList<Location> l = new ArrayList<Location>();

        for (int row = 0; row < getno_of_rows(); row++)
            for (int col = 0; col < getno_of_columns(); col++)
                l.add(new Location(row, col));

        return l;
    }
    public Grid clone()
    {
        int[][] temp = new int[Game_board.length][Game_board[0].length];
        for(int row = 0; row < Game_board.length; row++)
            for(int col = 0; col < Game_board[0].length; col++)
                temp[row][col] = Game_board[row][col];

        Grid r= new Grid(temp);
        return r;
    }

    public boolean isValid(Location loc)
    {
        return (loc.getrow() >= 0 && loc.getrow() < getno_of_rows() &&
                loc.getcolumn() >= 0 && loc.getcolumn() < getno_of_columns());
    }
    public String toString()
    {
        String resultant = "";


        Location loc;
        for(int i = 0; i < getno_of_rows(); i++)
        {
            for(int j = 0; j < getno_of_columns(); j++)
            {
                loc = new Location (i, j);

                if(get_location(loc) == -2)
                    resultant += "|  x ";
                else if(get_location(loc) == -1)
                    resultant += "|XXXX";
                else if(get_location(loc) == 0)
                    resultant+= "|    ";
                else if(get_location(loc) >= 1000)
                    resultant += "|" + get_location(loc);
                else if(get_location(loc) >= 100)
                    resultant += "| " + get_location(loc);
                else if(get_location(loc) >= 10)
                    resultant += "| " + get_location(loc) + " ";
                else
                    resultant += "| " + get_location(loc) + "  ";
            }

            resultant+= "|\n";
        }

        return resultant;
    }

}



