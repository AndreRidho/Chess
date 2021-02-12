package chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Node {
	
	/*
	 * id: ID of the Node
	 * previousMove: The move that led to this Node
	 * See Game class description for more info
	 */
	int id;
	String[][] board;
	int turnCounter;
	boolean turnColor = true;
	ArrayList<Node> children;
	HashMap<Piece, Integer> pieces;
	static int takenID;
	String previousMove;
	
	public Node() {
		
		id = takenID + 1;
		takenID++;
		
	}
	
	//Number of children
	public int getNumOfChilds() {
		
		int a=0;
		
		for(Node n: children) {
			a++;
		}
		
		return a;
	}
	
	//Number of grandchildren
	public int getNumOfGrands() {

		int a=0;
		
		for(Node n: children) {
			for(Node o: n.children) {
				a++;
			}
		}
		
		return a;
	}
	
	//Initializes all children nodes of this node and stores them in children ArrayList
	void nextLayer() throws CloneNotSupportedException{
		
		children = new ArrayList<Node>();
		
		for(Piece piece: pieces.keySet()) {
			
			if(piece.color == turnColor) {
				
				//For every move possible, create a new child Node
				for(String move: piece.showMoves(board, pieces)) {
					
					String[][] newBoard = new String[8][8];		
					HashMap<Piece, Integer> newPieces = new HashMap<Piece, Integer>();
					
					for(int i=0 ; i<8 ; i++) {
						for(int j=0 ; j<8 ; j++) {
							newBoard[j][i] = this.board[j][i];
						}
					}
					
					for(Entry<Piece, Integer> e: pieces.entrySet()) {
						Piece newPiece = (Piece)e.getKey().clone();
						newPieces.put(newPiece, e.getValue());			
					}
					
					Node newNode = new Node();
					
					char pieceID = move.charAt(0);
					int x = Integer.parseInt(move.substring(1,2));
					int y = Integer.parseInt(move.substring(2));
					
					ArrayList<Piece> toSet = new ArrayList<>();
					
					for(Piece p: newPieces.keySet()) {
						if(p.id == pieceID) {
							toSet.add(p);
						}
					}
					
					for(Piece p: toSet) {
						p.setPosition(x, y, newBoard, newPieces);
					}
					
					newNode.pieces = newPieces;
					newNode.board = newBoard;
					newNode.turnCounter = turnCounter + 1;
					newNode.turnColor = !turnColor;
					newNode.previousMove = move;
					
					children.add(newNode);
					
				}
				
			}
			
		}
		
	}
	
	//Check if any kings have been captured in this Node
	boolean kingsDead() {
		
		int kings = 0;
		
		for(Piece p: pieces.keySet()) {
			if (p.isType() == 'k') kings++;
		}
		
		return kings < 2; 
		
	}

}
