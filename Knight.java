package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;

public class Knight extends Piece{
	
	public Knight(boolean color){
		
		type = 'n';
		
		this.color = color;
		
		if(color) sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\KnightWhite.png");
		else sprite = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\KnightBlack.png");
		
		this.setFitDimensions();
		
	}
	
	@Override
	public ArrayList<String> showMoves(String[][] board, HashMap<Piece, Integer> pieces) {
		
		int x = this.getX();
		int y = this.getY();
		
		moves = new ArrayList<String>();
		
		if(checkIfAlly(x+1, y-2, pieces)) {
			
		}else {
			if(x+1<8 && y-2<8 && x+1>=0 && y-2>=0) {
				
				moves.add(this.id + "" + ((x+1)*10 + (y-2)));
			}
		}
		
		if(checkIfAlly(x+2, y-1, pieces)) {
			
		}else {
			if(x+2<8 && y-1<8 && x+2>=0 && y-1>=0) moves.add(this.id + "" + ((x+2)*10 + (y-1)));
		}
		
		if(checkIfAlly(x+2, y+1, pieces)) {
			
		}else {
			if(x+2<8 && y+1<8 && x+2>=0 && y+1>=0) moves.add(this.id + "" + ((x+2)*10 + (y+1)));
		}
		
		if(checkIfAlly(x+1, y+2, pieces)) {
			
		}else {
			if(x+1<8 && y+2<8 && x+1>=0 && y+2>=0) moves.add(this.id + "" + ((x+1)*10 + (y+2)));
		}
		
		if(checkIfAlly(x-1, y+2, pieces)) {
			
		}else {
			if(x-1<8 && y+2<8 && x-1>=0 && y+2>=0) moves.add(this.id + "" + ((x-1)*10 + (y+2)));
		}
		
		if(checkIfAlly(x-2, y+1, pieces)) {
			
		}else {
			if(x-2<8 && y+1<8 && x-2>=0 && y+1>=0) moves.add(this.id + "" + ((x-2)*10 + (y+1)));
		}
		
		if(checkIfAlly(x-2, y-1, pieces)) {
			
		}else {
			if(x-2<8 && y-1<8 && x-2>=0 && y-1>=0) moves.add(this.id + "" + ((x-2)*10 + (y-1)));
		}
		
		if(checkIfAlly(x-1, y-2, pieces)) {
			
		}else {
			if(x-1<8 && y-2<8 && x-1>=0 && y-2>=0) moves.add(this.id + "" + ((x-1)*10 + (y-2)));
		}
		
		zeroCheck(moves);
			
		return moves;
		
	}

}
