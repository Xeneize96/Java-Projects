

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
* This method is getting the words from the file and adding them in database
* and then it gets the words from the database again and store in array
* and then sort the array and finally print the words in file and empty the database
* @param inputFileName is the name of the input file from which the words to be read
* @param outputFileName is the name of the output file in which the data to be stored as a result
* @return It will return a string that will contain the success or error message
*/

/**
* This class is handling the database connections and all queries
*/

public class DbHandler {
	/**
	* This is the connection with the database
	*/
	private Connection con;
	/**
	* This is the url of  the database
	*/
    private String DBURL = "jdbc:mysql://localhost/word";
	/**
	* This is the username of the database
	*/

    private String DBUser = "root";
	/**
	* This is the password of the database
	*/

    private String DBPassword = "";

    
    /**
	* This constructor is calling the method to connect with the database
	*/

    public DbHandler()
    {
        try { 
            connection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	/**
	* This method is establishing the connection with the database
	*/

    private void connection() throws ClassNotFoundException,SQLException
    { 
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(DBURL, DBUser, DBPassword);
    }

    /**
	* This method is closing the connection with the database
	*/

    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
    * This method is getting the words count from the database.
    * @param word is the parameter that is to find in database and return its count
    * @return It will return count of word otherwise 0 if not found
    */
    
    public int getCountOfWords(String word)
    {
    	try{
		    String query = "select count from word where word ='" + word + "' ";
		    Statement stmt = null;
		    stmt = (Statement) con.createStatement();
		    ResultSet rs=stmt.executeQuery(query);
		    if (rs.next()) {
		        int count = rs.getInt(1);
		        return count;
		    }
		    return 0;
	    }
	    catch(Exception e)
	    {
	        System.err.println(e);
	        return 0;
	    }
    }

    /**
    * This method will return the total words in the database to initialize the array
    * @return integer containing the number of words
    */

    public int totalRows()
    {
    	try{
		    String query = "select count from word";
		    Statement stmt = null;
		    stmt = (Statement) con.createStatement();
		    ResultSet rs=stmt.executeQuery(query);
		    int count=0;
		    while(rs.next()) {
		        count++;
	        }
		    return count;
	    }
	    catch(Exception e)
	    {
	        System.err.println(e);
	        return -1;
	    }
    }

    /**
    * This method will empty the database after program finishes.
    */

    public void deleteAllWords()
    {
    	try{
		    String query = "delete from word";
		    Statement stmt = null;
		    stmt = (Statement) con.createStatement();
		    int rows=stmt.executeUpdate(query);
	    }
	    catch(Exception e)
	    {
	        System.err.println(e);
	    }
    }

    /**
    * @return It will return the words array
    */

    public WordData[] getAllWords()
    {
    	try{
    		WordData words[]=new WordData[totalRows()];
		    String query = "select * from word";
		    Statement stmt = null;
		    stmt = (Statement) con.createStatement();
		    ResultSet rs=stmt.executeQuery(query);
		    int i=0;
		    while(rs.next()) {
		        words[i]=new WordData(rs.getString(1),rs.getInt(2));
		        i++;		
	        }
		    return words;
	    }
	    catch(Exception e)
	    {
	        System.err.println(e);
	        return null;
	    }
    }

    /**
    * This method will insert word in database.
    * It will first get count of it and if its 0, then add word with 1 count.
    * Otherwise will add word with increment in count  
    * @return It will return false in case of exception
    */

    public boolean insert(String word) throws SQLException {
        PreparedStatement prst = null;
        int countOfWord=getCountOfWords(word);
        if (countOfWord==0) {
            String query = "INSERT INTO word(word,count) VALUES(?,?);";
            prst = con.prepareStatement(query);
            prst.setString(1, word);
            prst.setInt(2, 1);
            prst.execute();
        }else {
        	String query = "INSERT INTO word(word,count) VALUES(?,?);";
            prst = con.prepareStatement(query);
            prst.setString(1, word);
            prst.setInt(2, countOfWord++);
            prst.execute();
        }
        return true;
    }

}