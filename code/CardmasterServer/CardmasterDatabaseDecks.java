package webrunner.cardmaster;
import java.sql.*;
import java.util.Calendar;
import java.io.*;
import java.lang.Math;

 public class CardmasterDatabaseDecks {
	 	
 	
 	
 	
 	public int BuildDatabase() {
 		
 		String createDeckTableName  = "";
 		String createDeckTable = 
 			"CREATE TABLE IF NOT EXISTS "+CardmasterDatabase.dbname+".deckcards ( "+
 			"deckno INT NOT NULL,"+
 			"cardno INT NOT NULL,"+
 			"quantity INT NOT NULL,"+
 			"PRIMARY KEY(deckno,cardno),"+
 			"FOREIGN KEY (deckno) REFERENCES "+CardmasterDatabase.dbname+".decks(deckno));";
 			
 			
 		
 		
 		return 1;
 	}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 }