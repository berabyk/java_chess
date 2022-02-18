package chess;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class BoardGui extends JComponent {
        
    public int turnCounter = 0;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
    
    private final int square_Size = 60;
    public ArrayList<Piece> White;
    public ArrayList<Piece> Black;
    
    public ArrayList<DrawingShape> Static_Shapes;
    public ArrayList<DrawingShape> piece_images;
    public Piece Active_Piece;
    private final int y = 8;
    private final int x = 8;
    private Integer[][] chess_Squares;
    private String board_location = "chessimages\\chessboard.png";
    private String active_square_file_path = "chessimages\\selected_piece.png";
    //chessimages\\chessboard.png
    public void chessBoard(){    
        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                chess_Squares[i][j] = 0;
            }
        }
        
        //(int x, int y, boolean is_white, String file_location, BoardGui board)
        White.add(new King(3,0,true,"wking.png",this));
        White.add(new Queen(4,0,true,"wqueen.png",this));
        White.add(new Bishop(2,0,true,"wbishop.png",this));
        White.add(new Bishop(5,0,true,"wbishop.png",this));
        White.add(new Knight(1,0,true,"wknight.png",this));
        White.add(new Knight(6,0,true,"wknight.png",this));
        White.add(new Rook(0,0,true,"wrook.png",this));
        White.add(new Rook(7,0,true,"wrook.png",this));
        for(int i = 0; i < 8; i++){
            White.add(new Pawn(i,1,true,"wpawn.png",this));
        }
        Black.add(new King(3,7,false,"bking.png",this));
        Black.add(new Queen(4,7,false,"bqueen.png",this));
        Black.add(new Bishop(2,7,false,"bbishop.png",this));
        Black.add(new Bishop(5,7,false,"bbishop.png",this));
        Black.add(new Knight(1,7,false,"bknight.png",this));
        Black.add(new Knight(6,7,false,"bknight.png",this));
        Black.add(new Rook(0,7,false,"brook.png",this));
        Black.add(new Rook(7,7,false,"brook.png",this));
        for(int i = 0; i < 8; i++){
            Black.add(new Pawn(i,6,false,"bpawn.png",this));
        }
    }

    public BoardGui() {
        this.mouseAdapter = new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                int dest_X = e.getX();
                int dest_Y = e.getY();
                int clicked_Y = dest_Y / square_Size;
                int clicked_X = dest_X / square_Size;
                boolean is_whites_turn = true;
                if (turnCounter%2 == 1)
                {
                    is_whites_turn = false;
                }
                
                Piece clicked_piece = getPiece(clicked_X, clicked_Y);
                
                if (clicked_piece != null && ((is_whites_turn && clicked_piece.isWhite()) || (!is_whites_turn && clicked_piece.isBlack()))&& Active_Piece == null){
                    Active_Piece = clicked_piece;
                }
                else if (Active_Piece != null && Active_Piece.getX() == clicked_X && Active_Piece.getY() == clicked_Y){
                    Active_Piece = null;
                }
                else if (((is_whites_turn && Active_Piece.isWhite()) || (!is_whites_turn && Active_Piece.isBlack()))&& Active_Piece != null && Active_Piece.canMove(clicked_X, clicked_Y))
                {
                    if (clicked_piece == White.get(0) || clicked_piece == Black.get(0)){ //get(0) is Kings.
                        JFrame f;
                        f = new JFrame();
                        int a;
                        if (clicked_piece.isWhite()){
                            White.remove(clicked_piece);
                            JOptionPane.showMessageDialog(f, "Black Wins!", "Game Over!", JOptionPane.WARNING_MESSAGE);{
                                System.exit(0);
                            }
                        }
                        else{
                            Black.remove(clicked_piece);
                            JOptionPane.showMessageDialog(f, "White Wins!", "Winner", JOptionPane.WARNING_MESSAGE);{
                                System.exit(0);
                            }
                        }
                    }
                    
                    else if (clicked_piece != null){
                        if (clicked_piece.isWhite()){
                            White.remove(clicked_piece);
                        }
                        else{
                            Black.remove(clicked_piece);
                        }
                    }
                    Active_Piece.setX(clicked_X);
                    Active_Piece.setY(clicked_Y);
                    
                    if (Active_Piece.getClass().equals(Pawn.class)){
                        Pawn castedPawn = (Pawn)(Active_Piece);
                        castedPawn.setHasMoved(true);
                    }
                    
                    Active_Piece = null;
                    turnCounter++;
                    
                }
                drawBoard();
                
            }
            
        };

        chess_Squares = new Integer[y][x];
        Static_Shapes = new ArrayList();
        piece_images = new ArrayList();
        White = new ArrayList();
        Black = new ArrayList();

        chessBoard();

        this.setPreferredSize(new Dimension(480, 480));
        this.addMouseListener(mouseAdapter);
        this.setVisible(true);
        drawBoard();
    }


    private void drawBoard()
    {
        piece_images.clear();
        Static_Shapes.clear();
        
        Image board = loadImage(board_location);
        Static_Shapes.add(new DrawingImage(board, new Rectangle2D.Double(0, 0, board.getWidth(null), board.getHeight(null))));
        if (Active_Piece != null)
        {
            Image active_square = loadImage("chessimages\\selected_piece.png");
            Static_Shapes.add(new DrawingImage(active_square, new Rectangle2D.Double(square_Size*Active_Piece.getX(),square_Size*Active_Piece.getY(), active_square.getWidth(null), active_square.getHeight(null))));
        }
        for (int i = 0; i < White.size(); i++)
        {
            int y_axis = White.get(i).getX();
            int x_axis = White.get(i).getY();
            Image piece = loadImage("chessimages\\" + White.get(i).getFilePath());  
            piece_images.add(new DrawingImage(piece, new Rectangle2D.Double(square_Size*y_axis,square_Size*x_axis, piece.getWidth(null), piece.getHeight(null))));
        }
        for (int i = 0; i < Black.size(); i++)
        {
            int x_axis = Black.get(i).getX();
            int y_axis = Black.get(i).getY();
            Image piece = loadImage("chessimages\\" + Black.get(i).getFilePath());  
            piece_images.add(new DrawingImage(piece, new Rectangle2D.Double(square_Size*x_axis,square_Size*y_axis, piece.getWidth(null), piece.getHeight(null))));
        }
        this.repaint();
    }

    
    public Piece getPiece(int x, int y) {
        for (Piece p : White)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        for (Piece p : Black)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        return null;
    }

    private MouseAdapter mouseAdapter;

    private void adjustShapePositions(double x, double y) {
        Static_Shapes.get(0).adjustPosition(x, y);
        this.repaint();
    } 
        
    private Image loadImage(String imageFile) {
        try {
            return ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
            return NULL_IMAGE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //drawBackground(g2);
        drawShapes(g2);
    }

    private void drawShapes(Graphics2D g2) {
        Static_Shapes.forEach(shape -> {shape.draw(g2);});	
        piece_images.forEach(shape -> {shape.draw(g2);});
    }

}


