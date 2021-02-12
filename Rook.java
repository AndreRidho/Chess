package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class Rook extends PieceMoves{
	
	public Rook(boolean color){
		
		type = 'r';
		
		this.color = color;
		
		if(color) sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\RookWhite.png");
		else sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\RookBlack.png");
		
		this.setFitDimensions();
		
	}
	
	@Override
	public ArrayList<String> showMoves(String[][] board, HashMap<Piece, Integer> pieces) {
		
		int x = this.getX();
		int y = this.getY();
		
		moves = new ArrayList<String>();
		
		horiRight(x, y, board, pieces);
		horiLeft(x, y, board, pieces);
		vertUp(x, y, board, pieces);
		vertDown(x, y, board, pieces);
		
		zeroCheck(moves);
		
		return moves;
		
	}

}
