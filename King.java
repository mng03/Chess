
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class King extends ChessPiece
{
    public King(Position p, String c, Spaces board)
    {
        pointValue = 0;
        pos = p;
        color = c;
        grid = board;
        hasMoved = false;
        if (color.equalsIgnoreCase("black"))
        {
            IMG_FILE = "Black King.png";
        }
        else
        {
            IMG_FILE = "White King.png";
        }
        possibleMoves = new ArrayList<Position>();
    }

    public boolean isKing()
    {
        return true;
    }

    public String identity()
    {
        return "king";
    }
    
    public String notation()
    {
        return "K";
    }

    public void setPossibleMoves()
    {
        possibleMoves.clear();
        Position posTest = pos;
        posTest = posTest.getTopLeft();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getTopRight();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getBottomRight();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getBottomLeft();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getTop();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getBottom();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getRight();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

        posTest = pos;
        posTest = posTest.getLeft();
        if (grid.isPositionValid(posTest))
        {
            if (grid.isPositionNull(posTest))
            {
                possibleMoves.add(posTest);
            }
            else
            {
                if (!(grid.get(posTest).getColor().equals(this.color)))
                {
                    possibleMoves.add(posTest);
                }
            }
        }

    }
}
