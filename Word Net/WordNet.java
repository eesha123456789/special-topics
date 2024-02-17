import java.util.HashMap;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;


public class WordNet {
    private final Digraph main;
    private final HashMap<String, Bag<Integer>> synsetIds;
    private final HashMap<Integer, String> synsetStore;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){
        main=new Digraph(synsets.length());
        synsetIds=new HashMap<String, Bag<Integer>>();
        synsetStore=new HashMap<Integer, String>();
        sap=new SAP(main);
    }
 
    // returns all WordNet nouns
    public Iterable<String> nouns(){
        return synsetIds.keySet();
    }
 
    // is the word a WordNet noun?
    public boolean isNoun(String word){
        return synsetIds.containsKey(word);
    }
 
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        return sap.length(synsetIds.get(nounA), synsetIds.get(nounB));
    }
 
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
        int temp = sap.ancestor(synsetIds.get(nounA), synsetIds.get(nounB));
        return synsetStore.get(temp);
    }
 
    // do unit testing of this class
    public static void main(String[] args){}
 }