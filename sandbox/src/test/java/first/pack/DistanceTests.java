package first.pack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void testDistance1(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Assert.assertEquals(p1.distance(p2), 5.0);
 }

    @Test
    public void testDistance2(){
        Point p1 = new Point(-3.3, -4.8);
        Point p2 = new Point(-7.3, -7.8);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistance3(){
        Point p1 = new Point(-3.7, 2.7);
        Point p2 = new Point(5.8, 2.7);
        Assert.assertEquals(p1.distance(p2), 9.5);
    }

    @Test
    public void testDistance4(){
        Point p1 = new Point(-8.6, 3.5);
        Point p2 = new Point(5.9, 5.7);
        Assert.assertEquals(p1.distance(p2), 14.67, 0.0049);
    }

    @Test
    public void testDistance5(){
        Point p1 = new Point(0.4, 0.8);
        Point p2 = new Point(3.5, 3.5);
        Assert.assertEquals(p1.distance(p2), 4.11, 0.0049);
    }

}
