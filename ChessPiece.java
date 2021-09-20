
/**
 * Abstract class ChessPiece - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
import java.util.*;
public abstract class ChessPiece
{
    protected int pointValue;
    protected List<Position> possibleMoves;
    protected Position pos;
    protected String IMG_FILE;
    protected String color;
    protected Spaces grid;
    protected boolean hasMoved;
    public List<Position> getPossibleMoves()
    {
        return possibleMoves;
    }
    public int getPointValue()
    {
        return pointValue;
    }
    public abstract void setPossibleMoves();
    public abstract boolean isKing();
    public abstract String notation();
    public Position getPosition()
    {
        return pos;
    }
    public String getImgFile()
    {
        return IMG_FILE;
    }
    public String getColor()
    {
        return color;
    }
    public void removeSelfFromGrid()
    {
        grid.remove(pos);
        pos = null;
        grid = null;
    }
    public void putSelfInGrid(Spaces gr, Position p)
    {
        if(gr.isPositionNull(p))
        {
            gr.remove(p);
        }
        gr.put(p, this);
        grid = gr;
        pos = p;
    }
    public void setBoard(Spaces board)
    {
        grid = board;
        setPossibleMoves();
    }
    public void setPosition(Position p)
    {
        pos = p;
    }
    public void hasMoved()
    {
        hasMoved = true;
    }
    public boolean getHasMoved()
    {
        return hasMoved;
    }
    public void clearPossibleMoves()
    {
        possibleMoves.clear();
    }
    public void removePosition(Position p)
    {
        for (int i = 0; i < possibleMoves.size(); i++)
        {
            if (possibleMoves.get(i).getX() == p.getX() && possibleMoves.get(i).getY() == p.getY())
            {
                possibleMoves.remove(i);
                i--;
            }
        }
    }
    public void removePosition(int i)
    {
        possibleMoves.remove(i);
    }
    public void removePositionsExcept(List<Position> positionsNotRemoved)
    {
        boolean remove = true;
        for (int i = 0; i < possibleMoves.size(); i++)
        {
            for (int j = 0; j < positionsNotRemoved.size(); j++)
            {
                remove = true;
                if ((possibleMoves.get(i) == positionsNotRemoved.get(j)))
                {
                    remove = false;
                }
            }
            if (remove)
            {
                possibleMoves.remove(i);
                i--;
            }
        }
    }
    public void addPosition(Position p)
    {
        possibleMoves.add(p);
    }
    public abstract String identity();
    public String toString()
    {
        return identity() + pos;
    }
}
