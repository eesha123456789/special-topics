import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;

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
        seg=new LineSegment[points.length-1];
        Arrays.sort(points);

        for(int a=0;a<points.length-3;a++){
            for(int b=a+1; a<points.length-2;b++){
                for(int c=b+1;c<points.length-1;c++){
                    for (int d=c+1;d<points.length;d++){
                        if(points[a].slopeTo(points[b]) == points[b].slopeTo(points[c]) && points[b].slopeTo(points[c]) == points[c].slopeTo(points[d])){
                            LineSegment line=new LineSegment(points[a], points[b]);
                            seg[numOfSeg]=line;
                            numOfSeg++;
                            points[a].drawTo(points[d]);
                            StdDraw.show();
                        }
                    }
                }
            }
        }
        
    } 
    public int numberOfSegments(){
        return numOfSeg;
    }        
    public LineSegment[] segments() {
        return seg;
    }          
} 