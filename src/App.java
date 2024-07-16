
import java.util.Scanner;



public class App {

    //String availableShapes = "triangle, quadrilateral, circle";

    public static void printIntro() {
        System.out.println("Exercise 3");
        System.out.println("A circle is defined by a centre and a perimeter point, the others by corner points. ");
    }

    public static class Shape {
        private String type; //triangle, quadrilateral, circle

        public Shape(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    class Triangle extends Shape {
        public Triangle() {
            super("triangle");
        }
    }
        
    public static Shape getShape() {
        System.out.print("Enter the pattern type (triangle, quadrilateral, circle): ");
        String shape = new Scanner(System.in).next();
        Shape s = new Shape(shape);
        return s;
    }

    record Point(int x, int y) {                // establishes coordinates
    }

    static String readType() {          // user input to define shape 
        System.out.print("Enter the pattern type (triangle, quadrilateral, circle): ");
        return new Scanner(System.in).next();
    }

    static Point readPoint() {          // asks for coordinates 
        System.out.print("Enter the x-coordinate of the point:");
        var x = new Scanner(System.in).nextInt();
        System.out.print("Enter the y-coordinate of the point: ");
        var y = new Scanner(System.in).nextInt();

        return new Point(x, y);
    }

    static Point[] boundaries(Object[] ps) {
        int x1 = Integer.MIN_VALUE,
                y1 = Integer.MIN_VALUE,
                x2 = Integer.MAX_VALUE,
                y2 = Integer.MAX_VALUE;

        for (int i = 1; i < ps.length - (ps[0].equals("triangle") ? 0 : 1); i++) {
            x1 = Math.max(x1, ((Point) ps[i]).x);
            y1 = Math.max(y1, ((Point) ps[i]).y);
            x2 = Math.min(x2, ((Point) ps[i]).x);
            y2 = Math.min(y2, ((Point) ps[i]).y);
        }

        assert (x1 >= x2);
        assert (y1 >= y2);

        return new Point[]{new Point(x1, y1), new Point(x2, y2)};
    }

    static double area(Object[] ps) throws Exception {
        int x1 = Integer.MIN_VALUE,
                y1 = Integer.MIN_VALUE,
                x2 = Integer.MAX_VALUE,
                y2 = Integer.MAX_VALUE;

        for (int i = 1; i < ps.length - (ps[0].equals("triangle") ? 0 : 1); i++) {
            x1 = Math.max(x1, ((Point) ps[i]).x);
            y1 = Math.max(y1, ((Point) ps[i]).y);
            x2 = Math.min(x2, ((Point) ps[i]).x);
            y2 = Math.min(y2, ((Point) ps[i]).y);
        }

        assert (x1 >= x2);
        assert (y1 >= y2);

        // triangle: draw a quadrilateral around it and calculate half of the area
        // quadrilateral: ala = side * other side
        // circle: area = pi * r^2 ja r = sqrt(dx^2 + dy^2), so area = pi * (dx^2 + dy^2)
        return switch ((String) ps[0]) {
            case "triangle" -> (x1 - x2) * (y1 - y2) / 2;
            case "quadrilateral" -> (x1 - x2) * (y1 - y2);
            case "circle" -> Math.PI * ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            default -> throw new Exception("Unfamiliar pattern!");
        };
    }

    public static void main() throws Exception {

        printIntro();
        Shape shape1 = getShape();
        Shape shape2 = getShape();
        
        //createObject(shape);



        Object[] p1 = new Object[4];        // creates an array p1 (length 4)
        p1[0] = readType();                 // determines shape
        p1[1] = readPoint();                // x
        p1[2] = readPoint();                // y
        if (p1[0].equals("triangle"))   // if triangle asks one more point
            p1[3] = readPoint();

        Object[] p2 = new Object[4];
        p2[0] = readType();
        p2[1] = readPoint();
        p2[2] = readPoint();
        if (p2[0].equals("triangle"))
            p2[3] = readPoint();

        // Calculate the area covered by the patterns
        // No need to take into account the overlapping of patterns

        {
            double area_sum = 0;
            area_sum += area(p1);
            area_sum += area(p2);

            System.out.printf("Sum of area covered by the patterns:\n%f\n\n", area_sum);
        }

        // calculate the outer boundaries of the area covered by the patterns

        int x1 = Integer.MIN_VALUE,
                y1 = Integer.MIN_VALUE,
                x2 = Integer.MAX_VALUE,
                y2 = Integer.MAX_VALUE;

        {
            Point[] b1 = boundaries(p1);

            x1 = Math.max(x1, b1[0].x);
            y1 = Math.max(y1, b1[0].y);
            x2 = Math.min(x2, b1[1].x);
            y2 = Math.min(y2, b1[1].y);
        }

        {
            Point[] b2 = boundaries(p2);

            x1 = Math.max(x1, b2[0].x);
            y1 = Math.max(y1, b2[0].y);
            x2 = Math.min(x2, b2[1].x);
            y2 = Math.min(y2, b2[1].y);
        }

        assert (x1 >= x2);
        assert (y1 >= y2);

        System.out.printf("The common boundaries of the patterns:\n(%d, %d) x (%d, %d)\n\n",
                x2, y2, x1, y1);
    }

    
}

