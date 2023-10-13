import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args)
    {
        RandomizedQueue<String> fin = new RandomizedQueue<String>();
        while (!fin.isEmpty()) {
            fin.enqueue(StdIn.readString());
        }
        
        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            System.out.println(fin.dequeue());
        }
    }
}