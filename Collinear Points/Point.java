import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    public Point(int x1, int y1){                       // constructs the point (x, y)
        x=x1;
        y=y1;
    } 
    public void draw()   {
        StdDraw.point(x, y);
    }                          
    public   void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }                  
    public String toString(){
        return "(" + x + ", " + y + ")";
    }                          

    public int compareTo(Point that){
        if(x==that.x && y==that.y){
            return 0;
        }
        else if(y < that.y || (y==that.y && x<that.x)){
            return -1;
        }
        else
            return 1;

    }     // eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
   
    public  double slopeTo(Point that){
        if(x==that.x && y==that.y){
            return Double.NEGATIVE_INFINITY;
        }
        else if(x==that.x){
            return Double.POSITIVE_INFINITY;
        }
        else if(y==that.y){
            return +0.0;
        }
        return (double) (that.y-y)/(that.x-x);
    }    

    //public Comparator<Point> slopeOrder(){}              // compare two points by slopes they make with this point
    


}
