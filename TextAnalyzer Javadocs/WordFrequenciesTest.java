/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SSC
 */
public class WordFrequenciesTest {
    
    public WordFrequenciesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of countFrequency method, of class WordFrequencies.
     */
    @Test
    public void testCountFrequency() {
        String inputFileName = "in.txt";
        String outputFileName = "out.txt";
        WordFrequencies instance = new WordFrequencies();
		String expResult = "Can't find file \"" + inputFileName + "\".";
        String result = instance.countFrequency(inputFileName, outputFileName);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertWord method, of class WordFrequencies.
     */
    @Test
    public void testInsertWord() {
        String w = "Word";
        WordFrequencies instance = new WordFrequencies();
        instance.insertWord(w);
        WordData expResult;
        expResult = new WordData("Word");
        WordData result = WordFrequencies.getWord(w); 
        assertEquals(expResult.count, result.count);
        // TODO review the generated test code and remove the default call to fail.
	}
	
    /**
     * Test of sortByFrequency method, of class WordFrequencies.
     */
    @Test
    public void testSortByFrequency() {
        WordFrequencies instance=new WordFrequencies();
        instance.insertWord("Word");
        instance.insertWord("Apple");
        instance.insertWord("Apples");
        instance.insertWord("Word");
        WordFrequencies.sortByFrequency();
        String expectedResult = "word_2 apple_1 apples_1 ";
        String result="";
        for(int i=0;i<WordFrequencies.wordCount;i++)
        {
            result+=WordFrequencies.words[i].word+"_"+WordFrequencies.words[i].count+" ";
        }
       assertEquals(expectedResult, result);
    }
    
    @Test
    public void testWordCount() {
        WordFrequencies instance=new WordFrequencies();
        instance.insertWord("Word");
        instance.insertWord("Word");
        instance.insertWord("Word");
        int expectedResult = 3;
        int result = WordFrequencies.wordCount;
        assertEquals(expectedResult, result);
    }
}
