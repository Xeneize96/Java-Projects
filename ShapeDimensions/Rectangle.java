// Marcos

public class Rectangle {
   
    private double length, width, area;
    

    // Constructor Method
    public Rectangle(double l, double w) {

        length = l;
        width = w;

    }

    // Accessor Method (or Getter) for length
    public double getLength() {
        
        return length;

    }

    // Mutator Method (or Setter) for length
    public void setLength(double l) {
        
        length = l;

    }

    // Accesor Method (or Getter) for width
    public double getWidth() {
        
        return width;

    }

    // Mutator Method (or Setter) for width
    public void setWidth(double w) {
        
        width = w;

    }

    // computeArea Method
    public void computeArea() {

        area = length * width;
    }

    // toString Method
    public String toString() {
       
        String result;
        result = "Rectangle with sides " + length + " and " + width + " has area " + area;
        return result;

    }
}