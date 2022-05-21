/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintains the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToeGame {

   /**
	* The board of the game, stored as a single array.
	*/
	private CellValue[] board;


   /**
	* level records the number of rounds that have been
	* played so far. Starts at 0.
	*/
	private int level;

    /**
	* gameState records the current state of the game.
	*/
	private GameState gameState;


    /**
	* lines is the number of lines in the grid
	*/
	private final int lines;

    /**
	* columns is the number of columns in the grid
	*/
	private final int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game. 
	* For simplicity, it will be always 3 in this assignment.
	*/
	private final int sizeWin; 


   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	public TicTacToeGame(){
		// your code here
		this.lines = 3;
		this.columns = 3;
		this.sizeWin = 3;
		this.level = 0;
		this.gameState = GameState.PLAYING;
		this.board = new CellValue [this.lines * this.columns];
		emptyBoard();
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){
		// your code here
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = 3;
		this.level = 0;
		this.gameState = GameState.PLAYING;
		this.board = new CellValue [this.lines * this.columns];
		emptyBoard();
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){
		// your code here
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
		this.level = 0;
		this.gameState = GameState.PLAYING;
		this.board = new CellValue [this.lines * this.columns];
		emptyBoard();
	}

	private void emptyBoard(){
		for (int i = 0; i < this.lines * this.columns; i++)
			this.board[i] = CellValue.EMPTY;
	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){
		// your code here
		return this.lines;
	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){
		// your code here
		return this.columns;
	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){
		// your code here
		return this.level;
	}


   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){
		// your code here
		return this.gameState;
	}

   /**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){
		// your code here
		return this.sizeWin;
	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should
	* play next.
	* This method does not modify the state of the
	* game.
	* @return
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
	public CellValue nextCellValue(){
		if (level % 2 == 0)//if an even numbered round (0, 2, 4, etc.)
			return CellValue.X;
		return CellValue.O;//if an odd numbered round (1, 3, 5, etc.)
	}

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {
		return board[i];
	}

   /**
	* This method is called by the next player to play
	* at the cell  at index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valid, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, it is acceptable to keep playing
	* after a game is already won. If that is the case,
	* a message should be printed out and the move recorded.
	* the winner of the game is the player who won first
   	* @param i
    * the index of the cell in the array board that has been
    * selected by the next player
  	*/
	public void play(int i) {
		// your code here
		try{//catch invalid index
			if (this.board[i] == CellValue.EMPTY){//if index is empty
				this.board[i] = this.nextCellValue();
				setGameState(i);
				this.level++;
			}
			else//chosen cell isnt empty
				System.out.println("This cell has already been played");
		} catch (ArrayIndexOutOfBoundsException invalid){
			System.out.println("The value should be between 1 and " + (this.lines*this.columns));
		}
	}

   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set.
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (the the game was playing). Therefore, it only needs to
	* check if playing at index i has concluded the game
	* So check if 3 cells are formed to win.
   	* @param i
    *  the index of the cell in the array board that has just
    * been set
  	*/
	private void setGameState(int index){
		if (this.gameState == GameState.PLAYING){//once game's "over", it won't check for winners
			if (checkWin(this.nextCellValue(), index)){//if the current player wins
				if (this.nextCellValue() == CellValue.O)
					this.gameState = GameState.OWIN;
				else
					this.gameState = GameState.XWIN;
			}
			else if (checkFull())//draw if the board's full
				this.gameState = GameState.DRAW;
		}
	}

	//checks if person wins vertically or horizontally
	private boolean checkVH(CellValue player, int index){
		int start = index % this.columns;
		int end = this.columns * (this.lines - 1) + start;

		//this is the vertical check
		if (index <= end - this.columns && index >= start + this.columns){
			if (this.board[index] == player && this.board[index-this.columns] == player && this.board[index+this.columns] == player)
				return true;
		}
		if (index >= start + (2*this.columns)){
			if (this.board[index-(2*this.columns)] == player && this.board[index-this.columns] == player && this.board[index] == player)
				return true;
		}
		if (index <= end - (2*this.columns)){
			if (this.board[index+(2*this.columns)] == player && this.board[index+this.columns] == player && this.board[index] == player)
				return true;
		}

		//this is the horizontal check
		if (index % this.columns != 0 && index % this.columns != this.columns - 1){
			if (this.board[index] == player && this.board[index-1] == player && this.board[index+1] == player)
				return true;
		}
		if (index % this.columns >= 2){
			if (this.board[index-2] == player && this.board[index-1] == player && this.board[index] == player)
				return true;
		}
		if (index % this.columns <= this.columns - 3){
			if (this.board[index+2] == player && this.board[index+1] == player && this.board[index] == player)
				return true;
		}

		//if it hasn't won horizontally or vertically by now, it hasn't won.
		return false;
	}


	//checks if person wins diagonally
	private boolean checkDiagonal(CellValue player, int index){
		int upOver = index - (this.columns - 1);
		int downOver = index + this.columns + 1;
		int upBack = index - (this.columns + 1);
		int downBack = index + this.columns - 1;
		int upOverTwo = index - 2*(this.columns - 1);
		int downOverTwo = index + 2*(this.columns + 1);
		int upBackTwo = index - 2*(this.columns + 1);
		int downBackTwo = index + 2*(this.columns - 1);

		int colNumber = index % this.columns;
		
		if (colNumber-2 >= 0){//Run left side tests
			try{ //Catch if the code will test out of bounds 
				if (this.board[index] == player && this.board[upBack] == player && this.board[upBackTwo] == player)
					return true;
			} catch (Exception e){
				System.out.println("Trace");
			}
			
			try{
				if (this.board[index] == player && this.board[downBack] == player && this.board[downBackTwo] == player)
					return true;
			} catch (Exception e){}
		}

		if (colNumber+1 <= this.columns-1 && colNumber-1 >= 0){ //Run middle tests
			System.out.println("Mid");
			try{
				if (this.board[upBack] == player && this.board[index] == player && this.board[downOver] == player)
					return true;
			} catch (Exception e){}

			try{
				if (this.board[downBack] == player && this.board[index] == player && this.board[upOver] == player)
					return true;
			} catch (Exception e){}
		}
		
		if (colNumber+2 <= this.columns-1){ //Run right side test
			try{
				if (this.board[index] == player && this.board[upOver] == player && this.board[upOverTwo] == player)
					return true;
			} catch (Exception e){}

			try{
				if (this.board[index] == player && this.board[downOver] == player && this.board[downOverTwo] == player)
					return true;
			} catch (Exception e){}
		}

		return false;
	}

	//overall checks if someone wins by running checkVertical, checkHorizontal, and checkDiagonal
	private boolean checkWin(CellValue player, int index){
		return this.checkDiagonal(player, index) || this.checkVH(player, index);
	}

	private boolean checkFull(){
		for (int i = 0; i < this.columns * this.lines; i++){
			if (this.board[i] == CellValue.EMPTY)
				return false;
		}
		return true;
	}

	final String NEW_LINE = System.getProperty("line.separator"); // returns the OS dependent line separator

   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	*
   	* @return
    *  String representation of the game
  	*/
	public String toString(){
		// your code here
		// use NEW_LINE defined above rather than \n
		int index = 0;
		String result = "";
		for (int i = 0; i < this.lines; i++){
			result += " ";
			for (int j = 0; j < this.columns; j++){
				if (this.board[index] == CellValue.X)
					result += "X ";
				else if (this.board[index] == CellValue.O)
					result += "O ";
				else
					result += "  ";

				if (j + 1 == this.columns)
					result += "  ";
				else
					result += "| ";
				index++;
			}
			if (i != this.lines - 1){
				result += NEW_LINE;
				for (int j = 0; j < this.columns-1; j++)
					result += "----";
				result += "---";
				result += NEW_LINE ;
			}
		}
		result += NEW_LINE;
		return result;
	}

}