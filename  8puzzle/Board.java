public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[] board;
    private int n;

    public Board(int[][] tiles){
        n=tiles.length;
        board=new int[n*n];
        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
            board[i*n+j]=tiles[i][j];
            }
        }
    }
                                            
    // string representation of this board
    public String toString(){
        String fin = "";
        for(int i=0;i<n*n;i++){
            if(i%3==1){
                fin+="\n";
            }
            fin+=board[i];
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
            if(board[i]+1!=board[i+1] && board[i]!= 0){
                num++;
            }
        }
        return num;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int num =0;
        for (int i=0; i<n*n-1;i++){
            if(board[i]+1!=board[i+1] && board[i]!= 0){
                num++;
            }
        }
        return num;
    }

    // is this board the goal board?
    public boolean isGoal(){
        for (int i=0; i < n*n-1; i++){
            if (board[i]!=i+1){
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
    public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()

    // unit testing (not graded)
    public static void main(String[] args){}

}