import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points)
    {
        checkForNullPoints(points);
        Point[] one = Arrays.copyOf(points, points.length);
        Point[] two = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        Arrays.sort(two);
        checkForDuplicatedPoints(two);
        for (int i = 0; i < two.length; ++i)
        {
            Point origin = two[i];
            Arrays.sort(one);
            Arrays.sort(one, origin.slopeOrder());
            int count = 1;
            Point lineBeginning = null;
            for (int j = 0; j < one.length - 1; ++j)
            {
                if (one[j].slopeTo(origin) == one[j + 1].slopeTo(origin))
                {
                    count++;
                    if (count == 2)
                    {
                        lineBeginning = one[j];
                        count++;
                    }
                    else if (count >= 4 && j + 1 == one.length - 1)
                    {
                        if (lineBeginning.compareTo(origin) > 0)
                        {
                            segmentsList.add(new LineSegment(origin, one[j + 1]));
                        }
                        count = 1;
                    }
                }
                else if (count >= 4)
                {
                    if (lineBeginning.compareTo(origin) > 0)
                    {
                        segmentsList.add(new LineSegment(origin, one[j]));
                    }
                    count = 1;
                }
                else
                {
                    count = 1;
                }
            }
        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }

    public int numberOfSegments()
    {
        return segments.length;
    }

    public LineSegment[] segments()
    {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    private void checkForDuplicatedPoints(Point[] points)
    {
        for (int i = 0; i < points.length - 1; ++i)
        {
            if (points[i].compareTo(points[i+1]) == 0)
            {
                throw new java.lang.IllegalArgumentException("Duplicated points");
            }
        }
    }

    private void checkForNullPoints(Point[] points)
    {
        for (int i = 0; i < points.length; ++i)
        {
            if (points[i] == null)
            {
                throw new java.lang.NullPointerException("At least one point in array is null");
            }
        }
    }
}