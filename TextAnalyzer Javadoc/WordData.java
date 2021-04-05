
/**
* <h1>WordData</h1>
* This class is just the model class to hold the word and its count
*/
class WordData {


	/**
	*     A little class to hold the data for one word.
	*     The word (in lower case letters).
	*/
    String word; 
    /**
	*     The number of times the word has been found.
	*/
    int count; 

    /**
	*     The number of times the word has been found.
	*/

    
    /**
	*      Constructor creates an object with the specified word
    *       and with the counter initialized to 1.
	*/

    WordData(String w) {
        word = w;
        count = 1;
    }
}
