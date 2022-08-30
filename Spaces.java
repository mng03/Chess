
/**
 * Write a description of class Spaces here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Spaces
{
    private ChessPiece[][] board;

    public Spaces()
    {
        board = new ChessPiece[8][8];
    }

    public boolean isPositionNull(Position p)
    {
        if (get(p) != null)
        {  
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isPositionValid(Position p)
    {
        if (p.getX() >= 0 && p.getX() < 8)
            if (p.getY() >= 0 && p.getY() < 8)
                return true;
        return false;
    }

    public ChessPiece get(Position p)
    {
        return board[p.getX()][p.getY()];
    }

    public ChessPiece put(Position p, ChessPiece obj)
    {
        ChessPiece output = null;
        if (board[p.getX()][p.getY()] != null)
        {
            output = board[p.getX()][p.getY()];
        }
        board[p.getX()][p.getY()] = obj;
        return output;
    }

    public Object remove(Position p)
    {
        Object output = get(p);
        put(p, null);
        return output;
    }
    
    public List<Position> reset()
    {
        List<Position> allPossibleMoves = new ArrayList<Position>();
        for (ChessPiece[] array : board)
        {
            for (ChessPiece piece : array)
            {
                if (piece != null)
                {
                    piece.setBoard(this);
                    for (Position pos : piece.getPossibleMoves())
                    {
                        allPossibleMoves.add(pos);
                    }
                }
            }
        }
        return allPossibleMoves;
    }
    
    public void removeAllPositions(List<Position> positionsNotToBeRemoved)
    {
        for (ChessPiece[] array : board)
        {
            for (ChessPiece piece : array)
            {
                if (piece != null)
                {
                    piece.removePositionsExcept(positionsNotToBeRemoved);
                    /*for(int j = 0; j < positionsNotToBeRemoved.size(); j++)
                    {
                        for (int i = 0; i < piece.getPossibleMoves().size(); i++)
                        {
                            if (!((positionsNotToBeRemoved.get(j).getX() == piece.getPossibleMoves().get(i).getX()) && (positionsNotToBeRemoved.get(j).getY() == piece.getPossibleMoves().get(i).getY())))
                            {
                                piece.removePosition(i);
                            }
                        }
                    }*/
                }
            }
        }
    }
    
    public List<Position> whitePossibleMoves()
    {
        reset();
        List<Position> allPossibleMoves = new ArrayList<Position>();
        for (ChessPiece[] array : board)
        {
            for (ChessPiece piece : array)
            {
                if (piece != null && piece.getColor().equalsIgnoreCase("white"))
                {
                    for (Position pos : piece.getPossibleMoves())
                    {
                        allPossibleMoves.add(pos);
                    }
                }
            }
        }
        return allPossibleMoves;
    }
    
    public List<Position> blackPossibleMoves()
    {
        reset();
        List<Position> allPossibleMoves = new ArrayList<Position>();
        for (ChessPiece[] array : board)
        {
            for (ChessPiece piece : array)
            {
                if (piece != null && piece.getColor().equalsIgnoreCase("black"))
                {
                    for (Position pos : piece.getPossibleMoves())
                    {
                        allPossibleMoves.add(pos);
                    }
                }
            }
        }
        return allPossibleMoves;
    }
    
    public List<Position> whiteCurrentPossibleMoves()
    {
        List<Position> allPossibleMoves = new ArrayList<Position>();
        for (ChessPiece[] array : board)
        {
            for (ChessPiece piece : array)
            {
                if (piece != null && piece.getColor().equalsIgnoreCase("white"))
                {
                    for (Position pos : piece.getPossibleMoves())
                    {
                        allPossibleMoves.add(pos);
                    }
                }
            }
        }
        return allPossibleMoves;
    }
    
    public List<Position> blackCurrentPossibleMoves()
    {
        List<Position> allPossibleMoves = new ArrayList<Position>();
        for (ChessPiece[] array : board)
        {
            for (ChessPiece piece : array)
            {
                if (piece != null && piece.getColor().equalsIgnoreCase("black"))
                {
                    for (Position pos : piece.getPossibleMoves())
                    {
                        allPossibleMoves.add(pos);
                    }
                }
            }
        }
        return allPossibleMoves;
    }
}
