
public class RadioStationException extends Exception {
    // empty constructor
    public RadioStationException() {
    }

    // constructor with arguments
    public RadioStationException(String sign, int freq) {
        super("Call signal: \"" + sign + "\" with carrier frequency: " + freq + " is invalid!");
    }

    // constructor that takes other causes for exception
    public RadioStationException(Throwable cause) {
        super(cause);
    }
}