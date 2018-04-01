import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.*; 
import java.lang.*;

/**
 * @author Zaid Ajaj - s4807561
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


    @Override
    public boolean equals(Object o) 
    {
        SlidingGame other = (SlidingGame)o;

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(board[x][y] != other.board[x][y]) {
                    return false;
                }
            }
        }

        return true;
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

        return result.stream().mapToInt(i -> i).toArray();
    }

    private SlidingGame createNext(int x, int y) {
        SlidingGame game = new SlidingGame(createConfig(copyBoard(this.board)));

        game.board[holeX][holeY] = game.board[x][y];
        game.board[x][y] = HOLE;
        game.holeX = x;
        game.holeY = y;

        return game;
    }

    @Override
    public Collection<Configuration> successors() {
        // the output list of successors
        Collection<Configuration> successors = new ArrayList<>();

        if (holeX > 0) 
        { 
            successors.add(createNext(holeX - 1, holeY));
        }

        if (holeX < N - 1) 
        { 
            successors.add(createNext(holeX + 1, holeY));
        }

        if (holeY > 0) 
        { 
            successors.add(createNext(holeX, holeY - 1));
        }

        if(holeY < N - 1) 
        {
            successors.add(createNext(holeX, holeY + 1));
        }

        return successors;
    }

    @Override
    public int compareTo(Configuration g) {
        return equals(g) ? 0 : 1;
    }

    @Override
    public Configuration parent() {
        return parent;
    }
}
