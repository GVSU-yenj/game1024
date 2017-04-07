package game1024;

import java.lang.Math;
import java.util.*;

public class NumberGame implements NumberSlider {
	Random randy = new Random();
	Cell gameCell;
	TextUI run;
	GameStatus status;
	SlideDirection direction;
	public int winningValue = 2048;
	private int[][] board;
	private int row
	private int col;
	public ArrayList<Cell> temp = new ArrayList<Cell>();
	private Stack StacksOnStacks = new Stack();

	@Override
	public void resizeBoard(int height, int width, int winningValue) {
		try {
			for (int i = 1; i < 20; i++) {
				int val = (int) Math.pow(2, i);
				if (val == winningValue) {
					board = new int[height][width];// gameCell = new
					// Cell(height, width,
					// winningValue);
					this.winningValue = winningValue;
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
		// TODO Auto-generated method stub
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				board[i][j] = 0;
			}
		}
		placeRandomValue();
		placeRandomValue();
	}

	@Override
	public void setValues(int[][] ref) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				board[i][j] = ref[i][j];
			}
		}

	}

	/**
	 * 
	 * 
	 * @return Cell a new cell in the board.
	 */
	//TODO: modify to make it not do the while checker!
	//while smarter, it is not what I would have done. 
	//I would have had it check random spots until it placed something!
	@Override
	public Cell placeRandomValue() {
		// gameCell = new Cell();
		boolean b = false;
		int randVal;
		int value;

		randVal = randy.nextInt(8) + 1;
		value = (int) Math.pow(2, (randVal));
		while (b == false) {
			int r = randy.nextInt(4);
			int c = randy.nextInt(4);
			if (board[r][c] == 0) {
				board[r][c] = value;
				b = true;
			}
		}
		return gameCell;
	}

	@Override
	public boolean slide(SlideDirection dir) {
		boolean slideDir = false;

		//TODO: mod the directions accordingly. Right now they all do the same thing.
		if (dir == SlideDirection.LEFT) {
			for (int i = 0; i<this.row; i++){
				for (int j = this.col-1; j>0; j--){
					while(board[i][k-1] == 0){
						board[i][k-1] = board[i][k];
						board[i][k] = 0;
						stacksOnStacks.push(board);
						break;
					} 
					if(board[i][k] == board[i][k-1]){
						board[i][k-1] = board[i][k] * 2;
						board[i][k] =0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}
		if (dir == SlideDirection.RIGHT) {
			for (int i = 0; i<this.row; i++){
				for (int j = this.col-1; j>0; j--){
					while(board[i][k-1] == 0){
						board[i][k-1] = board[i][k];
						board[i][k] = 0;
						stacksOnStacks.push(board);
						break;
					} 
					if(board[i][k] == board[i][k-1]){
						board[i][k-1] = board[i][k] * 2;
						board[i][k] =0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}
		if(dir==SlideDirection.UP){
			for (int i = 0; i<this.row; i++){
				for (int j = this.col-1; j>0; j--){
					while(board[i][k-1] == 0){
						board[i][k-1] = board[i][k];
						board[i][k] = 0;
						stacksOnStacks.push(board);
						break;
					} 
					if(board[i][k] == board[i][k-1]){
						board[i][k-1] = board[i][k] * 2;
						board[i][k] =0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
		}

		if(dir==SlideDirection.DOWN){
			for(int i = 0;i<this.row-1;i++){
				for (int j = 0; j < this.col; j++) {
					while (board[i - 1][j] == 0) {
						board[k - 1][j] = board[k][j];
						board[k][j] = 0;
						stacksOnStacks.push(board);
						break;
					}
					if(board[k][j] == board[k - 1][j]){
						board[k - 1][j] = board[k][j] * 2;
						board[k][j] = 0;
						stacksOnStacks.push(board);
						break;
					}
				}
			}
			return slideDir;
		}

		@Override
		public ArrayList<Cell> getNonEmptyTiles() {
			// nested for loop over whole board and if the value in the board 'cell'
			// !0 then create new cell at that location.

			for (int i = 0; i < this.row; i++) {
				for (int j = 0; j < this.col; j++) {
					if (!(board[i][j] == 0)){
						gameCell = new Cell(i, j, board[i][j]);
						temp.add(gameCell);
					}
				}
			}
			return temp;
		}

		/**
		 * call getNonEmptyTiles and if any values == winningValue then set equal to
		 * USER_WON, if all tiles filled and no moves, USER_LOST if no winning value
		 * and still moves available, IN_PROGRESS
		 * 
		 */
		@Override
		public GameStatus getStatus() {
			GameStatus status;
			if (gameCell.value != winningValue) {
				status = GameStatus.IN_PROGRESS;
			}
			if (gameCell.value == winningValue) {
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
			stacksOnStacks.pop();
		}

		@Override
		public int[][] getBoard() {
			return this.board;
		}

	}
