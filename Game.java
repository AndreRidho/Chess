package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application{
	
	/*
	 * board: A board of chars representing pieces
	 * moves: Available moves for the current active piece
	 * targets: An array to store the target PNG files for each valid move
	 * pieces: A HashMap of pieces as the keys and the piece's coordinates as the value
	 * activePiece: The piece currently selected by the user
	 * currentNode: The Node the current game board is currently on
	 * turnColor: Determines whose turn it is
	 */
	private static String[][] board = new String[8][8];
	static ArrayList<String> moves = new ArrayList<String>();
	static ArrayList<ImageView> targets = new ArrayList<ImageView>();
	static HashMap<Piece, Integer> pieces = new HashMap<Piece, Integer>();
	static Piece activePiece;
	static Node currentNode;
	static int turnCounter = 1;
	static boolean turnColor;
	
	Pane pane = new Pane();
	Scene scene;
	
	public static String[][] getBoard() {
		return board;
	}

	public static void setBoard(String[][] board) {
		Game.board = board;
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		for(int i=0 ; i<8 ; i++) {
			for(int j=0 ; j<8 ; j++) {
				board[i][j]=".";
			}
		}
		
		pane.setPrefSize(720, 720);
		
		//Background of chessboard
		ImageView boardPic = new ImageView("file:\\C:\\Users\\mbrid\\eclipse-workspace2\\calc3\\src\\chess\\Board.png");
		
		boardPic.setFitHeight(720);
		boardPic.setFitWidth(720);
		pane.getChildren().add(boardPic);
		
		//Randomly assigning sides
		turnColor = (Math.random() < 0.5);
		
		spawnPieces(turnColor, pane);
		
		turnColor = true;
		
		printBoard(Game.board);
		
		//Setting the root Node
		currentNode = new Node();
		currentNode.board = new String[8][8];
		
		for(int i=0 ; i<8 ; i++) {
			for(int j=0 ; j<8 ; j++) {
				currentNode.board[j][i] = Game.board[j][i];
			}
		}
		
		currentNode.turnCounter = turnCounter;
		currentNode.pieces = new HashMap<Piece, Integer>();
		
		for(Entry<Piece, Integer> e: pieces.entrySet()) {
			Piece newPiece = (Piece)e.getKey().clone();
			currentNode.pieces.put(newPiece, e.getValue());			
		}
		
		//Initializing children nodes
		currentNode.nextLayer();
		
		//Initializing grandchildren nodes
		for(Node n: currentNode.children) {
			n.nextLayer();
		}
		
		System.out.println("Number of children: " + currentNode.getNumOfChilds());
		System.out.println("Number of grandchildren: " + currentNode.getNumOfGrands());
		
		printBoard(Game.board);
		
		scene = new Scene(pane);
		
		//What happens when player clicks
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
				int x = (int)event.getX()/90;
				int y = (int)event.getY()/90;
				int coordinate = x*10 + y;
				char activePieceID = '.';
				
				
				
				ArrayList<Integer> moveCoords = new ArrayList<Integer>();
				
				for(int i=0 ; i<moves.size() ; i++) {
					moveCoords.add(Integer.parseInt(moves.get(i).substring(1)));
				}
				
				//If player clicked on highlighted square
				if (moveCoords.contains(coordinate)) {
					
					activePieceID = activePiece.id;
					
					//If a piece was captured
					for(Entry<Piece, Integer> entry: pieces.entrySet()) {
						if (entry.getValue() == x*10+y) {
							entry.getKey().sprite.setImage(null);
							System.out.println("death of " + entry.getKey().toString());
						}
					}
					
					//Moving the piece
					activePiece.setPosition(x, y, board, pieces);
					activePiece.sprite.setX(x*90);
					activePiece.sprite.setY(y*90);
					
					//Removing target images
					removeTargets();
					
					//Resetting active piece
					activePiece = null;
					
					//Increment turncounter and switch sides
					turnCounter++;
					turnColor = !turnColor;
					
					//Updating currentNode; One of the children nodes becomes currentNode
					for(Node n: currentNode.children) {
						if(n.previousMove.equals(activePieceID + "" + coordinate)) {
							currentNode = n;
						}else {
							n.children.clear();
							n = null;
						}
					}
					
					//Initializing the next generation of grandchildren nodes
					try {
						
						for(Node n: currentNode.children) {
							n.nextLayer();
						}
						
					}catch(CloneNotSupportedException e) {
						
					}
							
					System.out.println("Number of children: " + currentNode.getNumOfChilds());
					System.out.println("Number of grandchildren: " + currentNode.getNumOfGrands());
					
					printBoard(board);
					
				}else {
					
					//If player clicked on a piece
					if(board[x][y] != ".") {
						
						try {
						
							//If player clicked on a piece other than activePiece
							if (activePiece.coordinate != coordinate) {
								
								removeTargets();
								selectNewPiece(coordinate, pane);
								
							}
						
						//If there is no active Piece (activePiece is null)
						}catch(NullPointerException e) {
							
							selectNewPiece(coordinate, pane);
							
						}
						
					}else {
						activePiece = null;
						removeTargets();
					}
					
				}
				
			}
		});
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	//Removing target images
	static void removeTargets() {
		for(ImageView t: targets) {
			t.setImage(null);
		}
		targets.clear();
		moves.clear();
	}
	
	static void selectNewPiece(int coordinate, Pane pane) {
		for (Entry<Piece, Integer> entry : pieces.entrySet()) {
	        if (entry.getValue().equals(coordinate) && entry.getKey().color == turnColor) {
	        	entry.getKey().displayTargets(pane, board, pieces);
	        	if(!targets.isEmpty()) activePiece = entry.getKey();
	        	
	        }
	    }
	}
	
	static void spawnPieces(boolean color, Pane pane) {

		//To account for first rook with coordinate 0,0 which causes some errors
		boolean q = true;
		Piece removeRook = null;
		
		for(int i=0 ; i<8 ; i++) {
			
			//Spawning non-pawn pieces
			Piece piece1 = null;
			Piece piece2 = null;
			
			switch(i) {
			
			case 0: case 7:
				piece1 = new Rook(color);
				piece2 = new Rook(!color);
				if(q) {
					removeRook = piece2;
					q = false;
				}
				break;
				
			case 1: case 6:
				piece1 = new Knight(color);
				piece2 = new Knight(!color);
				break;
				
			case 2: case 5:
				piece1 = new Bishop(color);
				piece2 = new Bishop(!color);
				break;
				
			case 3:
				
				if(color) {
					piece1 = new Queen(color);
					piece2 = new Queen(!color);
				}
				else {
					piece1 = new King(color);
					piece2 = new King(!color);
				}
				
				break;
				
			case 4:
				
				if(color) {
					piece1 = new King(color);
					piece2 = new King(!color);
				}
				else {
					piece1 = new Queen(color);
					piece2 = new Queen(!color);
				}
				
				break;
				
			}
			
			piece1.setPosition(i, 7, board, pieces);
			piece2.setPosition(i, 0, board, pieces);
			
			piece1.sprite.setX(i*90);
			piece1.sprite.setY(7*90);
			piece2.sprite.setX(i*90);
			piece2.sprite.setY(0*90);
			
			//Spawning pawns
			Piece pawn1 = null;
			Piece pawn2 = null;
			
			if(color) {
				pawn1 = new WhitePawn(color);
				pawn2 = new BlackPawn(!color);
				WhitePawn.setSide(true);
				BlackPawn.setSide(false);
			}
			else {
				pawn1 = new BlackPawn(color);
				pawn2 = new WhitePawn(!color);
				WhitePawn.setSide(false);
				BlackPawn.setSide(true);
			}
			
			pawn1.setPosition(i, 6, board, pieces);
			pawn2.setPosition(i, 1, board, pieces);
			
			pawn1.sprite.setX(i*90);
			pawn1.sprite.setY(6*90);
			pawn2.sprite.setX(i*90);
			pawn2.sprite.setY(1*90);
			
			pieces.put(piece1, piece1.coordinate);
			pieces.put(piece2, piece2.coordinate);
			pieces.put(pawn1, pawn1.coordinate);
			pieces.put(pawn2, pawn2.coordinate);
			pane.getChildren().addAll(piece1.sprite, piece2.sprite, pawn1.sprite, pawn2.sprite);
			
		}
		
		removeRook.setPosition(0, 0, board, pieces);
		
	}
	
	public static void printBoard(String[][] board) {
		
		for(int i=0 ; i<8 ; i++) {
			for(int j=0 ; j<8 ; j++) {
				System.out.print(board[j][i] + "\t");
			}
			System.out.println("\n\n");
		}
		System.out.println("-----------------------------------------------------------------------------");
		
	}

}
