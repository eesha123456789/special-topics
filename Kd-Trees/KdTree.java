import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {           
    
   private class Node {
		Point2D point;
		Node left;
		Node right;
		boolean bool;
		
		Node(Point2D p) {
			point = p;
			left = null;
			right = null;
		}
	}

   private Node begin;
	private LinkedList<Point2D> list= new LinkedList<Point2D>();
	private int length;

   public KdTree()  {
      begin=null;
      length=0;
   }                             // construct an empty set of points 
   public boolean isEmpty(){
      return begin==null;

   }                      // is the set empty? 
   public int size(){
      return length;
   }                         // number of points in the set 
   public void insert(Point2D p){
      
   }              // add the point to the set (if it is not already in the set)
   
   
   public boolean contains(Point2D p){
      while (begin != null) {
         if (begin.bool) {
             if (p.x() > begin.point.x()) 
               begin = begin.right;
             else if (p.x() < begin.point.x()) 
               begin = begin.left;
             else if (p.y() != begin.point.y()) 
               begin = begin.right;
             else 
               return true;
         }
         else if (!begin.bool) {
             if (p.y() > begin.point.y()) 
               begin = begin.right;
             else if (p.y() < begin.point.y()) 
               begin = begin.left;
             else if (p.x() != begin.point.x()) 
               begin = begin.right;
             else 
               return true;
         }
     }
     return false;
   }            // does the set contain point p? 


   public void draw(){
      if (begin != null) {
         drawNodes(begin);
      }
   }                      
   

   public Iterable<Point2D> range(RectHV rect){
		rangeFunc(begin, rect);
		return list;
   }             // all points that are inside the rectangle (or on the boundary) 
   public Point2D nearest(Point2D p){
      return null;
   }             // a nearest neighbor in the set to point p; null if the set is empty 


   private void drawNodes(Node temp) {
		temp.point.draw();
		
		if(temp.right != null) {
			drawNodes(temp.right);
		}
		
		if(temp.left != null) {
			drawNodes(temp.left);
		}
	}

   private void rangeFunc(Node tempNode, RectHV rect) {
		if (tempNode == null) return;
		
		if(tempNode.bool) {

			if(tempNode.point.x() > rect.xmax()) 
            rangeFunc(tempNode.left, rect);
				
			else if (tempNode.point.x() < rect.xmin()) 
            rangeFunc(tempNode.right, rect);
				
			else {
				rangeFunc(tempNode.left, rect);
				rangeFunc(tempNode.right, rect);
				if(rect.contains(tempNode.point)) {
					list.add(tempNode.point);
				}
			}
		} 
      else {

			if(tempNode.point.y() > rect.ymax())
            rangeFunc(tempNode.left, rect);
				
			else if (tempNode.point.y() < rect.ymin())
            rangeFunc(tempNode.right, rect);
				
			else {
				rangeFunc(tempNode.left, rect);
				rangeFunc(tempNode.right, rect);
				if(rect.contains(tempNode.point)) {
					list.add(tempNode.point);
				}
			}	
		}
	}

   public static void main(String[] args) {}                 // unit testing of the methods (optional) 
}
