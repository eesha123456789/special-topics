import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;


public class SAP {
    private final Digraph main;
    private final HashMap<HashSet<Integer>, int[]> softwareCache;
    public SAP(Digraph G){
        main = new Digraph(G);
        softwareCache = new HashMap<HashSet<Integer>, int[]>();
    }
 
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
        HashSet<Integer> pointer = new HashSet<>();
        pointer.add(v);
        pointer.add(w);
        int[] fin = softwareCache.get(pointer);
        return fin[0];
    }
 
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w){
        HashSet<Integer> pointer = new HashSet<>();
        pointer.add(v);
        pointer.add(w);
        int[] fin = softwareCache.get(pointer);
        return fin[0];
    }
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w){
        return -1;
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        return -1;
    }


    // do unit testing of this class
    public static void main(String[] args){}
 }
 