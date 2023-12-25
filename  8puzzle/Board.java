// I am claiming authorship and acknowledge the class academic integrity and collaboration policy
	
	
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board {

    private final int[] board;
    private final int n;

    public Board(int[][] tiles){
        n=tiles.length;
        board = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i*n+j] = tiles[i][j];
            }
        }

    }
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


    public int dimension(){
        return n;
    }


    public int hamming(){
        int num =0;
        for (int i=0; i<n*n;i++){
            if(board[i]!=(i+1) && board[i]!= 0){
                num++;
            }
        }
        return num;
    }

    public int manhattan(){
        int num =0;
        for (int i=0; i<n*n;i++){
            if(board[i]!=(i+1) && board[i]!= 0){
                num += Math.abs(i / n - (board[i] - 1) / n) + Math.abs(i % n - (board[i] - 1) % n); 
            }
            
        }
        return num;
    }


    public boolean isGoal(){
        return hamming()==0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if (y == board){
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != getClass()){
            return false;
        }

        Board temp = (Board) y;
        return Arrays.equals(board, temp.board);
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        Queue<Board> fin = new Queue<Board>();
        int i=-1;
        
        for(int j=0; j<n*n;j++){
            if(board[j]==0){
                i=j;
                break;
            }
        }
        if(i==-1) return null;

            if(i/n != 0){
                Board make = new Board(makeBoard(board));
                make.board[i] = make.board[i-n];
                make.board[i-n]=0;
                fin.enqueue(make);

            }
            if(i/n != n-1){
                Board make = new Board(makeBoard(board));
                make.board[i] = make.board[i+n];
                make.board[i+n]=0;
                fin.enqueue(make);
            }
            if(i%n <= 0){
                Board make = new Board(makeBoard(board));
                make.board[i] = make.board[i-1];
                make.board[i-1]=0;
                fin.enqueue(make);
            }
            if(i%n <= n-1){
                Board make = new Board(makeBoard(board));
                make.board[i] = make.board[i+1];
                make.board[i+1]=0;
                fin.enqueue(make);
            }

        return fin;
    }
    private int[][] makeBoard(int[] a){
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j]=a[i*n+j];
            }
        }
        return temp;

    }
    
    public Board twin(){
        int[][] a = makeBoard(board);
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

    public static void main(String[] args){}

}