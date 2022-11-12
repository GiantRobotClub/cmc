package webrunner.cardmaster;
import java.sql.*;
//import java.util.Calendar;
import java.io.*;
import java.lang.Math;
import java.util.Vector;
import java.util.StringTokenizer;


public class CardmasterDonationHandler {

    public static String dbname= "cubecart";
	    public static String dbuser = "cmc";
	    public static String dbpass = "cmcpass";
	    public static String dbhost= "127.0.0.1";



	public CardmasterDonationHandler() {
		
	}
	
	public static CardmasterDonationEntry[] GetDonationEntries() {
		return GetDonationEntries(false);
	}
	public static CardmasterDonationEntry[] GetDonationEntries(boolean restrictunfilled) {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		Vector <CardmasterDonationEntry> entries = new Vector<CardmasterDonationEntry>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+dbname+ "?user="+dbuser+"&password="+dbpass;
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String restrict = "";
			if (restrictunfilled) restrict = " where filled = 0 ";
			String statementstr = "SELECT * from "+dbname+".CubeCart_cards"+restrict+";";
			
			rs = stmt.executeQuery(statementstr);
			while (rs.next()){
				CardmasterDonationEntry newEntry = new CardmasterDonationEntry();
				newEntry.errorcode = rs.getInt("errorcode");
				newEntry.filled = false;
				if (rs.getInt("filled") >= 1) newEntry.filled = true;
				newEntry.accountname = rs.getString("account");
				newEntry.quantity = rs.getInt("quantity");
				newEntry.orderid = rs.getString("orderid");
				newEntry.deckname = rs.getString("card");
				newEntry.id = rs.getLong("id");
				
				entries.add(newEntry);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		//	return (CardmasterDonationEntry[])(entries.toArray());
			
		}
		
		finally {
		
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}				
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
			
		return (CardmasterDonationEntry[])(entries.toArray(new CardmasterDonationEntry[0]));
		}
		
		
	}

	public static int FillEntry(CardmasterDonationEntry entry) {
		if (entry.filled) return -1;
		
		int errorcode = 0;
		boolean filled = false;
		
		CardmasterUser user = CardmasterData.loadUser(entry.accountname);
		
		if (user == null) {
			errorcode = CardmasterDonationEntry.ERROR_USER_NOT_FOUND;
			
			UpdateEntry(entry,filled,errorcode);
			return errorcode;
			
			
			
		}
		if (entry.quantity == 0) {
			errorcode = CardmasterDonationEntry.ERROR_QUANTITY_ZERO;
			
			UpdateEntry(entry,filled,errorcode);
			return errorcode;
		}
		for (int i=0;i<entry.quantity;i++) {
		
			
			if (entry.deckname.startsWith("BOOSTER_")) {
				
				// handle as if a booster
				
				StringTokenizer tokenizer = new StringTokenizer(entry.deckname,"_");
				tokenizer.nextToken();
				
				int expan = Integer.parseInt(tokenizer.nextToken());
				
				
					CardmasterDeckFactory factory = new CardmasterDeckFactory();
					factory.init();
					int deckno = factory.createBooster(expan);
					if (deckno != -1) {
						CardmasterData.userpatch(user.name,"addd",deckno,"x");
						filled = true;
						webrunner.cardmaster.CardmasterData.userpatch(user.name,"extr",0,"donor%yes%yes%");
					
						}
					else {
						errorcode = CardmasterDonationEntry.ERROR_DECK_NOT_FOUND;
						UpdateEntry(entry,filled,errorcode);
						return errorcode;
					}
				
				
						
				
			}
			else if (entry.deckname.startsWith("BOX_")) {
				
				// handle as if a booster
				
				StringTokenizer tokenizer = new StringTokenizer(entry.deckname,"_");
				tokenizer.nextToken();
				
				int expan = Integer.parseInt(tokenizer.nextToken());
				
				
					CardmasterDeckFactory factory = new CardmasterDeckFactory();
					factory.init();
					int deckno = factory.createBoosterBox(expan);
					if (deckno != -1) {
						CardmasterData.userpatch(user.name,"addd",deckno,"x");
						filled = true;
						webrunner.cardmaster.CardmasterData.userpatch(user.name,"extr",0,"donor%yes%yes%");
					
						}
					else {
						errorcode = CardmasterDonationEntry.ERROR_DECK_NOT_FOUND;
						UpdateEntry(entry,filled,errorcode);
						return errorcode;
					}
				
				
						
				
			}
			else if (entry.deckname.startsWith("POINTS")) {
				
				
				webrunner.cardmaster.CardmasterData.userpatch(entry.accountname,"addp",1000,"x");
				webrunner.cardmaster.CardmasterData.userpatch(user.name,"extr",0,"donor%yes%yes%");
					
				filled = true;
				
			}
			else if (entry.deckname.startsWith("EXPED")) {
				
				
				
				CardmasterDeckFactory factory = new CardmasterDeckFactory();	
				factory.init();
				int deckno = factory.createNewDeck(0,3,2);
				if (deckno != -1)  {
					CardmasterData.userpatch(user.name,"addd",deckno,"x");
					filled = true;
					
					webrunner.cardmaster.CardmasterData.userpatch(user.name,"extr",0,"donor%yes%yes%");
					
					
					}	
					
					else {
						errorcode = CardmasterDonationEntry.ERROR_COULDNT_CREATE;
						UpdateEntry(entry,filled,errorcode);
						return errorcode;
					}		
				
				
			}
			
			
			else {
				
				
			
				CardmasterDeckFactory factory = new CardmasterDeckFactory();
				
				factory.init();
				int deckno = factory.newPreCopy(entry.deckname,user.name);
				if (deckno != -1)  {
					CardmasterData.userpatch(user.name,"addd",deckno,"x");
					webrunner.cardmaster.CardmasterData.userpatch(user.name,"extr",0,"donor%yes%yes%");
					
					filled = true;
				
					
					}
				else {
					errorcode = CardmasterDonationEntry.ERROR_DECK_NOT_FOUND;
					UpdateEntry(entry,filled,errorcode);
						return errorcode;
				}	
		
				
				
			}
		}
		CardmasterLogManager.WriteLog("Donation Handled: " + entry.accountname + " - " + entry.quantity + "x" + entry.deckname);
		
		UpdateEntry(entry, filled, errorcode);
		
		return errorcode;
				
	}
	
	public static void UpdateEntry(CardmasterDonationEntry entry, boolean filled, int errorcode) {
		Connection con = null;
		Statement stmt = null;
		boolean success = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+dbname+
						"?user="+dbuser+"&password="+dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			
 			
 				int filledint = 0;
 				if (filled) filledint = 1;
 	
 			
 				String createExtraData =
				
				"UPDATE "+dbname+".CubeCart_cards "+
				" SET filled = "+filledint+", errorcode="+errorcode+" "+
				" WHERE id = " +entry.id +";";
				stmt.executeUpdate(createExtraData);
 			
 			
		
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
	    	
	    	return;
	    }
	}
	
	public static int[] FillAllEntries() {
		
		CardmasterDonationEntry[] entries = GetDonationEntries(true);
		
		int returns[] = new int[3];
		
		returns[0] = 0; // return code
		returns[1] = 0; // successful
		returns[2] = 0; // errors.
		
		if (entries == null) {
			returns[0] = -1;
			return returns;
		}
		if (entries.length == 0) {
			returns [0] = 0;
			return returns;
		}
		
		int filledentries = 0;
		
		int errored = 0;
		for (int i=0;i<entries.length;i++) {
			CardmasterDonationEntry entry = entries[i];
			if (FillEntry(entry) == 0) filledentries++;
			else errored++;
		}
		
		if (filledentries > 0) returns[0] = 1;
		returns[1] = filledentries;
		returns[2] = errored;
		
		return returns;
		
	}
	








}	    