import java.util.LinkedList;

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] board;
    private int n;
    private int [] one_D;

    public Board(int[][] tiles){
        n=tiles.length;
        board=new int[n][n];
        one_D=new int[n*n];
        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                board[i][j]=tiles[i][j];
                one_D[i*n+j]=tiles[i][j];
            }
        }
    }
                                            
    // string representation of this board
    public String toString(){
        String fin = "";
        for(int i=0;i<n*n;i++){
            for(int j=0; j<n;j++){
                fin+=board[i][j];
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
        for (int i=0; i<n*n-1;i++){
            if(one_D[i]+1!=one_D[i+1] && one_D[i]!= 0){
                num++;
            }
        }
        return num;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int num =0;
        for (int i=0; i<n*n-1;i++){
            if(one_D[i]+1!=one_D[i+1] && one_D[i]!= 0){
                num+= (one_D[i]-1)/n-i/n;
                num+=(one_D[i]-1)% n - i % n;
            }
        }
        return num;
    }

    // is this board the goal board?
    public boolean isGoal(){
        for (int i=0; i < n*n; i++){
            if (one_D[i]!=i){
                return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(board==y){
            return true;
        }
        else{
            return false;
        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        LinkedList<Board> fin = new LinkedList<Board>();
        int[][] a = board;
        if(one_D[0] != 0){
            int temp = a[one_D[0]-1][one_D[1]];
            a[one_D[0]-1][one_D[1]] = a[one_D[0]][one_D[1]];
            a[one_D[0]][one_D[1]] = temp;
            fin.add(new Board(a));
        }
        if(one_D[0] != 0){
            int temp = a[one_D[0]+1][one_D[1]];
            a[one_D[0]+1][one_D[1]] = a[one_D[0]][one_D[1]];
            a[one_D[0]][one_D[1]] = temp;
            fin.add(new Board(a));
        }
        if(one_D[0] != 0){
            int temp = a[one_D[0]][one_D[1]-1];
            a[one_D[0]][one_D[1]-1] = a[one_D[0]][one_D[1]];
            a[one_D[0]][one_D[1]] = temp;
            fin.add(new Board(a));
        }
        if(one_D[0] != 0){
            int temp = a[one_D[0]][one_D[1]+1];
            a[one_D[0]][one_D[1]+1] = a[one_D[0]][one_D[1]];
            a[one_D[0]][one_D[1]] = temp;
            fin.add(new Board(a));
        }
        return fin;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        int[][] a = board;
        if(board[0][1] != 0 && board[0][0] != 0){
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