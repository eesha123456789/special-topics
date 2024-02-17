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
    private int update(Iterable<Integer> v, Iterable<Integer> w, String type){
        BreadthFirstDirectedPaths vBFDP = new BreadthFirstDirectedPaths(main, v);
        BreadthFirstDirectedPaths wBFDP = new BreadthFirstDirectedPaths(main, w);
        int path = Integer.MAX_VALUE;
        boolean temp=false;
        int dist=-2;
        int add=-1;
        for(int i=0;i<main.V();i++){
            if(vBFDP.distTo(i) <path && vBFDP.hasPathTo(i) && wBFDP.distTo(i)<path && wBFDP.hasPathTo(i)){
                add=wBFDP.distTo(i) + vBFDP.distTo(i);
                if(path > add){
                    path = add;
                    temp=true;
                    dist = i;
                }
            }
        }
        if(temp){
            if(type.equals("length")){
                return path;
            }
            else if(type.equals("ancestor")){
                return dist;
            }   
        }
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args){}
 }
 