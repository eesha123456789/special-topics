/**
 * @author Eesha Konatham attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 * It was hard to code the nearest function because I had to look for all the points that are around the point.
 * I did this by finding the points that are on either side in the x position and then the y position and then compared the distances 
 * However, I belive that find the distance was difficult to find especially because iit wasnt exactly to the left or right of the point
 */
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
	
	private Node start;
	private LinkedList<Point2D> list;
	private Point2D first;
	private Point2D compare;
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
			} 
			else {
				if (p.x() < position.point.x()) {
					if (position.left == null) {
						makeP.bool = false;
						position.left = makeP;
						size++;
						return;
					} 
					else 
						position = position.left;
				} 
				else {
					if (position.right == null) {
						makeP.bool = false;
						position.right = makeP;
						size++;
						return;
					} 
					else
						position = position.right;
				}

				
			}
		}
	}
	
   
	public boolean contains(Point2D p) {
		Node position = start;
		while (position != null) {
			if(p.equals(position.point))
				return true;
			
			if (position.bool) {
				if (p.x() < position.point.x())
					position = position.left;
				else
					position = position.right;
			}
			else {
				if(p.y() < position.point.y())
					position = position.left;
				else
					position = position.right;
			}
		}
		return false;
	}       // does the set contain point p? 

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
	
	public Point2D nearest(Point2D p) {
		first = null;
		compare = p;
		
		nearestFunc(start);
		
		return first;
	}
	
	private void nearestFunc(Node temp) {
		if (temp == null) return;
		if (first == null) 
			first = temp.point;
		else if (compare.distanceSquaredTo(first) > compare.distanceSquaredTo(temp.point))
			first = temp.point;
		
		if (temp.bool) {
			if (compare.distanceSquaredTo(first) > compare.distanceSquaredTo(temp.point)) {
				if(temp.point.x() >= compare.x()) {
					nearestFunc(temp.left);
					nearestFunc(temp.right);
				} 
				else {
					nearestFunc(temp.right);
					nearestFunc(temp.left);
				}
			} 
			else {
				if(temp.point.x() < compare.x())
					nearestFunc(temp.right);
				else if(temp.point.x() > compare.x())
					nearestFunc(temp.left);
				else {
					nearestFunc(temp.left);
					nearestFunc(temp.right);
				}
			}
		} 
		else {
			if (compare.distanceSquaredTo(first) > compare.distanceSquaredTo(temp.point)) {
				if(temp.point.y() >= compare.y()) {
					nearestFunc(temp.left);
					nearestFunc(temp.right);
				} 
				else {
					nearestFunc(temp.right);
					nearestFunc(temp.left);
				}
			} 
			else {
				if(temp.point.y() < compare.y())
					nearestFunc(temp.right);
				else if(temp.point.y() > compare.y())
					nearestFunc(temp.left);
				else {
					nearestFunc(temp.left);
					nearestFunc(temp.right);
				}
			}
		}
	}
	// a nearest neighbor in the set to point p; null if the set is empty 


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
