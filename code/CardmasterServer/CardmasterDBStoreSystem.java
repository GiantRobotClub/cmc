package webrunner.cardmaster;
import java.sql.*;
import java.util.Calendar;
import java.io.*;

public class CardmasterDBStoreSystem {
	
	public static CardmasterDBStore loadStore(String username) {
		CardmasterDBStore storeLoad = null;
		for (int i=0;i<5;i++) {
			storeLoad = loadStoreDB(username);
			if (storeLoad != null) return storeLoad;
		}
		return null;
	}
	
	
	static CardmasterDBStore loadStoreDB(String username) {
		
	
		
		ResultSet rs = null;

		Connection con = null;
		Statement stmt = null;
		CardmasterDBStore store = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String storeLoad = "SELECT * from "+CardmasterDatabase.dbname+".cmcstore AS t1"+
							  " where t1.ownername = \""+username+"\";";
			rs = stmt.executeQuery(storeLoad);
			if (!rs.next()) {
				
				store= null;
			}
			else {
				String ownername = rs.getString("ownername");
				String storename = rs.getString("storename");
				boolean enabled = rs.getInt("enabled") == 1;
				boolean sellfree = rs.getInt("sellfree") == 1;
				boolean cmail = rs.getInt("cmail") == 1;
				int sales = rs.getInt("salesforever");
				Date buydate = rs.getDate("lastbought");
				Date moddate = rs.getDate("lastmodified");
				Calendar buycal = Calendar.getInstance();
				Calendar modcal = Calendar.getInstance();
				buycal.setTime(buydate);
				modcal.setTime(moddate);
				
				int modmonth = modcal.get(Calendar.MONTH);
				int modday = modcal.get(Calendar.DAY_OF_MONTH);
				int modyear = modcal.get(Calendar.YEAR);
				
				int buymonth = buycal.get(Calendar.MONTH);
				int buyday = buycal.get(Calendar.DAY_OF_MONTH);
				int buyyear = buycal.get(Calendar.YEAR);
				
				
				store = new CardmasterDBStore();
				
				store.modmonth = modmonth+1;
				store.modday = modday;
				store.modyear = modyear;
				
				store.buymonth = buymonth+1;
				store.buyday = buyday;
				store.buyyear = buyyear;
				
				
				store.name = ownername;
				store.storename = storename;
				store.cmail = cmail;
				store.enabled = enabled;
				store.sellfree = sellfree;
				store.salesforever = sales;
			
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			store = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
					
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return store;
		}
	}
	public static CardmasterDBStore[] loadEnabledStores() {
	
		for (int i=0;i<5;i++) {
			CardmasterDBStore[] stores = loadEnabledStoresDB();
			if (stores != null) return stores;
			
		}
		
		return null;
	}
	
	
	
	
	static CardmasterDBStore[] loadEnabledStoresDB() {
		ResultSet rs = null;

		Connection con = null;
		Statement stmt = null;
		CardmasterDBStore[] stores = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String mesLoad = "SELECT ownername,enabled,storename,lastbought,lastmodified from "+CardmasterDatabase.dbname+".cmcstore AS t1"+
							  " where t1.enabled = '1'";
			rs = stmt.executeQuery(mesLoad);
			
			
			while (rs.next()) {
				if (stores == null)
					stores = new CardmasterDBStore[0];
				
				
				
				
				String ownername = rs.getString("ownername");
				String storename = rs.getString("storename");
				boolean enabled = rs.getInt("enabled") == 1;
		
			//System.out.println("found " + ownername);		
				Date buydate = rs.getDate("lastbought");
				Date moddate = rs.getDate("lastmodified");
				Calendar buycal = Calendar.getInstance();
				Calendar modcal = Calendar.getInstance();
				buycal.setTime(buydate);
				modcal.setTime(moddate);
				
				int modmonth = modcal.get(Calendar.MONTH);
				int modday = modcal.get(Calendar.DAY_OF_MONTH);
				int modyear = modcal.get(Calendar.YEAR);
				
				int buymonth = buycal.get(Calendar.MONTH);
				int buyday = buycal.get(Calendar.DAY_OF_MONTH);
				int buyyear = buycal.get(Calendar.YEAR);
				
				
				CardmasterDBStore store = new CardmasterDBStore();
				
				store.modmonth = modmonth+1;
				store.modday = modday;
				store.modyear = modyear;
				
				store.buymonth = buymonth+1;
				store.buyday = buyday;
				store.buyyear = buyyear;
				
				
				store.name = ownername;
				store.storename = storename;
			
				store.enabled = enabled;
			
			
				
				CardmasterDBStore[] temp = new CardmasterDBStore[stores.length];
				
				System.arraycopy(stores,0,temp,0,stores.length);
				stores = new CardmasterDBStore[stores.length + 1];
				System.arraycopy(temp,0,stores,0,temp.length);
				stores[stores.length-1] = store;
				
				
				
			}
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
			stores = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
				
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return stores;
		}
	}
	
	
	
	
	
	
	
	
	
	public static CardmasterDBStoreCard[] loadStoreCards(String username) {
		for (int i=0;i<5;i++) {
			CardmasterDBStoreCard[] cards = loadStoreCardsDB(username);
			if (cards != null) return cards;
			
		}
		
		return null;	
	}
	
	
	
	
	
	
	
	
	static CardmasterDBStoreCard[] loadStoreCardsDB(String user) {
		ResultSet rs = null;

		Connection con = null;
		Statement stmt = null;
		CardmasterDBStoreCard[] cards = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String mesLoad = "SELECT * from "+CardmasterDatabase.dbname+".cmcstorecard AS t1"+
							  " where t1.ownername = '"+user+"'";
			rs = stmt.executeQuery(mesLoad);
			
//	System.out.println(user);
			
			while (rs.next()) {
				if (cards == null)
					cards = new CardmasterDBStoreCard[0];
				
				

	
				
				String ownername = rs.getString("ownername");
				
				int cardno = rs.getInt("cardnumber");

							
			
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				
			
				Date adddate = rs.getDate("added");
			
				Calendar addcal = Calendar.getInstance();
			
				addcal.setTime(adddate);
				
				int addmonth = addcal.get(Calendar.MONTH);
				int addday = addcal.get(Calendar.DAY_OF_MONTH);
				int addyear = addcal.get(Calendar.YEAR);
			
				
				CardmasterDBStoreCard card = new CardmasterDBStoreCard();
				
				
				
				
				card.modmonth = addmonth+1;
				card.modday = addday;
				card.modyear = addyear;
				
				
				card.cardno = cardno;
				card.price = price;
				card.quantity = quantity;				
			
			
				
				CardmasterDBStoreCard[] temp = new CardmasterDBStoreCard[cards.length];
				
				System.arraycopy(cards,0,temp,0,cards.length);
				cards = new CardmasterDBStoreCard[cards.length + 1];
				System.arraycopy(temp,0,cards,0,temp.length);
				cards[cards.length-1] = card;
				
				
				
			}
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
			cards = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
				
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return cards;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public static CardmasterDBStoreCard loadStoreCard(String username, int cardid) {
		CardmasterDBStoreCard cardLoad = null;
		for (int i=0;i<5;i++) {
			cardLoad = loadStoreCardDB(username,cardid);
			if (cardLoad != null) return cardLoad;
		}
		return null;
		
	}
	
	
	
	static CardmasterDBStoreCard loadStoreCardDB(String username, int cardid) {
		

		
		ResultSet rs = null;

		Connection con = null;
		Statement stmt = null;
		CardmasterDBStoreCard card = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String storeLoad = "SELECT * from "+CardmasterDatabase.dbname+".cmcstorecard AS t1"+
							  " where t1.ownername = \""+username+"\" AND"+
							  " t1.cardnumber = "+cardid+" ;";
			rs = stmt.executeQuery(storeLoad);
			if (!rs.next()) {
				
				card= null;
			}
			else {
				String ownername = rs.getString("ownername");
				
				int cardno = rs.getInt("cardnumber");
				int quantity = rs.getInt("quantity");
				int price = rs.getInt("price");
				
				Date adddate = rs.getDate("added");
			
				Calendar addcal = Calendar.getInstance();
				addcal.setTime(adddate);
			
				int addmonth = addcal.get(Calendar.MONTH)+1;
				int addday = addcal.get(Calendar.DAY_OF_MONTH);
				int addyear = addcal.get(Calendar.YEAR);
			
				
				
				card = new CardmasterDBStoreCard();
				
				card.modmonth = addmonth;
				card.modday = addday;
				card.modyear = addyear;
				
				
				card.cardno = cardno;
				card.quantity = quantity;
				card.price = price;
			
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			card = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
					
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return card;
		}
		
		
	}
	
	
	
	
	public static boolean AddCard(String username, int cardid, int quantity) {
		for (int i=0;i<5;i++) {
			if (AddCardDB(username,cardid,quantity) == true) return true;
			
		}
		return false;
	}
	
	public static boolean RemCard(String username, int cardid, int quantity) {
		for (int i=0;i<5;i++) {
			if (RemCardDB(username,cardid,quantity) == true) return true;
			
		}
		return false;
	}
	
	static boolean AddCardDB(String username, int cardid, int quantity) {
	
		// when adding to a store with 0 it creates a deafault card thingy.
		if (loadStoreCard(username,cardid) == null) {
			if (!CreateDefaultCard(username,cardid).equals("Finished")) {
				System.out.println("Createdeafultcard Error");
				return false;
				}
		}
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
// 			System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstorecard"+
	 								" SET quantity = quantity + '"+quantity+ "' " +
	 								" WHERE ownername = \""+username+"\" AND"+
	 								" cardnumber = '"+cardid+"';";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
	}
	
	
	
	 static boolean RemCardDB(String username, int cardid, int quantity) {
		CardmasterDBStoreCard card = null;
		// when adding to a store with 0 it creates a deafault card thingy.
		if ((card = loadStoreCard(username,cardid)) == null) {
			 return false;
		}
		if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 	//		System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstorecard"+
	 								" SET quantity = quantity - '"+quantity+ "' " +
	 								" WHERE ownername = \""+username+"\" AND"+
	 								" cardnumber = '"+cardid+"';";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
	}
	
	public static boolean SetPrice(String username, int cardid, int price) {
		CardmasterDBStoreCard card = null;
		// when adding to a store with 0 it creates a deafault card thingy.
		if ((card = loadStoreCard(username,cardid)) == null) {
			 return false;
		}
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstorecard"+
	 								" SET price = "+price+ " "+
	 								" WHERE ownername = \""+username+"\" AND"+
	 								" cardnumber = '"+cardid+"';";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
	}
 	
 	
 	public static boolean SetAddedTime(String username, int cardid) {
 		for (int i=0;i<3;i++) {
 			if (SetAddedTimeDB(username,cardid)) return true;
 		}
 		return false;
 	}
 	
 	
 	
 	static boolean SetAddedTimeDB(String username, int cardid) {
 		CardmasterDBStoreCard card = null;
		// when adding to a store with 0 it creates a deafault card thingy.
		if ((card = loadStoreCard(username,cardid)) == null) {
			 return false;
		}
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
// 			System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
				Calendar rightNow = Calendar.getInstance();
				int month = rightNow.get(Calendar.MONTH)+1;
				int year = rightNow.get(Calendar.YEAR);
 				int day = rightNow.get(Calendar.DAY_OF_MONTH);
 				
 				
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstorecard"+
	 								" SET added = '"+year+"-"+month+"-"+day+"'"+ 
	 								" WHERE ownername = \""+username+"\" AND"+
	 								" cardnumber = '"+cardid+"';";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
 	}
 	
 	
 	public static String CreateDefaultCard(String username, int cardid) {
 		if (loadStoreCard(username,cardid) != null) return "Already Exists";
 		String success = "none";
 		for (int i=0;i<3;i++) {
 			success = CreateDefaultCardDB(username,cardid);
 			if (success.equals("Finished")) break;
 		}
 		return success;
 	}
 	
 	
 	public static String CreateDefaultStore(String username) {
 		if (loadStore(username) != null) return "Already Exists";
 		String success = "none";
 		for (int i=0;i<3;i++) {
 			success = CreateDefaultStoreDB(username);
 			if (success.equals("Finished")) break;
 		}
 		return success;
 		
 	}
 	static String CreateDefaultCardDB(String username, int cardid) {
 		
		Connection con = null;
		Statement stmt = null;
		String success = "Nothing";
 		
 		
 		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			 con = DriverManager.getConnection(url);
			
			 
			 
			Calendar rightNow = Calendar.getInstance();
			int month = 1;
			int year = 1900;
			int day = 1;
			
 			
			 
//			 System.out.println(user);
			 String createStore = 
			"INSERT INTO "+CardmasterDatabase.dbname+".cmcstorecard(ownername,cardnumber,price,quantity,added)"+
			"values ('"+username+"',"+
			"'"+cardid+"',"+
			"'"+0+"',"+
			"'"+0+"',"+
			"'"+year+"-"+month+"-"+day+"');";
				
 		
 			 stmt = con.createStatement();
    		 stmt.executeUpdate(createStore);
 		
 				success = "Finished";
			}
			catch(Exception e) {
				e.printStackTrace();
				success = success +" AN ERROR!";
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
 		
 		
 		
 		
 		
 		
 	}
 	 static String CreateDefaultStoreDB(String username) {
 	
 		
		Connection con = null;
		Statement stmt = null;
		String success = "Nothing";
 		
 		
 		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			 con = DriverManager.getConnection(url);
			
			 
			 
			Calendar rightNow = Calendar.getInstance();
			int month = rightNow.get(Calendar.MONTH) +1;
			int year = rightNow.get(Calendar.YEAR);
 			int day = rightNow.get(Calendar.DAY_OF_MONTH);
 			
			 
//			 System.out.println(user);
			 String createStore = 
			"INSERT INTO "+CardmasterDatabase.dbname+".cmcstore(ownername,lastmodified,lastbought,storename,enabled,cmail,sellfree,salesforever)"+
			"values ('"+username+"',"+
			"'"+year+"-"+month+"-"+day+"',"+
			"'"+year+"-"+month+"-"+day+"',"+
			"'"+"Unnamed Store"+"',"+
			"'"+0+"',"+
			"'"+0+"',"+
			"'"+0+"',"+
			"'"+0+"');";
				
 		
 			 stmt = con.createStatement();
    		 stmt.executeUpdate(createStore);
 		
 				success = "Finished";
			}
			catch(Exception e) {
				e.printStackTrace();
				success = success +" AN ERROR!";
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
 		
 		
 	}
	
	

	
	public static boolean CMail(String username, String buyname, int id, int price, int quant) {
		CardmasterDBStore store = loadStore(username);
		if (store == null) return false;
		if (!store.cmail) return false;
		else {
			CardmasterServerCard[] cards = CardmasterData.loadCardData();
			if (cards[id] == null) return false;
			String cardname = cards[id].name;
			String message = buyname + " has bought " + quant + "x " + cardname + " from your personal store for " + price  + " points!";
			String subject = cardname + " has been bought from your store";
			String returnval = CardmasterMessageSystem.SendMessage(username,buyname,message,subject,0);
			if (returnval.equals("Success")) return true;
		}
		return true;
	}
	public static boolean IncrementSales(String username, int quant) {
		for (int i=0;i<3;i++) {
			if (IncrementSalesDB(username,quant)) return true;
		}
		return false;
	}
	
	
	static boolean IncrementSalesDB(String username, int quant) {
		
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET salesforever = salesforever + "+quant+ " "+
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
	public static boolean SetStoreEnabled(String username, boolean enabled) {
		for (int i=0;i<3;i++) {
			if (SetStoreEnabledDB(username,enabled)) return true;
		}
		return false;
	}

	public static boolean TooOld(String username) {
		
		CardmasterDBStore store = loadStore(username);
			return TooOld(store);	
		}
	public static boolean TooOld(CardmasterDBStore store) {
	
		if (store == null) return false;
		Calendar now = Calendar.getInstance();
		
			
		int nowmonth = now.get(Calendar.MONTH)+1;
		int nowday = now.get(Calendar.DAY_OF_MONTH);
		int nowyear = now.get(Calendar.YEAR);
		
		
		int numdaysbuy = nowday - store.buyday;
		numdaysbuy = (nowmonth - store.buymonth) * 30;
		numdaysbuy = (nowyear - store.buyyear) * 356;
		
		
			
		int numdaysmod = nowday - store.modday;
		numdaysmod = (nowmonth - store.modmonth) * 30;
		numdaysmod = (nowyear - store.modyear) * 356;
		
		
		
		
		
		if (numdaysbuy >= 100 && numdaysmod >= 100) return true;
		else return false;
	}
	
	static boolean SetStoreEnabledDB(String username, boolean enabled) {
		
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 			int enabledn = 0;
 			if (enabled) enabledn = 1;
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET enabled = "+enabledn+ " "+
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
	
	
	public static boolean SetStoreCMail(String username, boolean enabled) {
		for (int i=0;i<3;i++) {
			if (SetStoreCMailDB(username,enabled)) return true;
		}
		return false;	
	}
	
	
	static boolean SetStoreCMailDB(String username, boolean enabled) {
	
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 			int enabledn = 0;
 			if (enabled) enabledn = 1;
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET cmail = "+enabledn+ " "+
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
	
	public static boolean SetStoreSellFree(String username, boolean enabled) {
		for (int i=0;i<3;i++) {
			if (SetStoreSellFreeDB(username,enabled)) return true;
		}
		return false;
	}
	
	
	static boolean SetStoreSellFreeDB(String username, boolean enabled) {
	
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 			int enabledn = 0;
 			if (enabled) enabledn = 1;
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET sellfree = "+enabledn+ " "+
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
	
	
	
	public static boolean SetModTime(String username) {
 		for (int i=0;i<3;i++) {
			if (SetModTimeDB(username)) return true;
		}
		return false;
 	}
 	
 	
 	
 	
	static boolean SetModTimeDB(String username) {
	
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 			
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
			
			
			
				Calendar rightNow = Calendar.getInstance();
				int month = rightNow.get(Calendar.MONTH)+1;
				int year = rightNow.get(Calendar.YEAR);
 				int day = rightNow.get(Calendar.DAY_OF_MONTH);
 				
		
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET lastmodified = '"+year+"-"+month+"-"+day+"'"+ 
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
 	
 	
 	
 	public static boolean SetBuyTime(String username) {
 		for (int i=0;i<3;i++) {
			if (SetBuyTimeDB(username)) return true;
		}
		return false;
 	}
 	
 	
 	
 	
 	
	static boolean SetBuyTimeDB(String username) {
	
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 			
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
			
			
			
				Calendar rightNow = Calendar.getInstance();
				int month = rightNow.get(Calendar.MONTH)+1;
				int year = rightNow.get(Calendar.YEAR);
 				int day = rightNow.get(Calendar.DAY_OF_MONTH);
 				
		
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET lastbought = '"+year+"-"+month+"-"+day+"'"+ 
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
 	
 	
 	
 	
	
	public static boolean SetStoreName(String username, String storename) {
		for (int i=0;i<3;i++) {
			if (SetStoreNameDB(username,storename)) return true;
		}
		return false;
	}
	
	
	
	static boolean SetStoreNameDB(String username, String storename) {
	
		//if (card.quantity < quantity) return false;
		
		Connection con = null;
		Statement stmt = null;
 		boolean success = true;
 		try{
 			
 		//	System.out.println("Entered userpatch for " + name + " (command: " + command);
 			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
			
			
			
		
		
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcstore"+
	 								" SET storename = '"+storename+"'"+ 
	 								" WHERE ownername = \""+username+"\";";
	 								
	 		
    		 stmt.executeUpdate(statement);
    		 
		}
		catch(Exception e) {
				e.printStackTrace();
				success = false;
				}
			finally {
				try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
				try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
				 
				return success	;
			}
		
		
	}
 	
 	
	
	
	
	
	
	public static String CreateDatabase() {
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try{
		
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
						//System.out.println(url);
			 con = DriverManager.getConnection(url);
			 whathappened = "Connection made";
			 String createStore =   " CREATE TABLE IF NOT EXISTS "+CardmasterDatabase.dbname+".cmcstore   ("+
			 						" ownername VARCHAR(50) PRIMARY KEY NOT NULL,"+
			 						" lastmodified DATE,"+
			 						" lastbought DATE,"+
			 						" storename VARCHAR(255),"+
			 						" enabled BOOL,"+
			 						" cmail BOOL,"+
			 						" sellfree BOOL,"+
			 						" salesforever INT unsigned,"+
			 						" FOREIGN KEY(ownername) REFERENCES "+CardmasterDatabase.dbname+".cmcusers(username));";
			 String createStoreCard=" CREATE TABLE IF NOT EXISTS "+CardmasterDatabase.dbname+".cmcstorecard ("+
			 						" ownername VARCHAR(50) NOT NULL,"+
			 						" cardnumber SMALLINT UNSIGNED NOT NULL,"+
			 						" price INT,"+
			 						" quantity SMALLINT,"+
			 						" added DATE,"+
			 						
			 						"PRIMARY KEY(ownername,cardnumber),"+
			 						"FOREIGN KEY(ownername) references "+CardmasterDatabase.dbname+".cmcstore(ownername));";
			 						
			 						
			stmt = con.createStatement();
    		 stmt.executeUpdate(createStore);
    		 whathappened = "Store Table Created"; 	
    		  stmt.executeUpdate(createStoreCard);
    		 whathappened = "Store Card Table Created"; 	
    		 
    		 
    		 	 whathappened = "Finished";
    	}	 	 
    	catch(Exception e) {
			e.printStackTrace();
			whathappened = whathappened += " and then an error!";
		}
		
		finally {
			try{stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{con.close();}catch (Exception e) {e.printStackTrace();	}
			 
			return whathappened;
			
		}
							 						
	}
	
}