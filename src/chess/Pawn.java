/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

public class Pawn extends Piece {

    private boolean has_moved;
    
    public Pawn(int x, int y, boolean is_white, String file_location, BoardGui board)
    {
        super(x,y,is_white,file_location, board);
        has_moved = false;
    }
    
    public void setHasMoved(boolean has_moved)
    {
        this.has_moved = has_moved;
    }
    
    public boolean getHasMoved()
    {
        return has_moved;
    }
    
    @Override
    public boolean canMove(int dest_x, int dest_y)
    {
    
        boolean hasmoved = this.getHasMoved();
    	int selectedy = this.getY();
    	int selectedx = this.getX();
    	
    	int lasty = Math.abs(dest_y - selectedy);
    	int lastx = Math.abs(dest_x - selectedx);
    	
        Piece pPiece = board.getPiece(dest_x, dest_y);
        
        if( pPiece != null) {
            if(pPiece.isWhite() && this.isWhite()) {
                return false;
            }
            else if(pPiece.isBlack() && this.isBlack()) {
                return false;
            }
        }
        
        if(lastx > 1 || lasty > 2) {
        	return false;
        }
    	if(lastx == 1 && lasty != 1) {
    		return false;
    	}
    	
        if(selectedy > dest_y) {      
            if(this.isWhite()) {
                return false;
            }
            if(pPiece == null && lastx == 1) {
                return false;
            }
            if(lastx != 1) {
                Piece nPiece = board.getPiece(selectedx, selectedy - 1);
                Piece nPiece1 = board.getPiece(selectedx, selectedy - 2);
                if(nPiece != null) {
                        return false;
                }
                if(lasty == 2 && nPiece1 != null) {
                        return false;
                }
            }
        }
        
        if(selectedy < dest_y) {
            if(this.isBlack()) {
                return false;
            }
            if(pPiece == null && lastx == 1) {
                return false;
            }
            if(lastx != 1) {
                Piece nPiece = board.getPiece(selectedx, selectedy + 1);
                Piece nPiece1 = board.getPiece(selectedx, selectedy + 2);
                if(nPiece != null) {
                        return false;
                }
                if(lasty == 2 && nPiece1 != null) {
                        return false;
                }
            }
        }
        if(has_moved && lasty > 1){
        	return false;
        }
           
        return true;
    }
}
