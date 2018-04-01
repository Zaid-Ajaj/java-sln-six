
import java.util.List;

/**
 * @author Zaid Ajaj - s4807561
 */
public class Program
{
    public static void main(String[] args) {
        int [] game = {1,2,3, 4,9,6, 7,5,8};

        SlidingGame initialGame = new SlidingGame (game);
        System.out.println(initialGame);
        Solver solver = new Solver(initialGame);
        System.out.println(solver.solve());
    }
}
