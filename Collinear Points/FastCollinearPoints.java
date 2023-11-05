import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int numOfSeg;
    private LineSegment[] seg;
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
        seg = new LineSegment[points.length];
        numOfSeg = 0;
        Point[] pts = new Point[points.length];   
        
        for (Point point : points) {
            Arrays.sort(points, point.slopeOrder());          
            double prevSlope = 0.0;
            
            for (int j = 0; j < points.length; j++) {
                double currentSlope = point.slopeTo(points[j]);
                if(j == 0 || currentSlope != prevSlope) {
                    
                    if(pts.length >= 3) {
                        seg[numOfSeg]=new LineSegment(pts[0], pts[pts.length-1]);
                        pts[0].drawTo(pts[pts.length-1]);    
                        StdDraw.show();   
                    }
                    for(int k=0;k<pts.length;k++){
                        pts[k]=null;
                    }
                } 
                prevSlope = currentSlope;
                for(int k=0;k<pts.length;k++){
                    if(pts[k]!=null){
                        pts[k]=pts[j];
                        break;
                    }
                } 
                
            }
        }
        
    }   


    public int numberOfSegments() {
        return numOfSeg;
    }    
    public LineSegment[] segments(){
        return seg;
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