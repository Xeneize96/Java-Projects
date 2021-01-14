public class Circle {
    
    private double radius, pi, area;


    // Constructor Method
    public Circle(double r) {

        radius = r;

    }

    // Accessor Method (or Getter) for length
    public double getRadius() {

        return radius;

    }

    // Mutator Method (or Setter) for length
    public void setRadius(double r) {

        radius = r;

    }

    // computeArea Method
    public void computeArea() {

        pi = 3.141592653589793238;
        area = radius * radius * pi;
    }

    // toString Method
    public String toString() {

        String result;
        result = "Circle with radius " + radius + " has area "  + area;
        return result;

    }
}
