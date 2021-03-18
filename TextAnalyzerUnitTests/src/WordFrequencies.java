
import java.io.*;
import java.io.FileNotFoundException;

public class WordFrequencies {

    static WordData[] words; // Created an array to hold the words from the file.

    static int wordCount = 0; // Created an int for the number of words currently stored in
    // the array.

    public String countFrequency(String inputFileName, String outputFileName) {
        TextReader in; // Used for reading from the input file.
        PrintWriter out; // Used for writing to the output file.

        words = new WordData[10]; // Start with space for 10 words.
        wordCount = 0; // 0, meaning there are no words in the array currently.

        /*
         * Gets the input file name from the user. If there's an exception,
         * the output prints a message and terminates the program.
         */
        try {
            in = new TextReader(new FileReader(inputFileName));
        } catch (FileNotFoundException e) {
            return "Can't find file \"" + inputFileName + "\".";
        }

        /*
         * Get the output file name from the user and try to create the output stream.
         * If there is an IOException, print a message and terminate the program.
         */
        try {
            out = new PrintWriter(new FileWriter(outputFileName));
        } catch (IOException e) {
            return "Can't open file \"" + outputFileName + "\" for output.";
        }

        /*
         * Read all the words from the input stream and insert them into the array of
         * words. Reading from a TextReader can result in an error of type
         * TextReader.Error. If one occurs, print an error message and terminate the
         * program.
         */
        try {
            while (true) {

                // Skip past and non-letters in the input stream. If an
                // end-of-stream has been reached, end the loop. Otherwise,
                // read a word and insert it into the array of words.
                while (!in.eof() && !Character.isLetter(in.peek())) {
                    in.getAnyChar();
                }
                if (in.eof()) {
                    break;
                }
                insertWord(in.getAlpha());
            }
        } catch (TextReader.Error e) {
            TextIO.putln("An error occurred while reading from the input file.");
            TextIO.putln(e.toString());
            return "An error occurred while reading from the input file.";
        }

        /*
         * Sort the list of words according the frequency with which they were found in
         * the file, and then output the list again.
         */
        sortByFrequency();

        out.println();
        out.println("The most frequent words:");
        out.println("---------------------------");
        out.println();

        putWordList(out);

        if (out.checkError()) {
            return "An error occurred while scanning to the output file.\n"
                    + "The output might be missing, incomplete, or corrupted.";
        } else {
            return "Scanning Complete. Added text file to current folder.";
        }
    } // end of main()

    public void insertWord(String w) {
        // Insert the word w into the array of words, unless it already
        // appears there. If the word already appears in the list,
        // add 1 to the counter for that word. The words in the array are in
        // lower case, and w is converted to lower case before it is processed.
        // Note that the words in the array are kept in alphabetical order.
        // If the array has no more space to hold w, then it is doubled
        // in size.

        int pos = 0; // This will be the position in the array where w belongs.

        w = w.toLowerCase();

        if (words == null) {
            words = new WordData[1];
        }

        /*
         * Find the position in the array where w belongs, after all the words that
         * precede w alphabetically. If a copy of w already occupies that position, then
         * it is not necessary to insert w, so just add 1 to the counter associated with
         * the word and return.
         */
        while (pos < wordCount && words[pos].word.compareTo(w) < 0) {
            pos++;
        }
        if (pos < wordCount && words[pos].word.equals(w)) {
            words[pos].count++;
            return;
        }

        /*
         * If the array is full, make a new array that is twice as big, copy all the
         * words from the old array to the new, and set the variable, words, to refer to
         * the new array.
         */
        if (wordCount == words.length) {
            WordData[] newWords = new WordData[words.length * 2];
            System.arraycopy(words, 0, newWords, 0, wordCount);
            words = newWords;
        }

        /*
         * Put w into its correct position in the array. Move any words that come after
         * w up one space in the array to make room for w. Create a new WordData object
         * to hold the new word.
         */
        for (int i = wordCount; i > pos; i--) {
            words[i] = words[i - 1];
        }
        words[pos] = new WordData(w);
        wordCount++;

    } // end insertWord()

    static void putWordList(PrintWriter output) {
        // Write the list of words from the words array, along with their
        // associated frequencies, to the output stream specified in the
        // parameter to this subroutine. Words are output in a column
        // that is 15 spaces wide. If an individual word is longer than
        // 15 spaces, the output won't be quite so neat.
        for (int i = 0; i < wordCount; i++) {
            output.print("   ");
            output.print(words[i].word);
            for (int space = words[i].word.length(); space < 15; space++) {
                output.print(" ");
            }
            output.print(" ");
            output.println(words[i].count);
        }
    } // end putWordList()

    public static WordData getWord(String wo) {
        for (WordData temp : words) {
            if (wo.toLowerCase().compareTo(temp.word) == 0) {
                return temp;
            }
        }
        return null;
    }

    static void sortByFrequency() {

        // Use insertion sort to sort the words in the list so that they
        // are in order of decreasing frequency. (Note that insertion sort
        // has the following neat property: In a group of words that all
        // have the same frequency, the words will remain in alphabetical
        // order after the words have been sorted by frequency.)
        for (int i = 1; i < wordCount; i++) {
            WordData temp = words[i]; // Save the word in position i.
            int location; // An index in the array.
            location = i - 1;
            while (location >= 0 && words[location].count < temp.count) {
                // Bump words that have frequencies less than the frequency
                // of temp up one space in the array.
                words[location + 1] = words[location];
                location--;
            }
            words[location + 1] = temp; // Put temp in last vacated space.

        }
    } // end sortByFrequency()

} // end class WordFrequencies
