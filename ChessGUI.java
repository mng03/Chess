
/**
 * Write a description of class JFrame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
public class ChessGUI
{ 
    private JPanel[][] spots;
    private JFrame frame;
    private Spaces board;
    private ChessPiece activePiece;
    private Chess game;
    private ChessPiece enpasanPiece;
    private final String ALPHABET = "abcdefgh";
    private final String BLACK_KING = "Black King.png";
    private final String WHITE_KING = "White King.png";
    private final String BLACK_QUEEN = "Black Queen.png";
    private final String WHITE_QUEEN = "White Queen.png";
    private final String BLACK_ROOK = "Black Rook.png";
    private final String WHITE_ROOK = "White Rook.png";
    private final String BLACK_BISHOP = "Black Bishop.png";
    private final String WHITE_BISHOP = "White Bishop.png";
    private final String BLACK_KNIGHT = "Black Knight.png";
    private final String WHITE_KNIGHT = "White Knight.png";
    private final String BLACK_PAWN = "Black Pawn.png";
    private final String WHITE_PAWN = "White Pawn.png";
    private final String MOVE_DOT = "Move Dot.png";
    public ChessGUI(Spaces board, Chess runner)
    {   
        this.board = board;
        game = runner;
        spots = new JPanel[8][8];
        Color darkBrown = new Color(139,69,19);
        Color lightBrown = new Color(222,184,135);
        boolean light = false;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                spots[i][j] = new JPanel();
                if (light)
                {
                    spots[i][j].setBackground(darkBrown);
                    if (!(j == 7))
                        light = false;
                }
                else
                {
                    spots[i][j].setBackground(lightBrown);
                    if (!(j == 7))
                        light = true;
                }
            }
        }
        frame = new JFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if ((j == 0) && (i != 8))
                {
                    JPanel numberPanel = new JPanel();
                    numberPanel.setBackground(new Color(132, 67, 2));
                    JLabel number = new JLabel("" + (8 - i));
                    number.setFont(new Font("Times New Roman", Font.BOLD, 36));
                    number.setHorizontalAlignment(JLabel.CENTER);
                    number.setVerticalAlignment(JLabel.CENTER);
                    numberPanel.add(number);
                    pane.add(numberPanel);
                }
                else if ((i == 8) && (j == 0))
                {
                    JPanel blank = new JPanel();
                    blank.setBackground(new Color(132, 67, 2));
                    pane.add(blank);
                }
                else if ((i == 8) && (j > 0))
                {
                    JPanel letterPanel = new JPanel();
                    letterPanel.setBackground(new Color(132, 67, 2));
                    String letters = "ABCDEFGH";
                    JLabel letter = new JLabel("" + letters.substring(j-1, j));
                    letter.setFont(new Font("Times New Roman", Font.BOLD, 36));
                    letterPanel.add(letter);
                    pane.add(letterPanel);
                }
                else
                {
                    pane.add(spots[i][j-1]);
                }
            }
        }
        setBoard(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("White:\t\t\tBlack:");
    }

    public void setBoard(Spaces board)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Position p = new Position(i, j);
                ChessPiece piece = board.get(p);
                if (piece != null)
                {
                    spots[i][j] = setImage(spots[i][j], piece.getImgFile());
                }
            }
        }
    }

    public JPanel[][] getSpots()
    {
        return spots;
    }

    public JPanel setImage(JPanel panel, String fileName)
    {
        ImageIcon img = new ImageIcon(fileName);
        JButton pic = new JButton(img);
        pic.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    showMoves(e);
                }
            });
        pic.setOpaque(false);
        pic.setContentAreaFilled(false);
        pic.setBorderPainted(false);
        panel.add(pic);
        return panel;
    }

    public void showMoves(ActionEvent e)
    {
        JPanel current = (JPanel)((JButton)e.getSource()).getParent();
        int x = 0;
        int y = 0;
        boolean found = false;
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (current == spots[i][j])
                {
                    x = i;
                    y = j;
                    found = true;
                    break;
                }
            }
        }
        if (!found)
        {
            return;
        }
        if ((board.get(new Position(x, y)).getColor().equals("white") && game.whiteTurn()) || (board.get(new Position(x, y)).getColor().equals("black") && !game.whiteTurn()))
        {
            if (activePiece == null)
            {
                activePiece = board.get(new Position(x, y));
                for(Position p : activePiece.getPossibleMoves())
                {
                    spots[p.getX()][p.getY()] = setImageDot(spots[p.getX()][p.getY()]);
                }
                if (activePiece.isKing())
                {
                    castleCheck();
                }
                if (activePiece.identity().equals("pawn") && enpasanPiece != null)
                {
                    if (game.checkLeftEnpasan(activePiece, enpasanPiece) || game.checkRightEnpasan(activePiece, enpasanPiece))
                    {
                        showEnpasan();
                    }
                }
            }
            else if (activePiece != board.get(new Position(x, y)))
            {
                if (activePiece.getColor().equals(board.get(new Position(x, y)).getColor()))
                {
                    for(Position p : activePiece.getPossibleMoves())
                    {
                        spots[p.getX()][p.getY()] = removeDot(spots[p.getX()][p.getY()]);
                    }
                    activePiece = board.get(new Position(x, y));
                    for(Position p : activePiece.getPossibleMoves())
                    {
                        spots[p.getX()][p.getY()] = setImageDot(spots[p.getX()][p.getY()]);
                    }
                    if (activePiece.isKing())
                    {
                        castleCheck();
                    }
                    if (activePiece.identity().equals("pawn") && enpasanPiece != null)
                    {
                        if (game.checkLeftEnpasan(activePiece, enpasanPiece) || game.checkRightEnpasan(activePiece, enpasanPiece))
                        {
                            showEnpasan();
                        }
                    }
                }
            }
            else
            {
                for(Position p : activePiece.getPossibleMoves())
                {
                    spots[p.getX()][p.getY()] = removeDot(spots[p.getX()][p.getY()]);
                }
                activePiece = null;
            }
        }
        frame.revalidate();
        frame.repaint();
    }

    public JPanel setImageDot(JPanel panel)
    {
        ImageIcon img = new ImageIcon("Move Dot.png");
        JButton pic = new JButton(img);
        pic.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    move(e);
                }
            });
        pic.setOpaque(false);
        pic.setContentAreaFilled(false);
        pic.setBorderPainted(false);
        panel.add(pic, 0);
        return panel;
    }

    public JPanel removeDot(JPanel panel)
    {
        panel.remove(panel.getComponents()[0]);
        return panel;
    }

    public void move(ActionEvent e)
    {
        String log = "";
        JPanel current = (JPanel)((JButton)e.getSource()).getParent();
        int x = 0;
        int y = 0;
        boolean found = false;
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (current == spots[i][j])
                {
                    x = i;
                    y = j;
                    found = true;
                    break;
                }
            }
        }
        if (!found)
        {
            return;
        }
        for(Position p : activePiece.getPossibleMoves())
        {
            spots[p.getX()][p.getY()] = removeDot(spots[p.getX()][p.getY()]);
        }
        Position activePos = activePiece.getPosition();
        Position move = new Position(x, y);
        log += activePiece.notation();
        if (!board.isPositionNull(new Position(x, y)))
        {
            spots[x][y] = removeChessPiece(spots[x][y]);
            board.remove(move);
            log += "x";
            if (activePiece.identity().equals("pawn"))
            {
                log = ALPHABET.substring(activePos.getY(), activePos.getY() + 1) + log;
            }
        }
        log += ALPHABET.substring(y, y+1) + (8-x);
        boolean promotion = false;
        spots[x][y] = setImage(spots[x][y], activePiece.getImgFile());
        spots[activePos.getX()][activePos.getY()] = removeChessPiece(spots[activePos.getX()][activePos.getY()]);
        board.remove(activePos);
        board.put(move, activePiece);
        activePiece.setPosition(move);
        activePiece.hasMoved();
        if (activePiece.identity().equals("pawn") && (x == 0 || x == 7))
        {
            if (game.whiteTurn())
            {
                whitePromoteScreen(move);
            }
            else
            {
                blackPromoteScreen(move);
            }
            spots[x][y] = removeChessPiece(spots[x][y]);
            spots[x][y] = setImage(spots[x][y], board.get(move).getImgFile());
            board.get(move).setPosition(move);
            board.get(move).hasMoved();
            promotion = true;
        }
        board.reset();
        game.switchTurns();
        if (promotion)
        {
            log += board.get(move).notation();
        }
        if (activePiece.identity().equals("pawn") && (activePos.getX() == x - 2 && activePos.getY() == y))
        {
            enpasanPiece = activePiece;
        }
        else
        {
            enpasanPiece = null;
        }
        activePiece = null;
        if(game.checkCheck())
        {
            //checkProtocol();
            log += "+";
        }
        else
        {
            //antiCheckProtocol();
        }
        frame.revalidate();
        frame.repaint();
        if (game.whiteTurn())
        {
            System.out.print(log + "\n");
        }
        else
        {
            System.out.print(log + "\t\t\t");
        }
    }

    public JPanel removeChessPiece(JPanel panel)
    {
        panel.remove(panel.getComponents()[0]);
        return panel;
    }

    public void checkProtocol()
    {
        if(game.whiteTurn())
        {
            ArrayList<Position> whiteKingMoves = (ArrayList)game.whiteKingSetMoves();
            ArrayList<Position> whiteKingBadMoves = new ArrayList<Position>();
            ArrayList<Position> whitePiecesGoodMoves = new ArrayList<Position>();
            for(int i = 0; i < whiteKingMoves.size(); i++)
            {
                if (game.checkCheck(whiteKingMoves.get(i)))
                    whiteKingBadMoves.add(whiteKingMoves.get(i));
            }
            for(Position pos : board.whitePossibleMoves())
            {
                ChessPiece p = board.put(pos, new Pawn(pos, "white", board));
                if (!game.checkCheck())
                {
                    whitePiecesGoodMoves.add(pos);
                }
                board.remove(pos);
                board.put(pos, p);
            }
            board.removeAllPositions(whitePiecesGoodMoves);
            game.whiteKingSetMoves();
            for (Position pos : whiteKingBadMoves)
            {
                game.whiteKingRemovePosition(pos);
            }
        }
        else
        {
            ArrayList<Position> blackKingMoves = (ArrayList)game.blackKingSetMoves();
            ArrayList<Position> blackKingBadMoves = new ArrayList<Position>();
            ArrayList<Position> blackPiecesGoodMoves = new ArrayList<Position>();
            for(int i = 0; i < blackKingMoves.size(); i++)
            {
                if (game.checkCheck(blackKingMoves.get(i)))
                    blackKingBadMoves.add(blackKingMoves.get(i));
            }
            for(Position pos : board.whitePossibleMoves())
            {
                ChessPiece p = board.put(pos, new Pawn(pos, "black", board));
                if (!game.checkCheck())
                {
                    blackPiecesGoodMoves.add(pos);
                }
                board.remove(pos);
                board.put(pos, p);
            }
            board.removeAllPositions(blackPiecesGoodMoves);
            System.out.println(blackPiecesGoodMoves);
            game.blackKingSetMoves();
            for (Position pos : blackKingBadMoves)
            {
                game.blackKingRemovePosition(pos);
            }
        }
        if (game.checkMate())
        {
            System.out.println("Checkmate! Good Game!");
        }
    }

    public void antiCheckProtocol()
    {
        if(game.whiteTurn())
        {
            ArrayList<Position> whiteKingMoves = (ArrayList)game.whiteKingSetMoves();
            ArrayList<Position> whiteKingBadMoves = new ArrayList<Position>();
            for(int i = 0; i < whiteKingMoves.size(); i++)
            {
                if (game.checkCheck(whiteKingMoves.get(i)))
                    whiteKingBadMoves.add(whiteKingMoves.get(i));
            }
            game.whiteKingSetMoves();
            for (Position pos : whiteKingBadMoves)
            {
                game.whiteKingRemovePosition(pos);
            }
        }
        else
        {
            ArrayList<Position> blackKingMoves = (ArrayList)game.blackKingSetMoves();
            ArrayList<Position> blackKingBadMoves = new ArrayList<Position>();
            for(int i = 0; i < blackKingMoves.size(); i++)
            {
                if (game.checkCheck(blackKingMoves.get(i)))
                    blackKingBadMoves.add(blackKingMoves.get(i));
            }
            game.blackKingSetMoves();
            for (Position pos : blackKingBadMoves)
            {
                game.blackKingRemovePosition(pos);
            }
        }
    }

    public void castleCheck()
    {
        if (game.checkLeftCastle())
        {
            if (game.whiteTurn())
            {
                game.whiteKingAddPosition(activePiece.getPosition().getLeft().getLeft());
                spots[activePiece.getPosition().getLeft().getLeft().getX()][activePiece.getPosition().getLeft().getLeft().getY()] =
                setCastleDot(spots[activePiece.getPosition().getLeft().getLeft().getX()][activePiece.getPosition().getLeft().getLeft().getY()]);
            }
            else
            {
                game.blackKingAddPosition(activePiece.getPosition().getLeft().getLeft());
                spots[activePiece.getPosition().getLeft().getLeft().getX()][activePiece.getPosition().getLeft().getLeft().getY()] =
                setCastleDot(spots[activePiece.getPosition().getLeft().getLeft().getX()][activePiece.getPosition().getLeft().getLeft().getY()]);
            }
        }
        if (game.checkRightCastle())
        {
            if (game.whiteTurn())
            {
                game.whiteKingAddPosition(activePiece.getPosition().getRight().getRight());
                spots[activePiece.getPosition().getRight().getRight().getX()][activePiece.getPosition().getRight().getRight().getY()] =
                setCastleDot(spots[activePiece.getPosition().getRight().getRight().getX()][activePiece.getPosition().getRight().getRight().getY()]);
            }
            else
            {
                game.blackKingAddPosition(activePiece.getPosition().getRight().getRight());
                spots[activePiece.getPosition().getRight().getRight().getX()][activePiece.getPosition().getRight().getRight().getY()] =
                setCastleDot(spots[activePiece.getPosition().getRight().getRight().getX()][activePiece.getPosition().getRight().getRight().getY()]);
            }
        }
    }

    public JPanel setCastleDot(JPanel panel)
    {
        ImageIcon img = new ImageIcon("Move Dot.png");
        JButton pic = new JButton(img);
        pic.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    castle(e);
                }
            });
        pic.setOpaque(false);
        pic.setContentAreaFilled(false);
        pic.setBorderPainted(false);
        panel.add(pic, 0);
        return panel;
    }

    public void castle(ActionEvent e)
    {
        String log = "";
        JPanel current = (JPanel)((JButton)e.getSource()).getParent();
        int x = 0;
        int y = 0;
        boolean found = false;
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (current == spots[i][j])
                {
                    x = i;
                    y = j;
                    found = true;
                    break;
                }
            }
        }
        if (!found)
        {
            return;
        }
        ChessPiece rook;
        boolean left;
        if (x == 0)
        {
            if (y == 2)
            {
                rook = board.get(new Position(0, 0));
                left = true;
            }
            else
            {
                rook = board.get(new Position(0, 7));
                left = false;
            }
        }
        else
        {
            if (y == 2)
            {
                rook = board.get(new Position(7, 0));
                left = true;
            }
            else
            {
                rook = board.get(new Position(7, 7));
                left = false;
            }
        }
        for(Position p : activePiece.getPossibleMoves())
        {
            spots[p.getX()][p.getY()] = removeDot(spots[p.getX()][p.getY()]);
        }
        if (!board.isPositionNull(new Position(x, y)))
        {
            spots[x][y] = removeChessPiece(spots[x][y]);
            board.remove(new Position(x, y));
        }
        Position activePos = activePiece.getPosition();
        spots[x][y] = setImage(spots[x][y], activePiece.getImgFile());
        spots[activePos.getX()][activePos.getY()] = removeChessPiece(spots[activePos.getX()][activePos.getY()]);
        board.remove(activePos);
        board.put(new Position(x, y), activePiece);
        activePiece.setPosition(new Position(x, y));
        activePiece.hasMoved();
        activePiece = rook;
        activePos = activePiece.getPosition();
        Position movePosition;
        if (left)
        {
            movePosition = new Position(x, y + 1);
            spots[x][y] = removeDot(spots[x][y]);
            log += "o-o-o";
        }
        else
        {
            movePosition = new Position(x, y - 1);
            log += "o-o";
        }
        spots[movePosition.getX()][movePosition.getY()] = setImage(spots[movePosition.getX()][movePosition.getY()], activePiece.getImgFile());
        spots[activePos.getX()][activePos.getY()] = removeChessPiece(spots[activePos.getX()][activePos.getY()]);
        board.remove(activePos);
        board.put(movePosition, activePiece);
        activePiece.setPosition(movePosition);
        activePiece.hasMoved();
        activePiece = null;
        board.reset();
        game.switchTurns();
        if(game.checkCheck())
        {
            //checkProtocol();
        }
        else
        {
            //antiCheckProtocol();
        }
        frame.revalidate();
        frame.repaint();
        if (game.whiteTurn())
        {
            System.out.print(log + "\n");
        }
        else
        {
            System.out.print(log + "\t\t\t");
        }
    }

    public void showEnpasan()
    {
        if(game.whiteTurn())
        {
            activePiece.addPosition(enpasanPiece.getPosition().getTop());
            spots[enpasanPiece.getPosition().getTop().getX()][enpasanPiece.getPosition().getTop().getY()] = setEnpasanDot(spots[enpasanPiece.getPosition().getTop().getX()][enpasanPiece.getPosition().getTop().getY()]);
        }
        else
        {
            activePiece.addPosition(enpasanPiece.getPosition().getBottom());
            spots[enpasanPiece.getPosition().getBottom().getX()][enpasanPiece.getPosition().getBottom().getY()] = setEnpasanDot(spots[enpasanPiece.getPosition().getBottom().getX()][enpasanPiece.getPosition().getBottom().getY()]);
        }
    }

    public JPanel setEnpasanDot(JPanel panel)
    {
        ImageIcon img = new ImageIcon("Move Dot.png");
        JButton pic = new JButton(img);
        pic.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    enpasan(e);
                }
            });
        pic.setOpaque(false);
        pic.setContentAreaFilled(false);
        pic.setBorderPainted(false);
        panel.add(pic, 0);
        return panel;
    }

    public void enpasan(ActionEvent e)
    {
        String log = "";
        JPanel current = (JPanel)((JButton)e.getSource()).getParent();
        int x = 0;
        int y = 0;
        boolean found = false;
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (current == spots[i][j])
                {
                    x = i;
                    y = j;
                    found = true;
                    break;
                }
            }
        }
        if (!found)
        {
            return;
        }
        for(Position p : activePiece.getPossibleMoves())
        {
            spots[p.getX()][p.getY()] = removeDot(spots[p.getX()][p.getY()]);
        }
        Position activePos = activePiece.getPosition();
        log += ALPHABET.substring(activePos.getY(), activePos.getY() + 1) + "x" + ALPHABET.substring(y, y+1) + (8-x) + "(ep)";
        if (game.whiteTurn())
        {
            spots[x + 1][y] = removeChessPiece(spots[x + 1][y]);
            board.remove(new Position(x + 1, y));
        }
        else
        {
            spots[x - 1][y] = removeChessPiece(spots[x - 1][y]);
            board.remove(new Position(x - 1, y));
        }
        spots[x][y] = setImage(spots[x][y], activePiece.getImgFile());
        spots[activePos.getX()][activePos.getY()] = removeChessPiece(spots[activePos.getX()][activePos.getY()]);
        board.remove(activePos);
        board.put(new Position(x, y), activePiece);
        activePiece.setPosition(new Position(x, y));
        activePiece.hasMoved();
        board.reset();
        game.switchTurns();
        activePiece = null;
        if(game.checkCheck())
        {
            //checkProtocol();
        }
        else
        {
            //antiCheckProtocol();
        }
        frame.revalidate();
        frame.repaint();
        if (game.whiteTurn())
        {
            System.out.print(log + "\n");
        }
        else
        {
            System.out.print(log + "\t\t\t");
        }
    }

    public void whitePromoteScreen(Position p)
    {
        boolean n = false, b = false, r = false, q = false;
        JFrame promotionFrame = new JFrame();
        promotionFrame.setTitle("Promotion");
        Container pane = promotionFrame.getContentPane();
        pane.setLayout(new GridLayout(1, 4));

        JPanel knightPanel = new JPanel();
        knightPanel.setBackground(new Color(132, 67, 2));
        JButton knight = new JButton(new ImageIcon(WHITE_KNIGHT));
        knight.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        knight.setOpaque(false);
        knight.setContentAreaFilled(false);
        knight.setBorderPainted(false);
        knightPanel.add(knight);
        pane.add(knightPanel);

        JPanel bishopPanel = new JPanel();
        bishopPanel.setBackground(new Color(132, 67, 2));
        JButton bishop = new JButton(new ImageIcon(WHITE_BISHOP));
        bishop.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        bishop.setOpaque(false);
        bishop.setContentAreaFilled(false);
        bishop.setBorderPainted(false);
        bishopPanel.add(bishop);
        pane.add(bishopPanel);

        JPanel rookPanel = new JPanel();
        rookPanel.setBackground(new Color(132, 67, 2));
        JButton rook = new JButton(new ImageIcon(WHITE_ROOK));
        rook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        rook.setOpaque(false);
        rook.setContentAreaFilled(false);
        rook.setBorderPainted(false);
        rookPanel.add(rook);
        pane.add(rookPanel);

        JPanel queenPanel = new JPanel();
        queenPanel.setBackground(new Color(132, 67, 2));
        JButton queen = new JButton(new ImageIcon(WHITE_QUEEN));
        queen.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        queen.setOpaque(false);
        queen.setContentAreaFilled(false);
        queen.setBorderPainted(false);
        queenPanel.add(queen);
        pane.add(queenPanel);
        promotionFrame.pack();
        promotionFrame.setLocationRelativeTo(null);
        promotionFrame.setVisible(true);
    }

    public void blackPromoteScreen(Position p)
    {
        boolean n = false, b = false, r = false, q = false;
        JFrame promotionFrame = new JFrame();
        promotionFrame.setTitle("Promotion");
        Container pane = promotionFrame.getContentPane();
        pane.setLayout(new GridLayout(1, 4));

        JPanel knightPanel = new JPanel();
        knightPanel.setBackground(new Color(132, 67, 2));
        JButton knight = new JButton(new ImageIcon(BLACK_KNIGHT));
        knight.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        knight.setOpaque(false);
        knight.setContentAreaFilled(false);
        knight.setBorderPainted(false);
        knightPanel.add(knight);
        pane.add(knightPanel);

        JPanel bishopPanel = new JPanel();
        bishopPanel.setBackground(new Color(132, 67, 2));
        JButton bishop = new JButton(new ImageIcon(BLACK_BISHOP));
        bishop.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        bishop.setOpaque(false);
        bishop.setContentAreaFilled(false);
        bishop.setBorderPainted(false);
        bishopPanel.add(bishop);
        pane.add(bishopPanel);

        JPanel rookPanel = new JPanel();
        rookPanel.setBackground(new Color(132, 67, 2));
        JButton rook = new JButton(new ImageIcon(BLACK_ROOK));
        rook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        rook.setOpaque(false);
        rook.setContentAreaFilled(false);
        rook.setBorderPainted(false);
        rookPanel.add(rook);
        pane.add(rookPanel);

        JPanel queenPanel = new JPanel();
        queenPanel.setBackground(new Color(132, 67, 2));
        JButton queen = new JButton(new ImageIcon(BLACK_QUEEN));
        queen.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    promote(e, p);
                    promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        queen.setOpaque(false);
        queen.setContentAreaFilled(false);
        queen.setBorderPainted(false);
        queenPanel.add(queen);
        pane.add(queenPanel);
        promotionFrame.pack();
        promotionFrame.setLocationRelativeTo(null);
        promotionFrame.setVisible(true);
    }

    public void promote(ActionEvent e, Position p)
    {
        String file = ((ImageIcon)((JButton)e.getSource()).getIcon()).getDescription();
        board.remove(p);
        if (file.equals(WHITE_KNIGHT))
            board.put(p, new Knight(p, "white", board));
        if (file.equals(WHITE_BISHOP))
            board.put(p, new Bishop(p, "white", board));
        if (file.equals(WHITE_ROOK))
            board.put(p, new Rook(p, "white", board));
        if (file.equals(WHITE_QUEEN))
            board.put(p, new Queen(p, "white", board));
        if (file.equals(BLACK_KNIGHT))
            board.put(p, new Knight(p, "black", board));
        if (file.equals(BLACK_BISHOP))
            board.put(p, new Bishop(p, "black", board));
        if (file.equals(BLACK_ROOK))
            board.put(p, new Rook(p, "black", board));
        if (file.equals(BLACK_QUEEN))
            board.put(p, new Queen(p, "black", board));
    }
}