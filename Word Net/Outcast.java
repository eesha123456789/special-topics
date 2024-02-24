// I am claiming authorship and acknowledge the class academic integrity and collaboration policy
	
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet network;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.network = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        String outcast = nouns[0];
        int temp1 = 0;
        for (String i : nouns) {
            temp1 += network.distance(nouns[0], i);
        }

        for (int i = 1; i < nouns.length; i++) {
            int temp2 = 0;
            for (String n : nouns) {
                temp2 += network.distance(nouns[i], n);
            }
            if (temp1 < temp2) {
                temp1 = temp2;
                outcast = nouns[i];
            }
        }
        return outcast;
    }

    // unit test
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}