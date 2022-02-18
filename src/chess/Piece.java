package chess;

public abstract class Piece {
    private int x;
    private int y;
    final private boolean is_white;
    private String file_location;
    public BoardGui board;
    
    public Piece(int x, int y, boolean is_white, String file_location, BoardGui board)
    {
        this.is_white = is_white;
        this.x = x;
        this.y = y;
        this.file_location = file_location;
        this.board = board;
    }
    
    public String getFilePath()
    {
        return file_location;
    }
    
    public void setFilePath(String location)
    {
        this.file_location = location;
    }
    
    public boolean isWhite()
    {
        return is_white;
    }
    
    public boolean isBlack()
    {
        return !is_white;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean canMove(int des_x, int dest_y)
    {
        return false;
    }
}
