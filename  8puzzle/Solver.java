import edu.princeton.cs.algs4.MinPQ;
import java.util.LinkedList;

public class Solver {
    private LinkedList<Board> outcome;
    private boolean bool;
    // find a solution to the initial board (using the A* algorithm)

    public Solver(Board initial){

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return bool;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if(bool){
            return outcome.size() -1;
        }
        else{
            return -1;
        }

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if(bool){
            return outcome;
        }
        return null;
    }

    // test client (see below) 
    public static void main(String[] args){}

}
