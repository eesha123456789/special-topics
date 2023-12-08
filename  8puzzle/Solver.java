import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import java.util.LinkedList;


public class Solver {
    private int numOfMoves;
    private boolean bool;
    private LinkedList<Board> answers;
    public Solver(Board initial){
        if(initial==null) throw new IllegalArgumentException();
        numOfMoves = -1;
        bool=true;
        SearchNode main = new SearchNode(null,initial, 0);
        SearchNode shadow = new SearchNode(null,initial.twin(),0);
        MinPQ<SearchNode> mainPQ = new MinPQ<SearchNode>(main.order());
        MinPQ<SearchNode> shadowPQ = new MinPQ<SearchNode>(shadow.order());
        SearchNode mainDelMin = mainPQ.delMin();
        SearchNode shadowDelMin = shadowPQ.delMin();
        mainPQ.insert(main);
        shadowPQ.insert(shadow);   
        answers = new LinkedList<Board>(); 
        

        while(!mainDelMin.outcome.isGoal()) {
            if(!shadowDelMin.outcome.isGoal()){
                if (mainDelMin.outcome.isGoal()) {
                    bool = true;
                    answers.addFirst(mainDelMin.outcome);
                    mainDelMin = mainDelMin.previous;
                    break;
                }
                for (Board i : mainDelMin.outcome.neighbors()) {      
                    if (mainDelMin.previous == null || !i.equals(mainDelMin.previous.outcome)) { 
                        SearchNode n = new SearchNode(null, i ,0);
                        n.steps = mainDelMin.steps + 1;
                        n.previous = mainDelMin;
                        mainPQ.insert(n);
                        }
                }
                
                for (Board i : shadowDelMin.outcome.neighbors()) {
                    if (shadowDelMin.previous == null ||!i.equals(shadowDelMin.previous.outcome)) {
                        SearchNode n = new SearchNode(null, i ,0);
                        n.steps = shadowDelMin.steps + 1;
                        n.previous = shadowDelMin;
                        shadowPQ.insert(n);
                    }
                }
                
                mainDelMin = mainPQ.delMin();
                shadowDelMin = shadowPQ.delMin();
         } 
        }   
    }
    private class SearchNode{
        private SearchNode previous;
        private Board outcome;
        private int steps;
        private int manhattan;
        private SearchNode(SearchNode previous, Board outcome, int steps){
            this.previous=previous;
            this.outcome=outcome;
            this.steps=steps;
            manhattan=outcome.manhattan();
        }

        private Comparator<SearchNode> order(){
            return new Order();
        }

        private class Order implements Comparator<SearchNode>{
            public int compare(SearchNode num_1, SearchNode num_2){
                int first=num_1.steps + num_1.manhattan;
                int second = num_2.steps +num_2.manhattan;
                if(first < second){
                    return -1;
                }
                if(first > second){
                    return 1;
                }
                return 0;
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return bool;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return numOfMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if(!bool){
            return null;
        }
        return answers;
}
    

    // test client (see below) 
    public static void main(String[] args) {
    }
}