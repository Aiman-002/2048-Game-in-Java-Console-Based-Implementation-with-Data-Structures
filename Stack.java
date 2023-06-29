
import java.util.LinkedList;
public class Stack implements Cloneable
{
    LinkedList<Grid> stack_Board_Game;
    LinkedList<Integer> stackScore;
    public Stack()
    {
        stack_Board_Game = new LinkedList<Grid>();
        stackScore = new LinkedList<Integer>();
    }

    public void push(Grid board, int score)
    {
        stackScore.add(score);
        stack_Board_Game.add(board);
    }

    public int popScore()
    {
        return stackScore.removeLast();
    }

    public Grid popBoard()
    {
        return stack_Board_Game.removeLast();
    }

    public int Visible_Score()
    {
        return stackScore.getLast();
    }

    public Grid Visible_Board()
    {
        return stack_Board_Game.getLast();
    }
    public Stack clone()
    {
        Stack Scores = new Stack();

        for(int i = stack_Board_Game.size() - 1; i >= 0; i--)
            Scores.push(stack_Board_Game.get(i), stackScore.get(i));

        return Scores;
    }
}


