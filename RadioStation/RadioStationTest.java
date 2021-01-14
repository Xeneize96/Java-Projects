import java.util.Scanner;

public class RadioStationTest {
    public static void main(String[] args) {
        String sign;
        int freq;
        Scanner sc = new Scanner(System.in);

        // creating 6 objects
        for (int i = 0; i < 6; i++) {
            System.out.print("\nEnter Call Signal: ");
            sign = sc.next();

            System.out.print("\nEnter Frequency: ");
            freq = sc.nextInt();

            // try to create a object
            try {
                RadioStation obj = new RadioStation(sign, freq);
                // print if object creation was successful
                // will not execute the following if exception occurs
                System.out.println("Object creation successful!\n");
            }
            // catch the exception
            catch (RadioStationException e) {
                // print the message of the exception
                System.out.println(e.getMessage());
            }
        }
    }
}