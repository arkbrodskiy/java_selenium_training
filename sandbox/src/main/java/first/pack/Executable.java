package first.pack;

public class Executable {

    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 0;
        p1.y = 0;
        Point p2 = new Point();
        p2.x = 5;
        p2.y = 5;
        System.out.println("Вычисление расстояния между точками по заданным координатам.");
        System.out.println("Координаты первой точки: (" + p1.x + ":" + p1.y + ")");
        System.out.println("Координаты второй точки: (" + p2.x + ":" + p2.y + ")");
        System.out.println("Расстояние между точками: " + Math.round(MyFirstProgram.distance(p1, p2)*100)/100.0);
    }
}
