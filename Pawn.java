
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Pawn extends ChessPiece
{
    public Pawn(Position p, String c, Spaces board)
    {
        pointValue = 1;
        pos = p;
        color = c;
        hasMoved = false;
        grid = board;
        if (color.equalsIgnoreCase("black"))
        {
            IMG_FILE = "Black Pawn.png";
        }
        else
        {
            IMG_FILE = "White Pawn.png";
        }
        possibleMoves = new ArrayList<Position>();
    }

    public boolean isKing()
    {
        return false;
    }
    
    public String identity()
    {
        return "pawn";
    }

    public String notation()
    {
        return "";
    }
    
    public void setPossibleMoves()
    {
        possibleMoves.clear();
        if (color.equals("white"))
        {
            if (grid.isPositionValid(pos.getTop()) && grid.isPositionNull(pos.getTop()))
            {
                possibleMoves.add(pos.getTop());
                if (!hasMoved)
                {
                    if (grid.isPositionValid(pos.getTop().getTop()) && grid.isPositionNull(pos.getTop().getTop()))
                    {
                        possibleMoves.add(pos.getTop().getTop());
                    }
                }
            }
            if (grid.isPositionValid(pos.getTopLeft()) && !grid.isPositionNull(pos.getTopLeft()))
            {
                if(!grid.get(pos.getTopLeft()).getColor().equals(this.color))
                {
                    possibleMoves.add(pos.getTopLeft());
                }
            }
            if (grid.isPositionValid(pos.getTopRight()) && !grid.isPositionNull(pos.getTopRight()))
            {
                if(!grid.get(pos.getTopRight()).getColor().equals(this.color))
                {
                    possibleMoves.add(pos.getTopRight());
                }
            }
        }
        else
        {
            if (grid.isPositionValid(pos.getBottom()) && grid.isPositionNull(pos.getBottom()))
            {
                possibleMoves.add(pos.getBottom());
                if (!hasMoved)
                {
                    if (grid.isPositionValid(pos.getBottom().getBottom()) && grid.isPositionNull(pos.getBottom().getBottom()))
                    {
                        possibleMoves.add(pos.getBottom().getBottom());
                    }
                }
            }
            if (grid.isPositionValid(pos.getBottomLeft()) && !grid.isPositionNull(pos.getBottomLeft()))
            {
                if(!grid.get(pos.getBottomLeft()).getColor().equals(this.color))
                {
                    possibleMoves.add(pos.getBottomLeft());
                }
            }
            if (grid.isPositionValid(pos.getBottomRight()) && !grid.isPositionNull(pos.getBottomRight()))
            {
                if(!grid.get(pos.getBottomRight()).getColor().equals(this.color))
                {
                    possibleMoves.add(pos.getBottomRight());
                }
            }
        }
    }
}
