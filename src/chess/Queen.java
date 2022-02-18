/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

public class Queen extends Piece {

    public Queen(int x, int y, boolean is_white, String file_location, BoardGui board)
    {
        super(x,y,is_white,file_location, board);
    }
    
    public boolean hv(int dest_x, int dest_y){
        
        int selectedx = this.getX();
    	int selectedy = this.getY();
    	
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
        
        if(selectedx != dest_x && selectedy != dest_y){
            return false;
        }
        
        if(dest_y > selectedy) {
            for(int i = 1; i < lasty; i++) {
                Piece nPiece = board.getPiece(selectedx, selectedy + i);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        if(dest_y < selectedy) {
            for(int i = 1; i < lasty; i++) {
                Piece nPiece = board.getPiece(selectedx, selectedy - i);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        if(dest_x > selectedx) {
            for(int i = 1; i < lastx; i++) {
                Piece nPiece = board.getPiece(selectedx + i, selectedy);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        if(dest_x < selectedx) {
            for(int i = 1; i < lastx; i++) {
                Piece nPiece = board.getPiece(selectedx - i, selectedy);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        
        return true;
    }
    
    public boolean diagonal(int dest_x, int dest_y){
    	int selectedx = this.getX();
    	int selectedy = this.getY();
    	
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
        
        if(lastx != lasty) {
            return false;
        }
        
        if(dest_y > selectedy && dest_x < selectedx) {
            for(int i = 1; i < lastx; i++) {
                Piece nPiece = board.getPiece(selectedx - i, selectedy + i);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        if(dest_y < selectedy && dest_x < selectedx) {
            for(int i = 1; i < lastx; i++) {
                Piece nPiece = board.getPiece(selectedx - i, selectedy - i);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        if(dest_y > selectedy && dest_x > selectedx) {
            for(int i = 1; i < lastx; i++) {
                Piece nPiece = board.getPiece(selectedx + i, selectedy + i);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        if(dest_y < selectedy && dest_x > selectedx) {
            for(int i = 1; i < lastx; i++) {
                Piece nPiece = board.getPiece(selectedx + i, selectedy - i);
                if( nPiece != null) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public boolean canMove(int dest_x, int dest_y)
    {
        return this.hv(dest_x, dest_y) || this.diagonal(dest_x, dest_y);
    }
}
