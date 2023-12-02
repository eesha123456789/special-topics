
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;

public class BruteCollinearPoints {
    
    private LineSegment seg[];
    private int numOfSeg;
    

    public BruteCollinearPoints(Point[] points) {
        
        // check to see if argument matches constraints
        checksPoints(points);
        
        points = points.clone();
        seg = new LineSegment[2];
        numOfSeg = 0;
        
        // sort array
        Arrays.sort(points);
        
        for (int a = 0; a < points.length - 3; a++) {            
            for (int b = a + 1; b < points.length - 2; b++) {
                for (int c = b + 1; c < points.length - 1; c++) {
                    for (int d = c + 1; d < points.length; d++) {
                        if(points[a].slopeTo(points[b]) == points[b].slopeTo(points[c]) &&
                            points[b].slopeTo(points[c]) == points[c].slopeTo(points[d])) {
                            enqueue(new LineSegment(points[a], points[d]));
                            points[a].drawTo(points[d]);
                            StdDraw.show();                           
                        }
                    }
                }
            }
        }
    }
    

    private void resize(int capacity) {
        assert capacity >= numOfSeg;

        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(seg, 0, temp, 0, numOfSeg);
        seg = temp;

    }
    
    private void enqueue(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if(numOfSeg == seg.length) {
            resize(2 * seg.length);
        }
        seg[numOfSeg++] = item;
    }
    
    private void checksPoints(Point[] points){
        if(points == null) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < points.length; i ++) {
            for(int j = 0; j < points.length; j++) {
                
                if(points[i] == null || points[j] == null) {
                    throw new IllegalArgumentException();
                }
                
                if(i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return numOfSeg;
    }    
    
    public LineSegment[] segments() {  
        return Arrays.copyOf(seg, numOfSeg);
    }      
    
    public static void main(String[] args) {
        In in = new In("files\\rs1423.txt");      // input file
        int n = in.readInt();   
        
        // padding for drawing
        int padding = 1000;
        
        // set draw scale
        StdDraw.setXscale(-padding, Short.MAX_VALUE + padding);
        StdDraw.setYscale(-padding, Short.MAX_VALUE + padding);
               
        // Index of array
        int index = 0;
        
        // turn on animation mode
        StdDraw.enableDoubleBuffering();
 
        // Create array
        Point[] points = new Point[n];
            
        points[index] = new Point(in.readInt(), in.readInt());
        points[index].draw();
        StdDraw.show();
        
        index++;
        
        while (!in.isEmpty()) {        
            points[index] = new Point(in.readInt(), in.readInt());
            points[index].draw();
            StdDraw.show();
                       
            index++;
        }
        
        BruteCollinearPoints bfcp = new BruteCollinearPoints(points);       
        //LineSegment[] lines = bfcp.segments();
    }
}