public class LineSegment {
    private Point p;
    private Point q;
    public LineSegment(Point p1, Point q1){
        if (p == null || q == null) {
            throw new IllegalArgumentException("argument to LineSegment constructor is null");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("both arguments to LineSegment constructor are the same point: " + p);
        }
        p=p1;
        q=q1;
    }       // constructs the line segment between points p and q
    public   void draw(){
        p.drawTo(q);
    }                        // draws this line segment
    public String toString() {
        return p + " -> " + q;
    }
}