
/**
 * Write a description of class Chess here.
 *
 * @Mark Glinberg
 * @Check Functionality doesn't work yet.
 * @Most fun code is in ChessGUI
 */
import java.util.*;
public class Chess
{
    public static void main(String[] args)
    {
        Chess chess = new Chess();
    }

    private ChessGUI display;
    private Spaces board;
    private boolean whiteTurn;
    private King blackKing;
    private King whiteKing;
    private Rook rightWhiteRook;
    private Rook leftWhiteRook;
    private Rook rightBlackRook;
    private Rook leftBlackRook;

    public Chess()
    {
        board = new Spaces();
        whiteTurn = true;
        for (int i = 0; i < 8; i++)
        {
            Pawn blackPawn = new Pawn(new Position(1, i), "black", board);
            board.put(new Position(1, i), blackPawn);
            Pawn whitePawn = new Pawn(new Position(6, i), "white", board);
            board.put(new Position(6, i), whitePawn);
        }
        leftBlackRook = new Rook(new Position(0, 0), "black", board);
        Knight blackKnight = new Knight(new Position(0, 1), "black", board);
        Bishop blackBishop = new Bishop(new Position(0, 2), "black", board);
        Queen blackQueen = new Queen(new Position(0, 3), "black", board);
        blackKing = new King(new Position(0, 4), "black", board);
        Bishop blackBishop2 = new Bishop(new Position(0, 5), "black", board);
        Knight blackKnight2 = new Knight(new Position(0, 6), "black", board);
        rightBlackRook = new Rook(new Position(0, 7), "black", board);
        leftWhiteRook = new Rook(new Position(7, 0), "white", board);
        Knight whiteKnight = new Knight(new Position(7, 1), "white", board);
        Bishop whiteBishop = new Bishop(new Position(7, 2), "white", board);
        Queen whiteQueen = new Queen(new Position(7, 3), "white", board);
        whiteKing = new King(new Position(7, 4), "white", board);
        Bishop whiteBishop2 = new Bishop(new Position(7, 5), "white", board);
        Knight whiteKnight2 = new Knight(new Position(7, 6), "white", board);
        rightWhiteRook = new Rook(new Position(7, 7), "white", board);
        board.put(new Position(0, 0), leftBlackRook);
        board.put(new Position(0, 1), blackKnight);
        board.put(new Position(0, 2), blackBishop);
        board.put(new Position(0, 3), blackQueen);
        board.put(new Position(0, 4), blackKing);
        board.put(new Position(0, 5), blackBishop2);
        board.put(new Position(0, 6), blackKnight2);
        board.put(new Position(0, 7), rightBlackRook);
        board.put(new Position(7, 0), leftWhiteRook);
        board.put(new Position(7, 1), whiteKnight);
        board.put(new Position(7, 2), whiteBishop);
        board.put(new Position(7, 3), whiteQueen);
        board.put(new Position(7, 4), whiteKing);
        board.put(new Position(7, 5), whiteBishop2);
        board.put(new Position(7, 6), whiteKnight2);
        board.put(new Position(7, 7), rightWhiteRook);
        display = new ChessGUI(board, this);
        board.reset();
    }
    
