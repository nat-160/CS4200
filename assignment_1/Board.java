import java.util.Arrays;
public class Board{
    private int row, col, count;
    private int[] queens = new int[8];

    /**
     * Defaults column of row 0 to 0
     */
    public Board(){
        this(0);
    }

    /**
     * Initializes the board
     *
     * @param   column  position of queen in row 0
     */
    public Board(int column){
        row = count = 0;
        col = column;
        Arrays.fill(queens, -1);
    }

    public int[] getBoard(){
        int[] data = Arrays.copyOf(queens,8);
        if(row<8)
            data[row] = -1;
        return data;
    }

    /**
     * Returns if board is finished
     *
     * @return  boolean status
     */
    public boolean isSolved(){
        return row >= 8;
    }

    /**
     * Progresses the board by one move
     *
     * @return  formatted string
     */
    public String next(){
        if(row >= 8){
            return "A solution is found. Restart program to start over.";
        }

        count++;

        if(count==1){
            queens[0] = col;
            row++;
            return "A queen is placed in row 0 and column " + col;
        }

        int move = nextValidMove();
        if(move!=-1){
            queens[row] = move;
            row++;
            if(row==8) return "A solution is found.";
            return "A queen is placed in row " + (row-1) + " and column " + queens[row-1];
        }
        queens[row] = -1;
        row--;
        return "No queen can be placed in row " + (row+1) + ". Backtrack to the row " + row + ".";
    }

    //Helper method for next()
    private int nextValidMove(){
        boolean[] validBoard = new boolean[8];
        Arrays.fill(validBoard, true);
        for(int i=0;i<row;i++){
            //elim queens[i] all the way down, same i
            validBoard[queens[i]] = false;
            //elim diagonal left to row
            int x = queens[i] - i + row;
            if(0 <= x && x < 8)
                validBoard[x] = false;
            //elim diagonal right to row
            x = queens[i] + i - row;
            if(0 <= x && x < 8)
                validBoard[x] = false;
        }
        //start from queens[row] because prev have used
        for(int i=queens[row]+1;i<8;i++)
            if(validBoard[i])
                return i;
        return -1;
    }

    /**
     * A string representation of the board
     *
     * @return      formatted string
     */
    public String toString(){
        String output = "";
        for(int i=0;i<8;i++){
            output += i + " ";
            if(queens[i]!=-1 && i<row){
                for(int j=0;j<queens[i];j++){
                    output += "  ";
                }
                output += "Q";
            }
            output += "\n";
        }
        output += "  0 1 2 3 4 5 6 7";
        return output;
    }

    /**
     * Number of times next() has been called
     *
     * @return      total moves
     */
    public int getMoveCount(){
        return count;
    }
}
