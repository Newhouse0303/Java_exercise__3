

import java.util.Scanner;

public class App {   
    public static void main(String[] args) throws Exception {
        GeometricsCalculatorApp.run();    
    }    
}

// establishes an immutable record for the coordinates 

record Point(int x, int y) {}

// class that is called from the main
// includes a constant value to indicate how many Shape objects will be created
// displays user interface to ask user the shape type (triangle, quadrilateral, circle)
// creates required shapes and stores the into an array

class GeometricsCalculatorApp {
    public static int N_OBJECTS = 2;
    
    public static void run() {
        UserInterface.printIntro();
        String shape = UserInterface.enquireShape();
        // Shape is abstract class and the objects on the list are from one of the subclasses
        // the type is defined in the parameter shape as per user's request
        Shape[] objects = ShapeManager.createObjects(shape, N_OBJECTS);
        Calculator.calculateAreaAndBoundaries(objects);
    }    
}

// responsible for calculating the areas of shapes

class Calculator {

    /*
    @param list of Shape objects 
    @precon list not empty
    @postcon prints calculated area & boundaries
    @exeption if list is empty throws ??
    */
     
    static void calculateAreaAndBoundaries(Shape[] objects) {
        double area_sum = getSumOfAreas(objects);
        double boundaries = getcalcBoundaries(objects);
        UserInterface.printResult(area_sum, boundaries);    
    }

    /*
    @param list of Shape objects
    @precon list not empty
    @postcon returns the sum of the areas of all Shape objects on the param list
    @each subclass of Shape has their respective overridden calculateArea method 
    */

    static double getSumOfAreas(Shape[] objects) {
    return Arrays.stream(objects)          
                 .mapToDouble(Shape::calculateArea) 
                 .sum();                    
}
    /*
    static double getSumOfAreas(Shape[] objects) {
        double totalSum = 0;
        for(int i = 0; i < objects.length; i++) {
            totalSum += objects[i].calculateArea();
        }
        return totalSum;
    }
    */

    /*
    @param list of Shape objects 
    @precon list not empty
    @postcon returns the sum of the areas of all Shape objects on the param list
    @each subclass of Shape has their respective overridden calculateBoundaries method 
    */ 
    
    static double getBoundaries(Shape[] objects) {
        return 0.1;
    }
}

class ShapeManager {

    /* 
    * @params string to indicate the shape (triangle, quadrilateral, circle), int to indicate how many
    * @precon string is one of the 3 mentioned above, int not null
    * @postcon return a list of required objects, length = n_oblects
    * @exception if params dont match throws IllegalArgumentException
    */
    
    public static Shape[] createObjects(String shape, int n_objects) {

        Shape[] objects = new Shape[n_objects];
        for(int i = 0; i < n_objects; i++) {
            objects[i] = createShape(shape);
        }
        return objects;
    }

    /*
    * determines the number of Points needed according to the shape
    * creates the required shape and sets the coordinates
    * @params: String to indicate the type of shape
    * @precon: String matches the options 
    * @postcon: creates new object of one of the subclasses of Shape 
            with attributes (int n_Points, and a list Point[] with the coordinates)
    * @exception: if the shape is unknown thrown IllegalArgumentException
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

    /*
    * defines the coordinates through UserInterface 
    * @params: int number of points required
    * @precon: int is greater than 0 and not null
    * @postcon: returns a list Point[] with the required Points 
    * @execption: if int is invalid throws IllegalArgumentException
    */

    public static Point[] setCoordinates(int n) {
        Point[] coords = new Point[n];
        for(int i = 0; i < n - 1; i++) {
            coords[i] = UserInterface.inquirePoint();
            System.out.println(i);
        }
        return coords;
    }    
}

// super class for all the shapes 
// fields for the number of coordinate Points and a list[Point] of the actual coords

abstract class Shape {
    private int n_Points; 
    private Point[] coordinates; 

    public Shape(int n_Points, Point[] coordinates) {
        this.n_Points = n_Points;
        this.coordinates = coordinates;
    }

    public int getN_points() {
        return n_Points;
    }

    // overridden accordingly in subclasses 
    public double calculateArea() {}

    // this would also be overridden accordingly in subclasses but now only returns 1.
    public double calculateBoundaries() {
        return 1;
    }

    
class Circle extends Shape {

    public Circle(int n_Points, Point[] coordinates) {
        super(n_Points, coordinates);    
    }

    /*
    * calculates the area according to the Points in this.coordinates;
    */
    
    @Override
    public double calculateArea() {
        // math here to calculate area of circle
        return 1;
    }
}

class Triangle extends Shape {
    
    public Triangle(int n_Points, Point[] coordinates) {
        super(n_Points, coordinates);
    }

    /*
    * calculates the area according to the Points in this.coordinates;
    */

    @Override
    public double calculateArea() { 
        // math here to calculate area of triangle
        return 11;
    }
}

class Quadrilateral extends Shape {

    public Quadrilateral(int n_Points, Point[] coordinates) {
        super(n_Points, coordinates);
    }

    /*
    * calculates the area according to the Points in this.coordinates;
    */
    
    @Override
    public double calculateArea() {
        // math here to calculate area of quadrilateral
        return 111;
    }

}

// responsible for interacting with user

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

    /*
    * asks user to give values for x and y to return a Point object with said values
    * @precon: input is a number
    * @postcon: returns a Point object with the input values 
    * @exception: if input is not a number throws InvalidInputException 
    */

    public static Point enquirePoint() {
        System.out.print("Enter the x-coordinate of the point : ");
        var x = new Scanner(System.in).nextInt();
        System.out.print("Enter the y-coordinate of the point : ");
        var y = new Scanner(System.in).nextInt();

        return new Point(x,y);
    }

    // prints result

    public static void printResult(double area_sum, double boundaries) {
        System.out.printf("Sum of area covered by the patterns:\n%f\n\n", area_sum);
        //System.out.printf("The common boundaries of the patterns:\n(%d, %d) x (%d, %d)\n\n", boundaries);
    }
}

