import edu.princeton.cs.algs4.StdIn;

import edu.princeton.cs.algs4.StdOut;


public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
	    int a = Integer.valueOf(args[0]);

	    while (!StdIn.isEmpty()){

	        String item = StdIn.readString();

	        queue.enqueue(item);

	    }

	    while (a > 0){

	        StdOut.println(queue.dequeue());

	        a--;

        }


    }

}