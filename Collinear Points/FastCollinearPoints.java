import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int numOfSeg;
    private LineSegment[] seg;
    private Point[] points;

    public FastCollinearPoints(Point[] points){

        if (points==null){
            throw new IllegalArgumentException();
        }
        for(int i=0;i<points.length;i++){
            if(points[i]==null){
                throw new IllegalArgumentException();
            }
            for(int k=0;k<points.length;k++){
                if(i!=k && points[i].compareTo(points[k])==0){
                    throw new IllegalArgumentException();
                }
            }
        }

        points = points.clone();
        seg = new LineSegment[2];
        numOfSeg = 0;
        LinkedList<Point> pts = new LinkedList<Point>();   
        
        
        for (Point point : points) {
            Arrays.sort(points, point.slopeOrder());          
            double a = 0.0;
            for (int i = 0; i < this.points.length; i++) {
                double b = point.slopeTo(this.points[i]);
                    if(i == 0 || b != a) {
                        
                        if(pts.size() >= 3) {
                            //Collections.sort(collinearPoints);
                            enqueue(new LineSegment(pts.getFirst(), pts.getLast()));                
                            pts.getFirst().drawTo(pts.getLast());    
                            StdDraw.show();   
                        }
                        
                        pts.clear();
                    } 
                    
                    pts.add(this.points[i]);
                    a = b; 
                }
            }
            
        }   


    public int numberOfSegments() {
        return numOfSeg;
    }    
    public LineSegment[] segments(){
        return Arrays.copyOf(seg, numOfSeg);
    }

    private void enqueue(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if(numOfSeg == seg.length) {
            LineSegment[] z = new LineSegment[2 * seg.length];
            System.arraycopy(seg, 0, z, 0, numOfSeg);
            seg = z;
        }
        
        seg[numOfSeg++] = item;
    }    

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}