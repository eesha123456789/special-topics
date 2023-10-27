import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final Point[] gridPoints;
    private int numOfSeg;
    private LineSegment[] seg;

    public BruteCollinearPoints(Point[] points){
        gridPoints=points;
        numOfSeg=0;
        seg=new LineSegment[1];
    } 
    public int numberOfSegments(){
        return numOfSeg;
    }        
    public LineSegment[] segments() {
        return seg;
    }          
} 