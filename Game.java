import java.util.*;
public class Game
{
    private Grid game_board;
    private Stack previous_moves;
    public static final double probability_of_2 = .90;

    private int scores = 0,no_of_turns = 0;
    private boolean quit = false, newGame = true;
    private Date d1;
    private int Remaining_undo  = -1;
    public Game()
    {
        this(4,4);
    }
    public Game(int rows, int columns)
    {
        game_board = new Grid(rows,columns);
        no_of_turns = 1;
        previous_moves = new Stack();
        addRandomPiece();
        addRandomPiece();
    }

    private Game(Game toClone)
    {
        game_board = toClone.game_board.clone();
        no_of_turns = toClone.no_of_turns;
        scores = toClone.scores;
        previous_moves = toClone.previous_moves.clone();

        Remaining_undo = toClone.Remaining_undo;

        quit = toClone.quit;
        newGame = toClone.newGame;
    }
    public void action(int direction)
    {
        if(lost())
            return;
        if(newGame)
            madeFirstMove();
        Grid lastBoard = game_board.clone();
        List<Location> locations = game_board.getLocations_Traversal(direction);
        for(Location loc : locations)
            move(loc, direction);
        if(! game_board.equals(lastBoard))
        {
            no_of_turns++;
            addRandomPiece();
            previous_moves.push(lastBoard, scores);
        }
    }
    private void move(Location from, int direction)
    {
        if(game_board.get_location(from) != -1 && game_board.get_location(from) != 0)
        {
            Location to = from.getAdjacent(direction);
            while(game_board.isValid(to))
            {
                if(game_board.isEmpty(to))
                {
                    game_board.moves(from, to);
                    from = to.clone();
                    to = to.getAdjacent(direction);
                }
                else
                {
                    if(game_board.get_location(from) == game_board.get_location(to))
                        add(from, to);

                    return;
                }
            }
        }
    }
    private void add(Location from, Location to)
    {
        scores += game_board.get_location(to) + game_board.get_location(from);
        game_board.set_location(to, game_board.get_location(to) + game_board.get_location(from));
        game_board.set_location(from, 0);
    }
    private void madeFirstMove()
    {
        newGame = false;
    }
    public void undo()
    {
        if(no_of_turns > 1 && Remaining_undo != 0)
        {
            scores = previous_moves.popScore();
            game_board = previous_moves.popBoard();
            no_of_turns--;

            if(Remaining_undo > 0)
                Remaining_undo--;
            if(Remaining_undo >= 0)
                System.out.println("Undos remaining: " + Remaining_undo);
        }
    }
    public void shuffle()
    {
        if(newGame)
            madeFirstMove();
        LinkedList<Integer> pieces = new LinkedList<Integer>();
        int num;

        for(int row = 0; row < game_board.getno_of_rows(); row++)
            for(int col = 0; col < game_board.getno_of_columns(); col++)
            {
                num = game_board.get_location(new Location(row, col));
                if(num > 0)
                {
                    pieces.add(num);
                    game_board.set_location(new Location(row,col), 0);
                }
            }

        List<Location> empty;
        for(int piece : pieces)
        {
            empty = game_board.getEmptyLocations();
            game_board.set_location(empty.get((int) (Math.random() * empty.size())), piece);
        }

        no_of_turns++;
    }

    public void addRandomPiece()
    {
        if(Math.random() < probability_of_2)
            addRandomPiece(2);
        else
            addRandomPiece(4);

    }
    private void addRandomPiece(int tile)
    {
        List<Location> empty = game_board.getEmptyLocations();
        if(! empty.isEmpty())
        {
            int randomLoc = (int) (Math.random() * empty.size());
            game_board.set_location(empty.get(randomLoc), tile);
        }
    }
    public boolean won()
    {
        return won(2048);
    }
    public boolean won(int winningTile)
    {
        Location loc;
        for(int col = 0; col < game_board.getno_of_columns(); col++)
        {
            for(int row = 0; row < game_board.getno_of_rows(); row++)
            {
                loc = new Location(row, col);
                if(game_board.get_location(loc) >= winningTile)
                    return true;
            }
        }
        return false;
    }
    public boolean lost()
    {
        if(quit)
            return true;
        if(!game_board.getEmptyLocations().isEmpty())
            return false;

        int current = -1;
        int next;
        for(int row = 0; row < game_board.getno_of_rows(); row++)
        {
            for(int col = 0; col < game_board.getno_of_columns(); col++)
            {
                next = current;
                current = game_board.get_location(new Location(row,col));

                if(current == next)
                    return false;
            }
            current = -1;
        }
        for(int col = 0; col < game_board.getno_of_columns(); col++)
        {
            for(int row = 0; row < game_board.getno_of_rows(); row++)
            {
                next = current;
                current = game_board.get_location(new Location(row,col));

                if(current == next)
                    return false;
            }
            current = -1;
        }
        return true;
    }
    public double timePlayed()
    {
        if(d1 == null)
            return 0;

        Date d2 = new Date();
        double seconds = ((d2.getTime() - d1.getTime()) / 1000.0);
        return seconds;
    }
    public void quit()
    {
        quit = true;
    }
    public int highestPiece()
    {
        int highest = 0;
        for(int col = 0; col < game_board.getno_of_columns(); col++)
            for(int row = 0; row < game_board.getno_of_rows(); row++)
            {
                if(game_board.get_location(new Location(row, col)) > highest)
                    highest = game_board.get_location(new Location(row, col));
            }

        return highest;
    }
    public boolean equals(Game otherGame)
    {
        return game_board.equals(otherGame.getGrid()) && scores == otherGame.getScore();
    }
    public Game clone()
    {
        Game game = new Game(this);
        return game;
    }
    public void setUndoLimit(int limit){
        Remaining_undo = limit;
    }
    public int getUndosRemaining(){
        return Remaining_undo;
    }
    public int getScore()
    {
        return scores;
    }
    public int getTurns()
    {
        return no_of_turns;
    }
    public Grid getGrid()
    {
        return game_board;
    }
    private void Display()
    {
        System.out.println(toString());
    }
    public String toString()
    {
        String output = "---------------------------------------------\n";
        output += "Turns:" + no_of_turns + "  Your Score: " + no_of_turns + "\n";

        output += " Remaining undos :";

        if(Remaining_undo >= 0)
            output += Remaining_undo;
        else
            output += " ";

        output += "\n---------------------------------------------\n";
        output += game_board.toString();

        return output;
    }
}


