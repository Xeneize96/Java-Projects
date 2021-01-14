// Marcos

public class Application {
    public static void main(String[] args) {
        Rectangle rectangleOne = new Rectangle(4.0, 5.0);
        Rectangle rectangleTwo = new Rectangle(5.7, 8.1);
        Circle circleOne = new Circle(3.2);
        Circle circleTwo = new Circle(4.0);

        rectangleOne.setLength(4.0);
        rectangleOne.setWidth(5.0);
        rectangleOne.computeArea();

        rectangleTwo.setLength(5.7);
        rectangleTwo.setWidth(8.1);
        rectangleTwo.computeArea();

        circleOne.setRadius(3.2);
        circleOne.computeArea();

        circleTwo.setRadius(4.0);
        circleTwo.computeArea();


        System.out.println(rectangleOne.toString());
        System.out.println(rectangleTwo.toString());
        System.out.println(circleOne.toString());
        System.out.println(circleTwo.toString());


    }
}
