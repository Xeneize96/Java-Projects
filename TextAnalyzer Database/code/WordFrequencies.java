
import java.io.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
* This class is performing the main functions
* It will read count the words.
* It will display the outputs in file
* Its methods will be called from GUI main method
* 
*/

public class WordFrequencies {
    /**
	* This is the array of words. Each element will have a word and its occurrence count
	* Created an array to hold the words from the file.
	*/

    static WordData[] words;  
        
    /**
	* This method is getting the words from the file and adding them in database
	* and then it gets the words from the database again and store in array
	* and then sort the array and finally print the words in file and empty the database
	* @param inputFileName is the name of the input file from which the words to be read
	* @param outputFileName is the name of the output file in which the data to be stored as a result
	* @return It will return a string that will contain the success or error message
	*/
    WordFrequencies(){
    	words=new WordData[10];
    }
    public String countFrequency(String inputFileName, String outputFileName) {
        TextReader in; // Used for reading from the input file.
        PrintWriter out; // Used for writing to the output file.

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
        System.out.println("Inserting words in database. Please wait... ");
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
                try {
                	insertWord(in.getAlpha());
                }catch(SQLException ex) {
                	return ex.toString();
                }
            }
        } catch (TextReader.Error e) {
            TextIO.putln("An error occurred while reading from the input file.");
            TextIO.putln(e.toString());
            return "An error occurred while reading from the input file.";
        }
        DbHandler db=new DbHandler();
        words = db.getAllWords();
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
        db.deleteAllWords();
        db.close();
        
        if (out.checkError()) {
            return "An error occurred while scanning to the output file.\n"
                    + "The output might be missing, incomplete, or corrupted.";
        } else {
            return "Scanning Complete. Added text file to current folder.";
        }
    } // end of main()
    /**
	*   This method is adding the word in the database and the
	*   database function will take care of the word count by itself
    *    @param It will take the word as argument to add in the list 
     * @throws SQLException 
	*/
    public void insertWord(String w) throws SQLException {
        w = w.toLowerCase();
        DbHandler db=new DbHandler();
        db.insert(w);
        db.close();
    } // end insertWord()
    
    /**
    * Write the list of words from the words array, along with their
    * associated frequencies, to the output stream specified in the
    * parameter to this subroutine. Words are output in a column
    * that is 15 spaces wide. If an individual word is longer than
    * 15 spaces, the output won't be quite so neat.
    * @param it will take the printwriter instance as argument to print the data in file
    */
    static void putWordList(PrintWriter output) {
        for (int i = 0; i < words.length; i++) {
            output.print("   ");
            output.print(words[i].word);
            for (int space = words[i].word.length(); space < 15; space++) {
                output.print(" ");
            }
            output.print(" ");
            output.println(words[i].count);
        }
    } // end putWordList()

    /**
     * This method is finding the word in the words array and is returning null if not found
     * @param it will take the word as input parameter to find
     */
     
    public static WordData getWord(String wo) {
        for (WordData temp : words) {
            if (wo.toLowerCase().compareTo(temp.word) == 0) {
                return temp;
            }
        }
        return null;
    }

    /**
     * Use insertion sort to sort the words in the list so that they
     * are in order of decreasing frequency. (Note that insertion sort
     * has the following neat property: In a group of words that all
     * have the same frequency, the words will remain in alphabetical
     * order after the words have been sorted by frequency.)
     */
    static void sortByFrequency() {

        for (int i = 1; i < words.length; i++) {
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
