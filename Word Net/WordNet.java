// I am claiming authorship and acknowledge the class academic integrity and collaboration policy
	

import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

public class WordNet {

    private final Digraph network;
    private final HashMap<String, Bag<Integer>> map;
    private final HashMap<Integer, String> synsetStore;
    private final SAP sap;

   // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        map = new HashMap<String, Bag<Integer>>();
        synsetStore = new HashMap<Integer, String>();
        int count = readSynsets(synsets);
        network = new Digraph(count);
        readHypernyms(hypernyms);

        DirectedCycle dc = new DirectedCycle(network);
        if (dc.hasCycle())
            throw new IllegalArgumentException();
            sap = new SAP(network);

        int singleRoot = 0;
        for (int i = 0; i < count; i++)
            if (network.outdegree(i) == 0)
                singleRoot++;
        if (singleRoot != 1)
            throw new IllegalArgumentException();
    }
    private int readSynsets(String synsets) {
        if (synsets == null)
            throw new IllegalArgumentException();
        In in = new In(synsets);
        int num = 0;
        while (in.hasNextLine()) {
            num++;
            String[] section = (in.readLine()).split(",");
            int index = Integer.parseInt(section[0]);
            synsetStore.put(index, section[1]);
            String[] words = section[1].split(" ");
            for (String i : words) {
                if (map.get(i) != null) {
                    Bag<Integer> list = map.get(i);
                    list.add(index);
                } 
                else {
                    Bag<Integer> list = new Bag<Integer>();
                    list.add(index);
                    map.put(i, list);
                }
            }
        }
        return num;
    }

    private void readHypernyms(String hypernyms) {
        if (hypernyms == null)
         throw new IllegalArgumentException();
        In in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] section = (in.readLine()).split(",");
            int v = Integer.parseInt(section[0]);
            for (int i = 1; i < section.length; i++) {
                int w = Integer.parseInt(section[i]);
                network.addEdge(v, w);
            }
        }
    }

   // returns all WordNet nouns
    public Iterable<String> nouns() {
        return map.keySet();
    }

   // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        return map.containsKey(word);
    }

   // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!map.containsKey(nounA)||!map.containsKey(nounB)){
            throw new IllegalArgumentException();
        }
        return sap.length(map.get(nounA), map.get(nounB));
    }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!map.containsKey(nounA)||!map.containsKey(nounB)){
            throw new IllegalArgumentException();
        }

        Bag<Integer> indexA = map.get(nounA);
        Bag<Integer> indexB = map.get(nounB);

        int index = sap.ancestor(indexA, indexB);
        return synsetStore.get(index);
    }



   // do unit testing of this class
    public static void main(String[] args) {}

}