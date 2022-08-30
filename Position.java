
/**
 * Write a description of class Position here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Position
{
    private int x, y;

    public Position(int xPt, int yPt)
    {
        x = xPt;
        y = yPt;
    }

    public void changePosition(int xPt, int yPt)
    {
        x = xPt;
        y = yPt;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Position getTop()
    {
        return new Position(x - 1, y);
    }
    
    public Position getBottom()
    {
        return new Position(x + 1, y);
    }
    
    public Position getLeft()
    {
        return new Position(x, y - 1);
    }
    
    public Position getRight()
    {
        return new Position(x, y + 1);
    }
    
    public Position getTopLeft()
    {
        return new Position(x - 1, y - 1);
    }
    
    public Position getTopRight()
    {
        return new Position(x - 1, y + 1);
    }
    
    public Position getBottomLeft()
    {
        return new Position(x + 1, y - 1);
    }
    
    public Position getBottomRight()
    {
        return new Position(x + 1, y + 1);
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
