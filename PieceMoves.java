package chess;

import java.util.HashMap;

/*
 * PieceMoves class shows moves for Queen, Rook, and Bishop classes
 */
public abstract class PieceMoves extends Piece{
	
	//Positive slope diagonal - Up
	protected void diagPosUp(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square right and one up
		x++;
		y--;
		
		try {
			
			if(board[x][y] == ".") {
				
				//Add square to moves and recur
				moves.add(this.id + "" + (x*10 + y));
				diagPosUp(x, y, board, pieces);			
				
			}else {
				
				//Check if it is ally or enemy
				//If ally, don't add square. If enemy, add square
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}
				
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	//Positive slope diagonal - Down
	protected void diagPosDown(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square left and one down
		x--;
		y++;
		
		try {			
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				diagPosDown(x, y, board, pieces);
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}				
			}			
		}catch(ArrayIndexOutOfBoundsException e) {	
			
		}
		
	}
	
	//Negative slope diagonal - Up
	protected void diagNegUp(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square left and one up
		x--;
		y--;
		
		try {
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				diagNegUp(x, y, board, pieces);
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	//Negative slope diagonal - Down
	protected void diagNegDown(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square right and one down
		x++;
		y++;
		
		try {			
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				diagNegDown(x, y, board, pieces);
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}				
			}			
		}catch(ArrayIndexOutOfBoundsException e) {	
			
		}
		
	}
	
	//Horizontal - Right
	protected void horiRight(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square right
		x++;
		
		try {
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				horiRight(x, y, board, pieces);			
				
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	//Horizontal - Left
	protected void horiLeft(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square left
		x--;
		
		try {
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				horiLeft(x, y, board, pieces);			
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	//Vertical - Up
	protected void vertUp(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square up
		y--;
		
		try {
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				vertUp(x, y, board, pieces);
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	//Vertical - Down
	protected void vertDown(int x, int y, String[][] board, HashMap<Piece, Integer> pieces){
		
		//One square down
		y++;
		
		try {
			if(board[x][y] == ".") {
				moves.add(this.id + "" + (x*10 + y));
				vertDown(x, y, board, pieces);
			}else {
				if(!this.checkIfAlly(x, y, pieces)) {
					moves.add(this.id + "" + (x*10 + y));
				}
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}

}
