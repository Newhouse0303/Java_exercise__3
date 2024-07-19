
import java.util.Scanner;

public class App {   
    public static void main(String[] args) throws Exception {
        AreaCalculatorApp.run();    
    }    
}

record Point(int x, int y) {}

class AreaCalculatorApp {
    public static int N_OBJECTS = 2;
    
    public static void run() {
        UserInterface.printIntro();
        String shape = UserInterface.enquireShape();
        Shape[] objects = ShapeManager.createObjects(shape, N_OBJECTS);
        Calculator.count(objects);
    }    
}

class Calculator {
    static void count(Shape[] objects) {
        double area_sum = sumOfAreas(objects);
        double boundaries = boundaries(objects);
        UserInterface.printResult(area_sum, boundaries);    
    }

    static double sumOfAreas(Shape[] objects) {
        double totalSum = 0;
        for(int i = 0; i < objects.length; i++) {
            totalSum += objects[i].calculateArea();
        }
        return totalSum;
    }

    static double boundaries(Shape[] objects) {
        return 0.1;
    }
}

class ShapeManager {

    public static Shape[] createObjects(String shape, int n_objects) {

        Shape[] objects = new Shape[n_objects];
        for(int i = 0; i < n_objects; i++) {
            objects[i] = createShape(shape);
        }
        return objects;
    }

    /*
     * determines the number of Points needed accordin to the shape
     */

    public static Shape createShape(String shape) {
        if (shape.equals("circle")) {
            return new Circle(2, setCoordinates(2));
        } else if (shape.equals("triangle")) {
            return new Triangle(3, setCoordinates(3));
        } else if (shape.equals("quadrilateral")) {
            return new Quadrilateral(4, setCoordinates(4));
        } else {
            throw new IllegalArgumentException("unknown shape");
        }
    }

    public static Point[] setCoordinates(int n) {
        Point[] coords = new Point[n];
        for(int i = 0; i < n - 1; i++) {
            coords[i] = UserInterface.inquirePoint();
            System.out.println(i);
        }
        return coords;
    }    
}

class Shape {
    private int n_Points; 
    private Point[] coordinates; 

    public Shape(int n_Points, Point[] coordinates) {
        this.n_Points = n_Points;
        this.coordinates = coordinates;
    }

    public int getN_points() {
        return n_Points;
    }

    public double calculateArea() {
        return 1;
    }
}

class Circle extends Shape {

    public Circle(int n_Points, Point[] coordinates) {
        super(n_Points, coordinates);
        
    }
    @Override
    public double calculateArea() {
        return 11;
    }
}

class Triangle extends Shape {
    
    public Triangle(int n_Points, Point[] coordinates) {
        super(n_Points, coordinates);
    }

    @Override
    public double calculateArea() {    
        return 111;
    }
}

class Quadrilateral extends Shape {

    public Quadrilateral(int n_Points, Point[] coordinates) {
        super(n_Points, coordinates);
    }
    @Override
    public double calculateArea() {
        return 1111;
    }

}

class UserInterface {

    public static void printIntro() {
        System.out.println("Exercise 3");
        System.out.println("A circle is defined by a centre and a perimeter point, the others by corner points. ");
        System.out.print("Enter the pattern type (triangle, quadrilateral, circle): ");
    }

    public static String enquireShape() {
        String shape = new Scanner(System.in).next();
        return shape; 
    }

    public static Point enquirePoint() {
        System.out.print("Enter the x-coordinate of the point : ");
        var x = new Scanner(System.in).nextInt();
        System.out.print("Enter the y-coordinate of the point : ");
        var y = new Scanner(System.in).nextInt();

        return new Point(x,y);
    }

    public static void printResult(double area_sum, double boundaries) {
        System.out.printf("Sum of area covered by the patterns:\n%f\n\n", area_sum);
        //System.out.printf("The common boundaries of the patterns:\n(%d, %d) x (%d, %d)\n\n", boundaries);
    }
}

