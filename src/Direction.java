/**
 * @author Zaid Ajaj - s4807561
 */
public enum Direction
{
    NORTH (0,-1), EAST (1,0), SOUTH(0,1), WEST(-1,0);
    
    private final int dx, dy;
    private Direction (int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    public int GetDX () {
        return dx;
    }

    public int GetDY () {
        return dy;
    }
}

