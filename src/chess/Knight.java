/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

public class Knight extends Piece {

    public Knight(int x, int y, boolean is_white, String file_location, BoardGui board)
    {
        super(x,y,is_white,file_location, board);
    }
    
    @Override
    public boolean canMove(int dest_x, int dest_y)
    {
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
        
        if(((lastx == 2 && lasty == 1)||(lasty == 2 && lastx == 1))) {
        	return true;
        }
        return false;
    }

}
