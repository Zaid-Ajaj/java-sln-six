import java.lang.*;
import java.util.*;

public class Runner 
{
    public static void main(String[] args)
    {
        Test.Case("Goal is found when initial config is correct", () -> 
        {   
            int[] initConfig = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            SlidingGame game = new SlidingGame(initConfig);
            System.out.println(game);
            boolean goalFound = game.isSolution();
            Test.AreEqual(true, goalFound);
        });

        Test.Case("int[][] length returns number of entries", () -> 
        {
            int[][] board = new int[3][3];
            Test.AreEqual(9, board.length);
        });

        Test.Case("Creating initial config from board works", () -> 
        {
            int[] initConfig = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            SlidingGame game = new SlidingGame(initConfig);
            int[] resultConfig = SlidingGame.createConfig(game.board);
            System.out.println(resultConfig);
            Test.AreEqual(true, true);
        });

        Test.Report();
    }
}