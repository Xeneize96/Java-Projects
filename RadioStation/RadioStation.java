public class RadioStation {
    
    // object variables
    String call_sign;
    int freq;

    
    // constructor to construct objects
    RadioStation(String sign, int frequency) throws RadioStationException {
        // throw a new exception if length of signal is not equal to 4
        // or if the string is not made up of letters
        // or the frequency is not in range
        // regex [A-Za-z]{4} means that it will check for either A-Z or a-z ("[A-Za-z]")
        // four times ("{4}")
        if (sign.length() != 4 || (!sign.matches("[A-Za-z]{4}")) || frequency < 88 || frequency > 108) {
            throw new RadioStationException(sign, frequency);
        }

        // else assign the values and create the object
        call_sign = sign;
        freq = frequency;
    }
}