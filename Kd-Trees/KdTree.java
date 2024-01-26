import java.util.LinkedList;
import java.util.List;

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
	
	private Node start;
	private LinkedList<Point2D> list;
	private Point2D champion;
	private Point2D comparison;
	private int size;
	

	public KdTree() {
		start = null;
	} //Constructor, creates a SET of points
	
	
	public boolean isEmpty() {
		return size() == 0;
	} //Returns true when there are no points in the set
	
	
	public int size() {
		if (start == null) {
			return 0;
		} else {
			return getCount(start);
		}
	} //Returns the number of points in the set 
	
	private int getCount(Node node) {
		return size;
	}
	
	
	public void insert(Point2D p) {
		Node makeP = new Node(p);
		Node position = start;
		
		if(start == null) {
			makeP.bool = true;
			start = makeP;
			size++;
			return;
		}
		
		while(true) {
			if(p.equals(position.point)) {
				return;
			}
			
			if (!position.bool) {
				if (p.y() < position.point.y()) {
					if (position.left == null) {
						makeP.bool = true;
						position.left = makeP;
						size++;
						return;
					} else {
						position = position.left;
					}
				} else {
					if (position.right == null) {
						makeP.bool = true;
						position.right = makeP;
						size++;
						return;
					} else {
						position = position.right;
					}
				}
			} else {
				if (p.x() < position.point.x()) {
					if (position.left == null) {
						makeP.bool = false;
						position.left = makeP;
						size++;
						return;
					} else {
						position = position.left;
					}
				} else {
					if (position.right == null) {
						makeP.bool = false;
						position.right = makeP;
						size++;
						return;
					} else {
						position = position.right;
					}
				}

				
			}
		}
	}
	
   
   public boolean contains(Point2D p){
      while (start != null) {
         if (start.bool) {
             if (p.x() > start.point.x()) 
               start = start.right;
             else if (p.x() < start.point.x()) 
               start = start.left;
             else if (p.y() != start.point.y()) 
               start = start.right;
             else 
               return true;
         }
         else if (!start.bool) {
             if (p.y() > start.point.y()) 
               start = start.right;
             else if (p.y() < start.point.y()) 
               start = start.left;
             else if (p.x() != start.point.x()) 
               start = start.right;
             else 
               return true;
         }
     }
     return false;
   }            // does the set contain point p? 


   public void draw(){
      if (start != null) {
         draw(start);
      }
   }                      
   

   public Iterable<Point2D> range(RectHV rect) {
	list = new LinkedList<Point2D>();
	rangeFunc(start, rect);
	return list;
	}

	private void rangeFunc(Node node, RectHV rect) {
		if (node == null) return;
		
		if(node.bool) {
			if(node.point.x() > rect.xmax()) 
				rangeFunc(node.left, rect);
				
			else if (node.point.x() < rect.xmin()) 
				rangeFunc(node.right, rect);
				
			else {
				rangeFunc(node.left, rect);
				rangeFunc(node.right, rect);
				
				if(rect.contains(node.point)) {
					list.add(node.point);
				}
			}
			
		} else {
			if(node.point.y() > rect.ymax()) 
				rangeFunc(node.left, rect);
				
			else if (node.point.y() < rect.ymin()) 
				rangeFunc(node.right, rect);
				
			else {
				rangeFunc(node.left, rect);
				rangeFunc(node.right, rect);
				
				if(rect.contains(node.point)) {
					list.add(node.point);
				}
			}	
		}
	}       // all points that are inside the rectangle (or on the boundary) 
	public Point2D nearest(Point2D p){
		return null;
	}             // a nearest neighbor in the set to point p; null if the set is empty 


   private void draw(Node temp) {
		temp.point.draw();
		
		if(temp.right != null) {
			draw(temp.right);
		}
		
		if(temp.left != null) {
			draw(temp.left);
		}
	}




   public static void main(String[] args) {}                 // unit testing of the methods (optional) 
}
