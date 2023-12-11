import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;

public class Solver {

    private SearchNode endAnswers;              
    
    private class SearchNode {      
        private SearchNode previous;  
        private Board outcome;    
        private int steps;                 
        public SearchNode(SearchNode previous_input, Board initial, int steps_input) {
            previous = previous_input;
            outcome = initial;
            steps = steps_input;
        }
    }
    
    private class Order implements Comparator<SearchNode> {
        public int compare(SearchNode first, SearchNode second) {
            int compare_first = first.outcome.manhattan() + first.steps;
            int compare_second = second.outcome.manhattan() + second.steps;
            if (compare_first > compare_second){
                return 1;
            } 
            if (compare_first < compare_second){
                return -1;
            }  
            return 0;
        }
    }

    public Solver(Board initial) {   
        MinPQ<SearchNode> mainPQ = new MinPQ<SearchNode>(new Order());
        MinPQ<SearchNode> shadowPQ = new MinPQ<SearchNode>(new Order());
        mainPQ.insert(new SearchNode(null, initial,0));
        shadowPQ.insert(new SearchNode(null, initial, 0));            
        SearchNode mainDelMin = mainPQ.delMin();
        SearchNode shadowDelMin = shadowPQ.delMin();

        while(!mainDelMin.outcome.isGoal() && !shadowDelMin.outcome.isGoal()) {

            for (Board index : mainDelMin.outcome.neighbors()) {      
                if (mainDelMin.previous == null || !index.equals(mainDelMin.previous.outcome)) {  
                    SearchNode i = new SearchNode(null, index, 0);
                    i.steps = mainDelMin.steps + 1;
                    i.previous = mainDelMin;
                    mainPQ.insert(i);
                    }
            }
            
            for (Board index : shadowDelMin.outcome.neighbors()) {
                if (shadowDelMin.previous == null ||!index.equals(shadowDelMin.previous.outcome)) {
                    SearchNode i = new SearchNode(null, index, 0);
                    i.steps = shadowDelMin.steps + 1;
                    i.previous = shadowDelMin;
                    shadowPQ.insert(i);
                    }
            }
             
             mainDelMin = mainPQ.delMin();
             shadowDelMin = shadowPQ.delMin();
         }
         if (mainDelMin.outcome.isGoal()){
            endAnswers = mainDelMin;
         } 
         else {
            endAnswers = null;
         }                              
    }



    public boolean isSolvable() {            
        return endAnswers != null;
    }

    public int moves() {                     
        if (endAnswers == null){
            return -1;
        } 
        return endAnswers.steps;
    }

    public Iterable<Board> solution() {      
        if (endAnswers == null) {
            return null;
        }
        Stack<Board> fin = new Stack<Board>();
        SearchNode i = endAnswers;
        while(i != null){
            fin.push(i.outcome);
            i = i.previous;
        };
        return fin;
    }

    public static void main(String[] args) {}
    }