import java.io.Console;
import java.util.*;

public class TicTacToe{

   /**
     * <b>main</b> of the application. Creates the instance of  TicTacToeGame 
     * and starts the game. If two parameters lines  and columns
     * are passed, they are used. If the parameters lines, columns
     * and win are passed, they are used.
     * Otherwise, a default value is used. Defaults values (3) are also
     * used if the paramters are too small (less than 2).
     * @param args
     *            command lines parameters
     */
     public static void main(String[] args) {

        StudentInfo.display();
        Scanner sc = new Scanner(System.in);

        Console console = System.console();
        TicTacToeGame game;
        int lines, columns, win;
        lines = 3;
        columns = 3;
        win = 3;

        if (args.length >= 2) {
            lines = Integer.parseInt(args[0]);
            if(lines<2){
                System.out.println("Invalid argument, using default...");
                lines = 3;
            }
            columns = Integer.parseInt(args[1]);
            if(columns<2){
                System.out.println("Invalid argument, using default...");
                columns = 3;
            }
        }
        
        if (args.length == 3){// The third argument will be ignored and 3 will be used
            win=3; //for simplcity, we will only consider the case of 3 cells to win
        }
        
        if (args.length > 3){
            System.out.println("Too many arguments. Only the first 3 are used.");
        } 
        
        game = new TicTacToeGame(lines, columns, win);

        while(game.getGameState() == GameState.PLAYING) {
            System.out.println(game);
            if (game.nextCellValue() == CellValue.X)
                System.out.print("X to play: ");
            else
                System.out.print("O to play: ");
            game.play(sc.nextInt());
            
        }

        System.out.println(game);
        System.out.println("Result: " + game.getGameState());

    }

}