package game1024;

import java.lang.Math;
import java.util.*;

public class NumberGame implements NumberSlider {
	Random randy = new Random();
	Cell gameCell;
	TextUI run;
	GameStatus status;
	SlideDirection direction;
	public int winCon = 1024;
	private int[][] board;
	private int row
	private int col;
	public ArrayList<Cell> temp = new ArrayList<Cell>();
	private Stack StacksOnStacks = new Stack();

	@Override
	public void resizeBoard(int height, int width, int winCon) {
		try {
			for (int i = 1; i < 20; i++) {
				int val = (int) Math.pow(2, i);
				if (val == winCon) {
					board = new int[height][width];
					this.winCon = winCon;
					this.row = height;
					this.col = width;
				}
			}
		} 
		catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void reset() {
		// TODO Make the "this.row" for all of the methods board.length and board[i].length !!
		for (int x = 0; x < this.row; x++) {
			for (int y = 0; y < this.col; y++) {
				board[i][j] = 0;
			}
		}
		placeRandomValue();
		placeRandomValue();
	}

	@Override
	public void setValues(int[][] ref) {
		// TODO Auto-generated method stub
		for (int x = 0; x < this.row; x++) {
			for (int y = 0; y < this.col; y++) {
				board[i][j] = ref[i][j];
			}
		}
	}

	/**
	 * 
	 * @return Cell a new cell in the board.
	 */
	//TODO: modify to make it not do the while checker!
	//while smarter, it is not what I would have done. 
	//I would have had it check random spots until it placed something!
	@Override
	public Cell placeRandomValue() {
		boolean b = false;
		int randVal = randy.nextInt(8) + 1;;
		int value = (int) Math.pow(2, (randVal));
		
		while (b == false) {
			int r = randy.nextInt(4);
			int c = randy.nextInt(4);
			if (board[x][y] == 0) {
				board[x][y] = value;
				b = true;
			}
		}
		return gameCell;
	}

	@Override
	public boolean slide(SlideDirection dir) {
		boolean slideDir = false;
		//TODO: mod the directions accordingly. Right now they all do the same thing.
		if(dir == SlideDirection.LEFT){
			for(int x = 0; i<this.row; i++){
				for(int y = this.col-1; y>0; y--){
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
			for(int x = 0; i<this.row; i++){
				for(int y = this.col-1; y>0; y--){
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
			for(int x = 0; i<this.row; i++){
				for(int y = this.col-1; y>0; y--){
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
			for(int x = 0; i<this.row; i++){
				for(int y = this.col-1; y>0; y--){
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

			for (int x = 0; x < this.row; x++) {
				for (int y = 0; y < this.col; y++) {
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
			// TODO Auto-generated method stub
			// set game goard equal to to board when pop
			board = stacksOnStacks.pop();
		}

		@Override
		public int[][] getBoard() {
			return this.board;
		}

	}