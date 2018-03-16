import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.3
 * @date 07-03-2016
 * A template implementation of a sliding game 
 * implementing the Graph interface
 */
public class SlidingGame implements Configuration {

    public static final int N = 3, SIZE = N * N, HOLE = SIZE;
    /**
     * The board is represented by a 2-dimensional array; the position of the
     * hole is kept in 2 variables holeX and holeY
     */
    private final int[] start;
    public final int[][] board;
    private int holeX, holeY;
    private Configuration parent;
    /**
     * A constructor that initializes the board with the specified array
     *
     * @param start: a one dimensional array containing the initial board. The
     * elements of start are stored row-wise.
     */
    public SlidingGame(int[] start) {
        parent = null;
        board = new int[N][N];
        this.start = start;
        assert start.length == N * N : "Length of specified board incorrect";

        for (int p = 0; p < start.length; p++) {
            board[p % N][p / N] = start[p];
            if (start[p] == HOLE) {
                holeX = p % N;
                holeY = p / N;
            }
        }
    }


    int[][] copyBoard(int[][] currentBoard)
    {
        int[][] nextBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
            {
                nextBoard[i][j] = currentBoard[i][j];
            }
        }

        return nextBoard;
    }

    /**
     * Converts a board into a printable representation. The hole is displayed
     * as a space
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int puzzel = board[col][row];
                buf.append(puzzel == HOLE ? "  " : puzzel + " ");
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("equals : not supported yet.");
    }

    @Override
    public boolean isSolution() 
    {
        int counter = 1;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (this.board[j][i] != counter)
                {
                    return false;
                }

                counter++;
            }
        }

        return true;
    }

    public static int[] createConfig(int[][] board)
    {
        ArrayList<Integer> result = new ArrayList();
        for (int y = 0; y < N; y++) 
        {
            for (int x = 0; x < N; x++)
            {
                result.add(board[x][y]);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    int[] nextConfigToEast(int[][] current) 
    {
        int[][] clone = copyBoard(current);

        for (int y = 0; y < N; y++)
        {
            for(int x = 0; x < N; x++)
            {
                if (current[x][y] == N * N)
                {
                    if (x < N - 1)
                    {
                        int temp = clone[x + 1][y];
                        clone[x + 1][y] = N * N;
                        clone[x][y] = temp;
                    }
                }
            }
        }

        return null;
    }

    private Configuration createNextConfigInDirection(Direction direction)
    {
        if (direction == Direction.EAST)
        {
            if (holeX + 1 > N) 
            {
                return new SlidingGame(copyBoard(this.board)); 
            }


            holeX = holeX + 1;

            

        }
        else if (direction == Direction.WEST)
        {

        }
        else if (direction == Direction.NORTH)
        {

        }
        else
        {

        }

        return null;
    }

    @Override
    public Collection<Configuration> successors() 
    {
        throw new UnsupportedOperationException("successors : not supported yet.");
    }

    @Override
    public int compareTo(Configuration g) {
        throw new UnsupportedOperationException("compareTo : not supported yet.");
    }

    @Override
    public Configuration parent() {
        return parent;
    }
}
