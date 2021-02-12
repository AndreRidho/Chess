package chess;

public abstract class Pawn extends Piece{
	
	//Pawn's way forward is blocked by another Piece
	protected boolean blockForward = false;
	
	//Pawn is facing the edge of the board
	protected boolean edgeForward  = false;
	
	//Pawn's right is the edge of the board
	protected boolean edgeRight = false;
	
	//Pawn's left is the edge of the board
	protected boolean edgeLeft = false;

	public Pawn(){
		type = 'p';
	}
	
	protected void checkLeftRight(String[][] board) {
		
		try {
			String a = board[x+1][y];
		}catch(ArrayIndexOutOfBoundsException e) {
			edgeRight = true;
		}
		
		try {
			String a = board[x-1][y];
		}catch(ArrayIndexOutOfBoundsException e) {
			edgeLeft = true;
		}
		
	}

}


