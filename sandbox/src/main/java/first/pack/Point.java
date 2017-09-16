package first.pack;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        double xDiff = this.x - p.x;
        double yDiff = this.y - p.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
