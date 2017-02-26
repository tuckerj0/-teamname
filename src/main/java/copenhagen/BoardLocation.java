package copenhagen;

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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
