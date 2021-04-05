import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordDataTest {
	/**
		This test case will add the word in the list and check for ots count.
		It should be one.	
	*/
	@Test
    public void testConstuctor() {
        WordData t = new WordData("Word");
        int expectedResult = 1;
        int result = t.count;
        assertEquals(expectedResult, result);
    }


}
