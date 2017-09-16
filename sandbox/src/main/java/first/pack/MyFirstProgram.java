package first.pack;

public class MyFirstProgram{
	public static void main(String[] args){
		System.out.println("Hello, World!");
	}

	public static double distance(Point p1, Point p2){
	    double xDiff = p1.x - p2.x;
        double yDiff = p1.y - p2.y;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}
}