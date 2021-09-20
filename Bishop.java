
/**
 * Write a description of class Bishop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Bishop extends ChessPiece
{
    public Bishop(Position p, String c, Spaces board)
    {
        pointValue = 3;
        pos = p;
        color = c;
        grid = board;
        hasMoved = false;
        if (color.equalsIgnoreCase("black"))
        {
            IMG_FILE = "Black Bishop.png";
        }
        else
        {
            IMG_FILE = "White Bishop.png";
        }
        possibleMoves = new ArrayList<Position>();
    }
    
    public boolean isKing()
    {
        return false;
    }
    
    public String identity()
    {
        return "bishop";
    }
    
    public String notation()
    {
        return "B";
    }

    public void setPossibleMoves()
    {
        possibleMoves.clear();
        Position posTest = pos;
        for (int i = 0; i < 7; i++)
        {
            posTest = posTest.getTopLeft();
            if (grid.isPositionValid(posTest))
            {
                if (grid.isPositionNull(posTest))
                {
                    possibleMoves.add(posTest);
                }
                else
                {
                    if ((grid.get(posTest).getColor().equals(this.color)))
                    {
                        break;
                    }
                    else
                    {
                        possibleMoves.add(posTest);
                        break;
                    }
                }
            }
        }
        posTest = pos;
        for (int i = 0; i < 7; i++)
        {
            posTest = posTest.getTopRight();
            if (grid.isPositionValid(posTest))
            {
                if (grid.isPositionNull(posTest))
                {
                    possibleMoves.add(posTest);
                }
                else
                {
                    if ((grid.get(posTest).getColor().equals(this.color)))
                    {
                        break;
                    }
                    else
                    {
                        possibleMoves.add(posTest);
                        break;
                    }
                }
            }
        }
        posTest = pos;
        for (int i = 0; i < 7; i++)
        {
            posTest = posTest.getBottomRight();
            if (grid.isPositionValid(posTest))
            {
                if (grid.isPositionNull(posTest))
                {
                    possibleMoves.add(posTest);
                }
                else
                {
                    if ((grid.get(posTest).getColor().equals(this.color)))
                    {
                        break;
                    }
                    else
                    {
                        possibleMoves.add(posTest);
                        break;
                    }
                }
            }
        }
        posTest = pos;
        for (int i = 0; i < 7; i++)
        {
            posTest = posTest.getBottomLeft();
            if (grid.isPositionValid(posTest))
            {
                if (grid.isPositionNull(posTest))
                {
                    possibleMoves.add(posTest);
                }
                else
                {
                    if ((grid.get(posTest).getColor().equals(this.color)))
                    {
                        break;
                    }
                    else
                    {
                        possibleMoves.add(posTest);
                        break;
                    }
                }
            }
        }
    }
}
