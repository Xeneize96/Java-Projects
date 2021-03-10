

class WordData {
    // A little class to hold the data for one word.
    String word; // The word (in lower case letters).
    int count; // The number of times the word has been found.

    WordData(String w) {
        // Constructor creates an object with the specified word
        // and with the counter initialized to 1.
        word = w;
        count = 1;
    }
}