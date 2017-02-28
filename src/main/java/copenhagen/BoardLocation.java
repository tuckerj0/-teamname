package copenhagen;

/**
 * This class is for locations on the game board. Each board location contains a row and a column.
 */
public class BoardLocation{
    public int row = -1;
    public int column = -1;

    public BoardLocation(int column,int row){
        this.row = row;
        this.column = column;
    }

    public BoardLocation(){
        this.row = -1;
        this.column = -1;
    }

    /**
     * This is a getter that retrieves the row number.
     * @return This function returns the row number.
     */
	public int getRow() {
		return row;
	}

    /**
     * This is a setter that sets the row number of a given location.
     * @param row This parameter is the row number.
     */
	public void setRow(int row) {
		this.row = row;
	}

    /**
     * This is a getter that retrieves the column number.
     * @return This function returns the column number.
     */
	public int getColumn() {
		return column;
	}

    /**
     * This is a setter that sets the column number of a given location.
     * @param column This parameter is the column number.
     */
	public void setColumn(int column) {
		this.column = column;
	}
}
