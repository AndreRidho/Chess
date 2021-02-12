package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class King extends Piece {

	public King(boolean color) {

		type = 'k';

		this.color = color;

		if (color)
			sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\KingWhite.png");
		else
			sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\KingBlack.png");

		this.setFitDimensions();

	}

	@Override
	public ArrayList<String> showMoves(String[][] board, HashMap<Piece, Integer> pieces) {

		int x = this.getX();
		int y = this.getY();

		moves = new ArrayList<String>();

		if (checkIfAlly(x + 1, y, pieces)) {

		} else {
			if(x+1<8 && y<8 && x+1>=0 && y>=0) moves.add(this.id + "" + ((x + 1) * 10 + (y)));
		}

		if (checkIfAlly(x + 1, y + 1, pieces)) {

		} else {
			if(x+1<8 && y+1<8 && x+1>=0 && y+1>=0) moves.add(this.id + "" + ((x + 1) * 10 + (y + 1)));
		}

		if (checkIfAlly(x, y + 1, pieces)) {

		} else {
			if(x<8 && y+1<8 && x>=0 && y+1>=0) moves.add(this.id + "" + ((x) * 10 + (y + 1)));
		}

		if (checkIfAlly(x - 1, y + 1, pieces)) {

		} else {
			if(x-1<8 && y+1<8 && x-1>=0 && y+1>=0) moves.add(this.id + "" + ((x - 1) * 10 + (y + 1)));
		}

		if (checkIfAlly(x - 1, y, pieces)) {

		} else {
			if(x-1<8 && y<8 && x-1>=0 && y>=0) moves.add(this.id + "" + ((x - 1) * 10 + (y)));
		}

		if (checkIfAlly(x - 1, y - 1, pieces)) {

		} else {
			if(x-1<8 && y-1<8 && x-1>=0 && y-1>=0) moves.add(this.id + "" + ((x - 1) * 10 + (y - 1)));
		}

		if (checkIfAlly(x, y - 1, pieces)) {

		} else {
			if(x<8 && y-1<8 && x>=0 && y-1>=0) moves.add(this.id + "" + ((x) * 10 + (y - 1)));
		}

		if (checkIfAlly(x + 1, y - 1, pieces)) {

		} else {
			if(x+1<8 && y-1<8 && x+1>=0 && y-1>=0) moves.add(this.id + "" + ((x + 1) * 10 + (y - 1)));
		}
		
		zeroCheck(moves);

		return moves;

	}

}
