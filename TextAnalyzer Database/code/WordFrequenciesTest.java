/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        try {
			instance.insertWord(w);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        WordData expResult;
        expResult = new WordData("Word");
        DbHandler db=new DbHandler();
        WordFrequencies.words=db.getAllWords();
        db.deleteAllWords();
        db.close();
        
        WordData result = WordFrequencies.getWord(w); 
        assertEquals(expResult.count, result.count+1);
        // TODO review the generated test code and remove the default call to fail.
	}
	
    /**
     * Test of sortByFrequency method, of class WordFrequencies.
     */
    @Test
    public void testSortByFrequency() {
        WordFrequencies instance=new WordFrequencies();
        try {
        	instance.insertWord("Apple");
            instance.insertWord("Apples");
            instance.insertWord("Word");
        	instance.insertWord("Word");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DbHandler db=new DbHandler();
        WordFrequencies.words=db.getAllWords();
        db.deleteAllWords();
        db.close();
        WordFrequencies.sortByFrequency();
        String expectedResult = "apple_0 apples_0 word_0 word_0 ";
        String result="";
        for(int i=0;i<WordFrequencies.words.length;i++)
        {
            result+=WordFrequencies.words[i].word+"_"+WordFrequencies.words[i].count+" ";
        }
        //System.out.println(expectedResult);
        //System.out.println(result);
        
       assertEquals(expectedResult, result);
    }
    
    @Test
    public void testWordCount() {
        WordFrequencies instance=new WordFrequencies();
        try {
			instance.insertWord("Word");
			instance.insertWord("Word");
	        instance.insertWord("Word");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DbHandler db=new DbHandler();
        WordFrequencies.words=db.getAllWords();
        db.deleteAllWords();
        db.close();
        int expectedResult = 3;
        int result = WordFrequencies.words.length;
        assertEquals(expectedResult, result);
    }
}
