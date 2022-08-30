
/**
 * Write a description of class Queen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Queen extends ChessPiece
{
    public Queen(Position p, String c, Spaces board)
    {
        pointValue = 9;
        pos = p;
        color = c;
        grid = board;
        hasMoved = false;
        if (color.equalsIgnoreCase("black"))
        {
            IMG_FILE = "Black Queen.png";
        }
        else
        {
            IMG_FILE = "White Queen.png";
        }
        possibleMoves = new ArrayList<Position>();
    }
    
    public boolean isKing()
    {
        return false;
    }
    
    public String identity()
    {
        return "queen";
    }
    
    public String notation()
    {
        return "Q";
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
        posTest = pos;
        for (int i = 0; i < 7; i++)
        {
            posTest = posTest.getTop();
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
            posTest = posTest.getBottom();
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
            posTest = posTest.getRight();
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
            posTest = posTest.getLeft();
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
