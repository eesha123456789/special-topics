import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {

    private final Digraph map;
    private final HashMap<HashSet<Integer>, int[]> softwareCache;

    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalAccessError();
        }

        map = new Digraph(G);
        softwareCache = new HashMap<>();
    }
    public int length(int v, int w) {
        update(v, w);
        HashSet<Integer> pointer = new HashSet<>();
        pointer.add(v);
        pointer.add(w);
        int[] value = softwareCache.get(pointer);
        return value[0];
    }

    // a common ancestor of v and w that participates in an shortest ancestral path;
    public int ancestor(int v, int w) {
        update(v, w);
        HashSet<Integer> pointer = new HashSet<>();
        pointer.add(v);
        pointer.add(w);
        int[] value = softwareCache.get(pointer);
        return value[1];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return update(v, w,"length");
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such
    // path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return update(v, w, "ancestor");
    }

    private void update(int v, int w) {
        validate(v);
        validate(w);

        HashSet<Integer> key = new HashSet<>();
        key.add(v);
        key.add(w);

        if (softwareCache.containsKey(key))
            return;

        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(map, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(map, w);

        int length = Integer.MAX_VALUE;
        int ancestor = -2;
        for (int i = 0; i < map.V(); i++) {
            if (vPath.hasPathTo(i) && vPath.distTo(i) < length && wPath.hasPathTo(i)
                    && wPath.distTo(i) < length) {
                int add = vPath.distTo(i) + wPath.distTo(i);
                if (length > add) {
                    length = add;
                    ancestor = i;
                }
            }
        }

        if (length == Integer.MAX_VALUE) {
            length = -1;
            ancestor = -1;
        }

        int[] value = new int[] { length, ancestor };
        softwareCache.put(key, value);
    }

    private int update(Iterable<Integer> v, Iterable<Integer> w, String type) {
        validate(v);
        validate(w);

        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(map, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(map, w);
        int length = Integer.MAX_VALUE;
        boolean bool = false;
        int ancestor = -1;

        for (int i = 0; i < map.V(); i++) {
            if (vPath.hasPathTo(i) && vPath.distTo(i) < length && wPath.hasPathTo(i)
                    && wPath.distTo(i) < length) {
                int add = vPath.distTo(i) + wPath.distTo(i);
                if (length > add) {
                    length = add;
                    bool=true;
                    ancestor = i;
                }
            }
        }

        if (bool) {
            if(type.equals("ancestor")){
                return ancestor;
            }
            if(type.equals("length")){
                return length;
            }
        } 
        return -1;
    }

    private void validate(int vertex) {
        if (vertex < 0 || vertex >= map.V())
            throw new IllegalAccessError();
    }

    private void validate(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalAccessError();
        }
        for (int i : vertices) {
            if (i < 0 || i >= map.V()) {
                throw new IllegalAccessError();
            }
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {}
}