package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class WhitePawn extends Pawn {
	
	private static boolean side;

	public WhitePawn(boolean color) {
		
		this.color = color;
		sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\PawnWhite.png");
		this.setFitDimensions();
		
	}

	public static boolean isSide() {
		return side;
	}

	public static void setSide(boolean side) {
		WhitePawn.side = side;
	}
	
	@Override
	public ArrayList<String> showMoves(String[][] board, HashMap<Piece, Integer> pieces) {
		
		int x = this.getX();
		int y = this.getY();
		
		moves = new ArrayList<String>();
		
		checkLeftRight(board);
		
		//If White is bottomside
		if(side) {
			
			//If one spot in front of pawn is empty
			try {
				if(board[x][y - 1] == ".") {
					moves.add(this.id + "" + (x*10 + (y-1)));
				}else blockForward = true;
			}catch(Exception e) {
				edgeForward = true;
			}
			
			//If pawn can kill:
			//If pawn is facing the edge of the board there is no way to kill (becomes Queen - will make that later)
			if(!edgeForward) {
				
				//Checks if the square (that is one square to the right and one square below) is occupied
				if(!edgeRight && board[x+1][y-1] != ".") {
					
					//Add targeted occupied square to available moves if it has an enemy
					if(!this.checkIfAlly(x+1, y-1, pieces)) moves.add(this.id + "" + ((x+1)*10+(y-1)));
				}
				if(!edgeLeft && board[x-1][y-1] != ".") {
					if(!this.checkIfAlly(x-1, y-1, pieces)) moves.add(this.id + "" + ((x-1)*10+(y-1)));				
				}
			}
			
			//If pawn is in starting position
			if (this.getY() == 6 && !blockForward) {
				if(board[x][y - 2] == ".") {
					moves.add(this.id + "" + (x*10 + (y-2)));
				}
			}
			
		//If white is topside - exact same code with changes to x and y
		}else {
			
			try {
				if(board[x][y + 1] == ".") {
					moves.add(this.id + "" + (x*10 + (y+1)));
				}else blockForward = true;
			}catch(Exception e) {
				edgeForward = true;
			}
			
			if(!edgeForward) {
				if(!edgeRight && board[x+1][y+1] != ".") {
					if(!this.checkIfAlly(x+1, y+1, pieces)) moves.add(this.id + "" + ((x+1)*10+(y+1)));
				}
				if(!edgeLeft && board[x-1][y+1] != ".") {
					if(!this.checkIfAlly(x-1, y+1, pieces)) moves.add(this.id + "" + ((x-1)*10+(y+1)));				
				}
			}
			
			if (this.getY() == 1 && !blockForward) {
				if(board[x][y + 2] == ".") {
					moves.add(this.id + "" + (x*10 + (y+2)));
				}
			}
			
		}
		
		zeroCheck(moves);
		
		return moves;
		
	}

}