    public boolean checkLeftEnpasan(ChessPiece pawn, ChessPiece enpasan)
    {
        if (board.isPositionValid(enpasan.getPosition().getLeft()) && !(board.isPositionNull(enpasan.getPosition().getLeft())))
        {
            if (board.get(enpasan.getPosition().getLeft()) == pawn)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightEnpasan(ChessPiece pawn, ChessPiece enpasan)
    {
        if (board.isPositionValid(enpasan.getPosition().getRight()) && !(board.isPositionNull(enpasan.getPosition().getRight())))
        {
            if (board.get(enpasan.getPosition().getRight()) == pawn)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkRightCastle()
    {
        if (!checkCheck())
        {
            if (whiteTurn)
            {
                if ((!rightWhiteRook.getHasMoved()) && (!whiteKing.getHasMoved()))
                {
                    if (board.get(whiteKing.getPosition().getRight().getRight()) == null && !checkCheck(whiteKing.getPosition().getRight().getRight()))
                    {
                        if (board.get(whiteKing.getPosition().getRight()) == null && !checkCheck(whiteKing.getPosition().getRight()))
                        {
                            return true;
                        }
                    }
                }
            }
            else
            {
                if ((!rightBlackRook.getHasMoved()) && (!blackKing.getHasMoved()))
                {
                    if (board.get(blackKing.getPosition().getRight().getRight()) == null && !checkCheck(blackKing.getPosition().getRight().getRight()))
                    {
                        if (board.get(blackKing.getPosition().getRight()) == null && !checkCheck(blackKing.getPosition().getRight()))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkLeftCastle()
    {
        if (!checkCheck())
        {
            if (whiteTurn)
            {
                if ((!leftWhiteRook.getHasMoved()) && (!whiteKing.getHasMoved()))
                {
                    if (board.get(whiteKing.getPosition().getLeft().getLeft().getLeft()) == null)
                    {
                        if (board.get(whiteKing.getPosition().getLeft().getLeft()) == null && !checkCheck(whiteKing.getPosition().getLeft().getLeft()))
                        {
                            if (board.get(whiteKing.getPosition().getLeft()) == null && !checkCheck(whiteKing.getPosition().getLeft()))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
            else
            {
                if ((!leftBlackRook.getHasMoved()) && (!blackKing.getHasMoved()))
                {
                    if (board.get(blackKing.getPosition().getLeft().getLeft().getLeft()) == null)
                    {
                        if (board.get(blackKing.getPosition().getLeft().getLeft()) == null && !checkCheck(blackKing.getPosition().getLeft().getLeft()))
                        {
                            if (board.get(blackKing.getPosition().getLeft()) == null && !checkCheck(blackKing.getPosition().getLeft()))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkCheck()
    {
        if (whiteTurn)
        {
            List<Position> allPositions = board.blackPossibleMoves();
            for (Position pos : allPositions)
            {
                if (pos.getX() == (whiteKing.getPosition().getX()) && pos.getY() == (whiteKing.getPosition().getY()))
                {
                    return true;
                }
            }
        }
        else
        {
            List<Position> allPositions = board.whitePossibleMoves();
            for (Position pos : allPositions)
            {
                if (pos.getX() == (blackKing.getPosition().getX()) && pos.getY() == (blackKing.getPosition().getY()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCheck(Position p)
    {
        if (whiteTurn)
        {
            List<Position> allPositions = board.blackPossibleMoves();
            for (Position pos : allPositions)
            {
                if (board.get(pos) == null && (pos.getX() == (p.getX()) && pos.getY() == (p.getY())))
                {
                    return true;
                }
                if (board.get(pos) != null)
                {
                    ChessPiece c = (ChessPiece) board.remove(pos);
                    List<Position> allNewTestPositions = board.blackPossibleMoves();
                    for (Position position : allNewTestPositions)
                    {
                        if ((position.getX() == p.getX()) && (position.getY() == p.getY()))
                        {
                            board.put(pos, c);
                            return true;
                        }
                    }
                    board.put(pos, c);
                }
            }
        }
        else
        {
            List<Position> allPositions = board.whitePossibleMoves();
            for (Position pos : allPositions)
            {
                if (board.get(pos) == null && (pos.getX() == (p.getX()) && pos.getY() == (p.getY())))
                {
                    return true;
                }
                if (board.get(pos) != null)
                {
                    ChessPiece c = (ChessPiece) board.remove(pos);
                    List<Position> allNewTestPositions = board.whitePossibleMoves();
                    for (Position position : allNewTestPositions)
                    {
                        if ((position.getX() == p.getX()) && (position.getY() == p.getY()))
                        {
                            board.put(pos, c);
                            return true;
                        }
                    }
                    board.put(pos, c);
                }
            }
        }
        return false;
    }

    public boolean checkMate()
    {
        if (whiteTurn)
        {
            if (board.whiteCurrentPossibleMoves().size() == 0)
            {
                return true;
            }
        }
        else
        {
            if (board.blackCurrentPossibleMoves().size() == 0)
            {
                return true;
            }
        }
        return false;
    }

    public boolean whiteTurn()
    {
        return whiteTurn;
    }

    public void switchTurns()
    {
        if (whiteTurn)
            whiteTurn = false;
        else
            whiteTurn = true;
    }

    public List<Position> whiteKingSetMoves()
    {
        whiteKing.setPossibleMoves();
        return whiteKing.getPossibleMoves();
    }

    public List<Position> blackKingSetMoves()
    {
        blackKing.setPossibleMoves();
        return blackKing.getPossibleMoves();
    }

    public void whiteKingRemovePosition(Position p)
    {
        whiteKing.removePosition(p);
    }

    public void blackKingRemovePosition(Position p)
    {
        blackKing.removePosition(p);
    }
    
    public void whiteKingAddPosition(Position p)
    {
        whiteKing.addPosition(p);
    }
    
    public void blackKingAddPosition(Position p)
    {
        blackKing.addPosition(p);
    }
}
