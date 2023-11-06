import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    private Point[] points;
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

        points = points.clone();
        seg = new LineSegment[2];
        numOfSeg= 0;
        Arrays.sort(this.points);

        for(int a=0;a<points.length-3;a++){
            for(int b=a+1; a<points.length-2;b++){
                for(int c=b+1;c<points.length-1;c++){
                    for (int d=c+1;d<points.length;d++){
                        if(points[a].slopeTo(points[b]) == points[b].slopeTo(points[c]) && points[b].slopeTo(points[c]) == points[c].slopeTo(points[d])){
                            enqueue(new LineSegment(this.points[a], this.points[d]));

                            this.points[a].drawTo(this.points[d]);
                            StdDraw.show();   
                        }
                    }
                }
            }
        }
        
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
    public int numberOfSegments(){
        return numOfSeg;
    }        
    public LineSegment[] segments() {
        return seg;
    }          
} 