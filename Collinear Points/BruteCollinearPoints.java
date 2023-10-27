import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int numOfSeg;
    private LineSegment[] seg;

    public BruteCollinearPoints(Point[] points){
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

        numOfSeg=0;
        seg=new LineSegment[points.length];
        for(int a=0;a<points.length;a++){
            for(int b=a+1; a<points.length;b++){
                for(int c=a+2;c<points.length;c++){
                    for (int d=a+3;d<points.length;d++){
                        if(points[a].slopeTo(points[b]) == points[b].slopeTo(points[c]) && points[b].slopeTo(points[c]) == points[c].slopeTo(points[d])){
                            LineSegment line=new LineSegment(points[a], points[b]);
                            if(Arrays.binarySearch(seg,line)<0){
                                seg[numOfSeg]=line;
                                numOfSeg++;
                            }
                        }
                    }
                }
            }
        }
        
    } 
    public int numberOfSegments(){
        return seg.length;
    }        
    public LineSegment[] segments() {
        return seg;
    }          
} 