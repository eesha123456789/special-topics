import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomWord{
    public static void main(String args[]){
        String winner="";
        int a=1;
        while (!StdIn.isEmpty()){
            String now = StdIn.readString();
            if(StdRandom.bernoulli (1.0/a)){
                winner=now;
            }
            a++;
        }
        StdOut.println(winner);
    }
}
