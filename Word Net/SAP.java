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
        return update(v,w,"length");
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        return update(v,w,"ancestor");
    }
    private int update(Iterable<Integer> v, Iterable<Integer> w, String type) {
        validate(v);
        validate(w);
        BreadthFirstDirectedPaths vBFDP = new BreadthFirstDirectedPaths(main, v);
        BreadthFirstDirectedPaths wBFDP = new BreadthFirstDirectedPaths(main, w);

        int length = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i=0; i<main.V(); i++) {
            if (vBFDP.distTo(i) < length && vBFDP.hasPathTo(i) && wBFDP.distTo(i) < length && wBFDP.hasPathTo(i)) {
                int add = vBFDP.distTo(i) + wBFDP.distTo(i);
                if (length > add) {
                    length = add;
                    ancestor = i;
                }
            }
        }

        if (length != Integer.MAX_VALUE) {
            if(type.equals("length")){
                return length;
            }
            else if(type.equals("ancestor")){
                return ancestor;
            }
        } 
        return -1;

    }
    private void validate (Iterable<Integer> vertices){
        if(vertices == null){
            throw new IllegalAccessError();
        }
        for(int i: vertices){
            if(i < 0|| i >= main.V()){
                throw new IllegalAccessError();
            }
        }
    }
    // do unit testing of this class
    public static void main(String[] args){}
 }
 