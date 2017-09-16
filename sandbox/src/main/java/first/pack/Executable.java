package first.pack;

public class Executable {

    public static void main(String[] args) {
        Point p1 = new Point(-5, 5);
        Point p2 = new Point(5, 5);
        distanceOutput(p1, p2);
        Point p3 = new Point(0, 0);
        Point p4 = new Point(3.5, 3.5);
        distanceOutput(p3, p4);
        Point p5 = new Point(-8.2, -3.6);
        Point p6 = new Point(5.7, -2.4);
        distanceOutput(p5, p6);
    }

    public static void distanceOutput(Point p1, Point p2){
        System.out.println("Вычисление расстояния между точками по заданным координатам.");
        System.out.println("Координаты первой точки: (" + p1.x + ":" + p1.y + ")");
        System.out.println("Координаты второй точки: (" + p2.x + ":" + p2.y + ")");
        System.out.println("Расстояние между точками: " + Math.round(p1.distance(p2)*100)/100.0);
    }
}
