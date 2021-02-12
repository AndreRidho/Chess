package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class Queen extends PieceMoves{
	
	public Queen(boolean color){
		
		type = 'q';
		
		this.color = color;
		
		if(color) sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\QueenWhite.png");
		else sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\QueenBlack.png");
		
		this.setFitDimensions();
		
	}
	
	@Override
	public ArrayList<String> showMoves(String[][] board, HashMap<Piece, Integer> pieces) {
		
		int x = this.getX();
		int y = this.getY();
		
		moves = new ArrayList<String>();
		
		diagPosUp(x, y, board, pieces);
		diagPosDown(x, y, board, pieces);
		diagNegUp(x, y, board, pieces);
		diagNegDown(x, y, board, pieces);
		horiRight(x, y, board, pieces);
		horiLeft(x, y, board, pieces);
		vertUp(x, y, board, pieces);
		vertDown(x, y, board, pieces);
		
		zeroCheck(moves);
		
		return moves;
		
	}
	
}
