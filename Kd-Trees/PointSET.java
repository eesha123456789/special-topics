import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;

public class PointSET {
   private SET<Point2D> set;
   public PointSET(){                               // construct an empty set of points {
      set = new SET<Point2D>();
   }
   public boolean isEmpty(){                      // is the set empty? 
      return set.isEmpty();
   }
   public int size(){                       // number of points in the set 
      return set.size();
   }
   public void insert(Point2D p){              // add the point to the set (if it is not already in the set)
      if(!set.contains(p))
         set.add(p);
   }
   public boolean contains(Point2D p){            // does the set contain point p? 
      return set.contains(p);
   }
   public void draw(){
      for (Point2D i : set)
            i.draw();
   }                         // draw all points to standard draw 
   public Iterable<Point2D> range(RectHV rect){             // all points that are inside the rectangle (or on the boundary) 
      Queue<Point2D> fin = new Queue<Point2D>();
      for(Point2D i : set){
         if(i.x()<=rect.xmax() && i.x()>=rect.xmin() && i.y()<=rect.ymax() && i.y()>=rect.ymin()){
            fin.enqueue(i);
         }
      }

      return fin;
   }
   public Point2D nearest(Point2D p) {
      Point2D fin = null;
      double distance = 0;
      for(Point2D i: set){
         if(i.distanceTo(p)>distance){
            distance = i.distanceTo(p);
            fin=i;
         }
      }
      return fin;
   }            // a nearest neighbor in the set to point p; null if the set is empty 

   //public static void main(String[] args)                  // unit testing of the methods (optional) 
}
