
import java.util.*;


/**
 * @author Zaid Ajaj - s4807561
 */
public class Solver
{
   // A queue for maintaining graphs that are not visited yet.
    Queue<Configuration> toExamine;

    public Solver( Configuration g ) {
        toExamine = new PriorityQueue<>();
        toExamine.add(g);
    }

    /**
     * A skeleton implementation of the solver
     *
     * @return a string representation of the solution
     */
    public String solve() 
    {
        while ( ! toExamine.isEmpty() ) 
        {
            Configuration next = toExamine.remove();
            System.out.println(next);

            if ( next.isSolution() ) 
            {
                return "Success!";
            } 
            else 
            {
                for ( Configuration succ : next.successors() ) 
                {
                    toExamine.add(succ);
                }
            }
        }
        return "Failure!";
    }
    
}
