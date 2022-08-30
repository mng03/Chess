
/**
 * Write a description of class Knight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Knight extends ChessPiece
{
    public Knight(Position p, String c, Spaces board)
    {
        pointValue = 3;
        pos = p;
        color = c;
        grid = board;
        hasMoved = false;
        if (color.equalsIgnoreCase("black"))
        {
            IMG_FILE = "Black Knight.png";
        }
        else
        {
            IMG_FILE = "White Knight.png";
        }
        possibleMoves = new ArrayList<Position>();
        //setPossibleMoves();
    }
    
    public boolean isKing()
    {
        return false;
    }
    
    public String identity()
    {
        return "knight";
    }
    
    public String notation()
    {
        return "N";
    }

    public void setPossibleMoves()
    {
        possibleMoves.clear();
        Position testPos = pos.getTop().getTopRight();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getTop().getTopLeft();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getLeft().getTopLeft();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getLeft().getBottomLeft();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getBottom().getBottomRight();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getBottom().getBottomLeft();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getRight().getTopRight();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
        testPos = pos.getRight().getBottomRight();
        if (grid.isPositionValid(testPos))
        {
            if(grid.isPositionNull(testPos))
            {
                possibleMoves.add(testPos);
            }
            else
            {
                if (!grid.get(testPos).getColor().equals(this.color))
                {
                    possibleMoves.add(testPos);
                }
            }
        }
    }
}
