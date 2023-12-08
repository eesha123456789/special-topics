import java.util.LinkedList;
import java.util.Arrays;

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int[] board;
    private final int n;
    private int empty_pos;

    public Board(int[][] tiles){
        n=tiles.length;
        board = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i*n+j] = tiles[i][j];
                if(tiles[i][j] == 0){
                    empty_pos=i*n+j;

                }
            }
        }

    }
                                            
    // string representation of this board
    public String toString(){
        String fin = "";
        fin+=n+"\n";
        for(int i=0;i<n;i++){
            for(int j=0; j<n;j++){
                fin+=board[i*n+j];
                if(j == n-1){
                    break;
                }
                fin+= " ";
            }
            fin+="\n";
        }
        return fin;
    }

    // board dimension n
    public int dimension(){
        return n;
    }

    // number of tiles out of place
    public int hamming(){
        int num =0;
        for (int i=0; i<n*n;i++){
            if(board[i]!=(i+1) && board[i]!= 0){
                num++;
            }
        }
        return num;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int num =0;
        for (int i=0; i<n*n;i++){
            if(board[i]!=(i+1) && board[i]!= 0){
                num += Math.abs(i / n - (board[i] - 1) / n) + Math.abs(i % n - (board[i] - 1) % n); 
            }
            
        }
        return num;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming()==0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(y== board){
            return true;
        }
        if(y==null || y.getClass() != board.getClass() || ((Board)y).n != n){
            return false;
        }
        for (int i = 0; i < n*n; i++) {
            if (board[i] != ((Board) y).board[i]){
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        LinkedList<Board> fin = new LinkedList<Board>();
        int[] make = new int[n];
        for(int i=0; i<n*n;i++){
            if(i/n != 0){
                int[][] a = Arrays.copyOf(makeBoard(),n);
                make[i] = board[i-n];
                make[i-n]=0;
                fin.push(new Board(a));

            }
            if(i/n != n-1){
                int[][] a = Arrays.copyOf(makeBoard(),n);
                make[i] = board[i+n];
                make[i+n]=0;
                fin.push(new Board(a));
            }
            if(i%n != 0){
                int[][] a = Arrays.copyOf(makeBoard(),n);
                make[i] = board[i-n];
                make[i-n]=0;
                fin.push(new Board(a));
            }
            if(i%n != n-1){
                int[][] a = Arrays.copyOf(makeBoard(),n);
                make[i] = board[i-n];
                make[i-n]=0;
                fin.push(new Board(a));
            }
            return fin;
        }
    }
    /*private Board nextTo(int x, int y){
        int [][] temp = new int[n][n];
        temp = makeBoard();
        Board next = new Board(temp);
        int a = next.board[x];
        next.board[y] = next.board[x];
        next.board[x] = a;
        for (int i = 0; i < n*n; i++) {
            if(board[i] == 0){
                empty_pos= i;
            }
        }
        return next;
    }*/
    private int[][] makeBoard(){
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j]=board[i*n+j];
            }
        }
        return temp;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        int[][] a = makeBoard();
        if(a[0][1] != 0 && a[0][0] != 0){
            int temp = a[0][1];
            a[0][1] = a[0][0];
            a[0][0] = temp;
            return new Board(a);
        }
        else{
            int temp = a[1][1];
            a[1][1] = a[1][0];
            a[1][0] = temp;
            return new Board(a);
        }
    }

    // unit testing (not graded)
    public static void main(String[] args){}

}