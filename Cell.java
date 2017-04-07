package game1024;

public class Cell implements Comparable<Cell> {
    public int row, column, value;

    public Cell()
    {
        this(0,0,0);
    }
    public Cell (int r, int c, int v)
    {
        row = r;
        column = c;
        value = v;
    }

    /* must override equals to ensure List::contains() works
     * correctly
     */
    @Override
    public int compareTo (Cell other) {
        if (this.row < other.row) return -1;
        if (this.row > other.row) return +1;

        /* break the tie using column */
        if (this.column < other.column) return -1;
        if (this.column > other.column) return +1;

        System.out.println(this.value - other.value);
        return this.value - other.value;
    }
    public int cellDiff(Cell other){
    	System.out.println(this.value - other.value);
        return this.value - other.value;
    }
    
    public int getRow(){
    	return this.row;
    }
    public int getCol(){
    	return this.column;
    }
    public int getVal(){
    	return this.value;
    }
    
}
