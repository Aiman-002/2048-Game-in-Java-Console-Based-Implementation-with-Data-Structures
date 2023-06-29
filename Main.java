import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void Display(){
        System.out.println("\t\t\t\tSemester 3\t\t\t\t");
        System.out.println("\t\t\t  Aiman and Huda\t\t\t\t");
        System.out.println("\t\t  Data Structure project\t\t");
        System.out.println("\t\t\t     2048 Game  \t\t\t\t");
        System.out.println("--------------------------------------------");
        System.out.println("\t\t  Use 'A' 'W' 'S' 'D' Keys\t\t\t ");
        System.out.println("\t\t      you can use   \t\t\t");
        System.out.println("\t\t  'Shuffle' and  'Undo'\t\t\t");
        System.out.println("--------------------------------------------");
        System.out.println();

        System.out.println("\t\tPress 1 for Playing Mode");
        System.out.println("\t\tPress 2 for Normal Mode");
        System.out.println("\t\tpress 3 for Game settings");
    }
    private static Scanner scan = new Scanner (System.in);
    private static HashMap<Character,Integer> keyMap = new HashMap<Character,Integer>();

    public static void main(String[] args)
    {

        int input = -1;
        do
        {
            Display();

            input = getIntegerInput(1, 13, "Invalid input!! Enter 1-9 without punctuation marks.");

            switch(input)
            {
                case 1: practiceMode();
                    break;
                case 2: normalMode();
                    break;

                case 3: customizeKeyMap();
                    break;
            }
            System.out.println("Do you want to play again? y/n");
        }
        while(scan.next().charAt(0) == 'y');
    }
    public static void manualPlay(Game game)
    {
        String direction = "";
        boolean winningGame = false;

        System.out.println(game);

        while(!(game.lost()))
        {
            direction = scan.next();
            direction = direction.toLowerCase();


            if(direction.equals("undo"))
                game.undo();


            else if(direction.equals("shuffle"))
                game.shuffle();


            else if(direction.charAt(0) == 'q')
                game.quit();

            else if(keyMap.containsKey(direction.charAt(0)))
            {
                game.action(keyMap.get(direction.charAt(0)));
            }

            else if(direction.charAt(0) == 'w')
                game.action(Location.up_move);

            else if(direction.charAt(0) == 'd')
                game.action(Location.right_move);

            else if(direction.charAt(0) == 's')
                game.action(Location.down_move);

            else if(direction.charAt(0) == 'a')
                game.action(Location.left_move);

            else
            {
                System.out.println("Invalid Commands!!");
                System.out.println("You can only perform: Left, Right, Up, Down, Quit, Undo, Shuffle");
            }
            System.out.println(game);

            if(game.won() && winningGame == false)
            {
                System.out.println("Congratulations! yeah,you won the Game.");
                winningGame = true;
            }
        }
    }
    public static void practiceMode()
    {
        Game game = new Game();
        game.setUndoLimit(-1);
        manualPlay(game);
    }

    public static void normalMode()
    {
        Game game = new Game();
        game.setUndoLimit(10);
        manualPlay(game);
    }
    private static void customizeKeyMap()
    {
        char key;
        System.out.println("Customizing Input");
        System.out.println("Enter the keys which you want to use:");

        System.out.print("For Up move: ");
        key = scan.next().charAt(0);
        keyMap.put(key, Location.up_move);

        System.out.print("for Right move: ");
        key = scan.next().charAt(0);
        keyMap.put(key, Location.right_move);

        System.out.print("For Down move: ");
        key = scan.next().charAt(0);
        keyMap.put(key, Location.down_move);

        System.out.print("For Left move: ");
        key = scan.next().charAt(0);
        keyMap.put(key, Location.left_move);

    }


    public static int getIntegerInput(int minValue, int maxValue, String errorMessage)
    {
        int key = 0;
        boolean isInteger = false,isValid = false;
        do
        {
            try
            {
                key = scan.nextInt();
                isInteger = true;
            }
            catch(InputMismatchException e)
            {
                scan.next();
                isInteger = false;
            }

            if(!isInteger || (key < minValue || key > maxValue))
            {
                isValid = false;
                System.out.println(errorMessage);
            }
            else
                isValid = true;

        } while(!isValid);

        return key;
    }
    public static int getLimitInput()
    {
        String Input_limit;

        while(true)
        {
            Input_limit = scan.nextLine();

            if(Input_limit.equals(""))
                return -1;
            else
            {
                try
                {
                    int moves_Limit = Integer.parseInt(Input_limit);

                    if(moves_Limit >= 0)
                        return moves_Limit;
                    else
                        System.out.println("Please enter value 0 or greater");

                }
                catch (Exception e)
                {
                    System.out.println("Please enter a numerical value 0 or greater");
                    System.out.println("Press [enter] for unlimited");
                }

            }
        }
    }
}

