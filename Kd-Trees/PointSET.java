import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private SET<Point2D> points;

    public PointSET() {  // construct an empty set of points 
        points = new SET<Point2D>();
    }     
    
    
    public boolean isEmpty() { // is the set empty?   
        return points.isEmpty();
    }            
    
    public int size() {     // number of points in the set 
        return points.size();
    }   
    
    
    public void insert(Point2D p) {     // add the point to the set (if it is not already in the set)
        if (p==null) 
            throw new IllegalArgumentException();

        if (!points.contains(p)) {
            points.add(p);
        }   
    }       
    
     
    public boolean contains(Point2D p) {       // does the set contain point p?
        if (p == null)
            throw new IllegalArgumentException();
        
        return points.contains(p);
    }   
    
    public void draw() {       // draw all points to standard draw 
        for (Point2D i : points)
            i.draw();
    } 
    
     // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect) { 
        if (rect==null) 
            throw new IllegalArgumentException();
        
        Stack<Point2D> list = new Stack<Point2D>();
        for (Point2D p : points) {
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax() && p.y() >= rect.ymin() && p.y() <= rect.ymax()) {
                list.push(p);
            }
        }
        return list;
    }  
    
     
    public Point2D nearest(Point2D p) {     // a nearest neighbor in the set to point p; null if the set is empty
        if (p==null) 
            throw new IllegalArgumentException();
        if (points.isEmpty())
            return null;
        

        double dist = 0xffff;
        Point2D close = null;
        for (Point2D temp : points) {
            if (temp.distanceSquaredTo(p) < dist) {
                dist = temp.distanceSquaredTo(p);
                close = temp;
            }
        }
        return close;
    }             
 
    // unit testing of the methods (optional) 
    public static void main(String[] args)  { }                
 }