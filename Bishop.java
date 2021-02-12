package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class Bishop extends PieceMoves{
	
	public Bishop(boolean color){
		
		type = 'b';
		
		this.color = color;
		
		if(color) sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\BishopWhite.png");
		else sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\BishopBlack.png");
		
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
		
		zeroCheck(moves);
		
		return moves;
		
	}

}
