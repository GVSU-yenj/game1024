package game1024;

import java.lang.Math;
import java.util.*;

public class DaGame implements NumberSlider {
	Random randy = new Random();
	Cell gameCell;
	TextUI run;
	GameStatus status;
	SlideDirection direction;
	public int winCon = 1024;
	private int[][] board = new int[4][4];
	//private int row;
	//private int col;
	private int height;
	private int width;
	public ArrayList<Cell> temp = new ArrayList<Cell>();
	private Stack<int[][]> stacksOnStacks = new Stack<int[][]>();

	@Override
	public void resizeBoard(int height, int width, int winCon) {
		try {
			for (int i = 1; i < 20; i++) {
				int val = (int) Math.pow(2, i);
				if (val == winCon) {
					board = new int[height][width];
					this.winCon = winCon;
				}
			}
		} 
		catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void reset() {
		// TODO Make the "board.length" for all of the methods board.length and board[i].length !!
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = 0;
			}
		}
		placeRandomValue();
		placeRandomValue();
	}

	@Override
	public void setValues(int[][] ref) {
		// TODO Auto-generated method stub
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = ref[x][y];
			}
		}
	}

	/**
	 * 
	 * @return Cell a new cell in the board.
	 */
	//TODO: make it check for empty spots twice b4 placing.
	@Override
	public Cell placeRandomValue() {
		boolean placed = false;
		int randPlaceX = randy.nextInt(height);
		int randPlaceY = randy.nextInt(width);
		int randVal1 = randy.nextInt(8) + 1;
		int randVal2 = randy.nextInt(8) + 1;;
		int val1 = (int) Math.pow(2, randVal1);
		int val2 = (int) Math.pow(2, randVal2);
		
		while(placed = false){
			if(board[randPlaceX][randPlaceY] == null){
				board[randPlaceX][randPlaceY] = randVal1;
				placed = true;
			}
			
		}

		return gameCell;
	}

	@Override
	public boolean slide(SlideDirection dir) {
		boolean slideDir = false;
		//TODO: mod the directions accordingly. Right now they all do the same thing.
		if(dir == SlideDirection.LEFT){
			for(int x = 1; x < board.length; x++){
				for(int y = board[x].length-1; y > 0; y--){
					while(board[x][y-1] == 0){
						board[x][y-1] = board[x][y];
						board[x][y] = 0;
						//should this stack addition line be here? uhhhhhhhhh I mean it'll work
						stacksOnStacks.push(board);
						break;
					} 
					if(board[x][y] == board[x][y-1]){
						board[x][y] = board[x][y] * 2;
						board[x][y] = 0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}
		if (dir == SlideDirection.RIGHT){
			for(int x = 0; x < board.length; x++){
				for(int y = board[x].length-1; y > 0; y--){
					while(board[x][y-1] == 0){
						board[x][y-1] = board[x][y];
						board[x][y] = 0;
						//should this stack addition line be here? uhhhhhhhhh I mean it'll work
						stacksOnStacks.push(board);
						break;
					} 
					if(board[x][y] == board[x][y-1]){
						board[x][y] = board[x][y] * 2;
						board[x][y] = 0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}
		if(dir==SlideDirection.UP){
			for(int x = 0; x < board.length; x++){
				for(int y = board[x].length-1; y>0; y--){
					while(board[x][y-1] == 0){
						board[x][y-1] = board[x][y];
						board[x][y] = 0;
						//should this stack addition line be here? uhhhhhhhhh I mean it'll work
						stacksOnStacks.push(board);
						break;
					} 
					if(board[x][y] == board[x][y-1]){
						board[x][y] = board[x][y] * 2;
						board[x][y] = 0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}
		if(dir==SlideDirection.DOWN){
			for(int x = 0; x<board.length; x++){
				for(int y = board[x].length-1; y>0; y--){
					while(board[x][y-1] == 0){
						board[x][y-1] = board[x][y];
						board[x][y] = 0;
						//should this stack addition line be here? uhhhhhhhhh I mean it'll work
						stacksOnStacks.push(board);
						break;
					} 
					if(board[x][y] == board[x][y-1]){
						board[x][y] = board[x][y] * 2;
						board[x][y] = 0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}
			return slideDir;
		}

		/**
		 * @return ArrayList<Cell> gives back a board of cells. I'd never use it, but hey, it's there.
		 */
		@Override
		public ArrayList<Cell> getNonEmptyTiles() {
			// nested for loop over whole board and if the value in the board 'cell'
			// !0 then create new cell at that location.

			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board[x].length; y++) {
					if (!(board[x][y] == 0)){
						gameCell = new Cell(x, y, board[x][y]);
						temp.add(gameCell);
					}
				}
			}
			return temp;
		}

		/**
		 * call getNonEmptyTiles and if any values == winCon then set equal to
		 * USER_WON, if all tiles filled and no moves, USER_LOST if no winning value
		 * and still moves available, IN_PROGRESS
		 * 
		 */
		@Override
		public GameStatus getStatus() {
			GameStatus status;
			if (gameCell.value != winCon) {
				status = GameStatus.IN_PROGRESS;
			}
			if (gameCell.value == winCon) {
				status = GameStatus.USER_WON;
			} 
			else {
				// status = GameStatus.USER_LOST;
				status = GameStatus.IN_PROGRESS;
			}
			return status;
		}

		@Override
		public void undo() {
			// set game goard equal to to board when pop
			board = stacksOnStacks.pop();
		}

		@Override
		public int[][] getBoard() {
			return this.board;
		}

	}
