package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Piece implements Cloneable{
	
	ImageView sprite;
	boolean color;
	int x;
	int y;
	char type;
	int coordinate;
	ArrayList<String> moves;
	char id;
	static char takenID=64;
	
	public Piece() {		
		id = ++takenID;		
	}
	
	protected void setFitDimensions() {
		sprite.setFitHeight(90);
		sprite.setFitWidth(90);
	}
	
	@Override
	public String toString() {

		if (this instanceof WhitePawn) {
			return "White Pawn" + id;
		}else if (this instanceof BlackPawn) {
			return "Black Pawn" + id;
		}else if (this instanceof Rook) {
			return "Rook" + id;
		}else if (this instanceof Bishop) {
			return "Bishop" + id;
		}else if (this instanceof Knight) {
			return "Knight" + id;
		}else if (this instanceof Queen) {
			return "Queen" + id;
		}else if (this instanceof King) {
			return "King" + id;
		}else {
			System.exit(0);
			return null;
		}
		
	}
	
	protected char isType() {		
		return type;		
	}
	
	protected void setPosition(int x, int y, String[][] board, HashMap<Piece, Integer> pieces) {
		
		//Killing another piece
		if (board[x][y] != ".") {
			
			Piece removePiece = null;
			
			for(Entry<Piece, Integer> entry: pieces.entrySet()) {
				if (entry.getValue() == x*10+y) {
					//entry.getKey().sprite.setImage(null);
					
					removePiece = entry.getKey();
				}
			}
			
			pieces.remove(removePiece);
			
		}
		
		//Replacing the square the piece was in before it moved with ".", which means empty
		board[this.x][this.y] = ".";
		
		board[x][y] = this.isType() + "" + this.id;
		
		this.setX(x);
		this.setY(y);
		this.setCoordinate();
		
		//Updating the piece's position		
		pieces.replace(this, this.coordinate);
		
	}

	public void setCoordinate() {
		this.coordinate = x*10 + y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	//Looks for available moves for this piece. Is differently overridden in every type of piece
	protected ArrayList<String> showMoves(String[][] board, HashMap<Piece, Integer> pieces) {
		
		return null;
		
	}
	
	protected void displayTargets(Pane pane, String[][] board, HashMap<Piece, Integer> pieces) {
		
		Game.moves = this.showMoves(board, pieces);
		Game.activePiece = this;
		
		//Preventing suicide moves and implementing checks, checkmates
		for(Node n: Game.currentNode.children) {
			for(Node o: n.children) {
				if(o.kingsDead()) {
					
					Game.moves.remove(n.previousMove);
					
				}
			}
		}
		
		
		
		for(String s: Game.moves){
			
			//Load the target png and put it wherever there is a valid move
			int i = Integer.parseInt(s.substring(1));
			ImageView target = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\target.png");
			pane.getChildren().add(target);
			target.setFitHeight(90);
			target.setFitWidth(90);
			
			int x = (i/10)*90;
			int y = (i%10)*90;
			
			target.setX(x);
			target.setY(y);
			
			Game.targets.add(target);
			
		}
		
	}
	
	//Checking if another piece is the same color
	protected boolean checkIfAlly(Piece piece) {
		
		if(this.color == piece.color) return true;
		else return false;
		
	}
	
	//Checking if another piece is the same color, given coordinates and list of all pieces
	protected boolean checkIfAlly(int x, int y, HashMap<Piece, Integer> pieces) {
		
		//Iterates through every piece, looking for the targeted piece
		for(Entry<Piece, Integer> entry: pieces.entrySet()) {
			if (entry.getValue() == x*10+y) {
				
				if(this.checkIfAlly(entry.getKey())) return true;
				else return false;
				
			}
		}
		
		return false;
		
	}
	
	//Since x and y are integers, they do not store hanging zeros. This method is to handle this
	protected void zeroCheck(ArrayList<String> moves) {
		
		for(String m: moves) {
			char id = m.charAt(0);
			int index = moves.indexOf(m);
			int coordinate = Integer.parseInt(m.substring(1));
			if(coordinate == 0) moves.set(index, id + "00");
			else if(coordinate < 10) moves.set(index, id + "0" + coordinate);
		}
		
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}


