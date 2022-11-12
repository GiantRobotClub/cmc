package webrunner.cardmaster;
import java.sql.*;
import java.util.Calendar;
import java.io.*;
import java.lang.Math;

 public class CardmasterDatabase {
	
	
	
	
	
		  
		 //public static String dbname= "mythos";
	    public static String dbname = webrunner.cardmaster.CardmasterData.dbname;
	    public static String dbuser = webrunner.cardmaster.CardmasterData.dbuser;
	    public static String dbpass = webrunner.cardmaster.CardmasterData.dbpass;
	    public static String dbhost= webrunner.cardmaster.CardmasterData.dbhost;
	    
	public static String AddExistingUserToDatabase(String name) {
		CardmasterUser user = CardmasterData.loadUserOld(name);
		return SaveUserDB(user);
		
			
	}
	

	
	public static int EmailExists(String email) {
		int returnval = 0;
		ResultSet rs = null;	
		Connection con = null;
		Statement stmt = null;
		try{
		
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+CardmasterDatabase.dbname+
							"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			
			String userLoad = "SELECT * from "+dbname+".cmcusers where email = '" + email + "';";
			rs = stmt.executeQuery(userLoad);
			
			
			if (rs.next()) {
					
				
				returnval = 1;	
				
			}else {
				returnval = 0;
			}
			
			
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
			returnval = -1;
		}
		
		
		finally {
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			
			return returnval;
		}
	}
	
	
	// loads all user data
	public static CardmasterUser[] LoadUserData() { 
		ResultSet rs = null;
		ResultSet rs2 = null;
		Connection con = null;
		Statement stmt = null;
		Statement stmt2 = null;
		CardmasterUser[] users = new CardmasterUser[0];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			String userLoad = "SELECT * from "+dbname+".cmcusers;";
			rs = stmt.executeQuery(userLoad);
			while (rs.next()){
			
				String uname = rs.getString("username");
				String name = uname;
				System.out.println(name);
				String email = rs.getString("email");
				String password = rs.getString("password");
				Date lastlogin = rs.getDate("lastlogin");
				Calendar cal = Calendar.getInstance();
				cal.setTime(lastlogin);
				int month = cal.get(Calendar.MONTH)+1;
				int date = cal.get(Calendar.DAY_OF_MONTH);
				int year = cal.get(Calendar.YEAR);
				int points = rs.getInt("points");
				
				int wins = rs.getInt("wins");
				int losses = rs.getInt("loss");
				int verifcode = rs.getInt("verifcode");
				boolean verified = false;
				if (rs.getInt("verified") >= 1) verified = true;
				int prideckno = rs.getInt("prideckno");
				String deckLoad = "SELECT deckno from "+dbname+".cmcdecks AS t1"+
							  " where t1.owner = \""+name+"\";";
				
				
				int[] decks = new int[1];
			
				rs2 = stmt2.executeQuery(deckLoad);
				
				while (rs2.next()) {
					int[] tempdeck = new int[decks.length +1];
					System.arraycopy(decks,0,tempdeck,0,decks.length);
					tempdeck[decks.length] = rs2.getInt("deckno");
					decks = new int[decks.length +1];
					System.arraycopy(tempdeck,0,decks,0,tempdeck.length);
					
				}
				rs2.close();
				rs2 = null;
				
				CardmasterUser user = new CardmasterUser();
				
							
				
				user.decks = decks;
				user.password = password;
				user.name = name;
				user.wins = wins;
				user.losses = losses;
				user.verifcode = verifcode;
				user.email = email;
				user.emailverified = verified;
				user.primarydeck = prideckno;
				user.points = points;
				user.month = month;
				user.year = year;
				user.day = date;
				
				
				CardmasterUser[] tempusers = new CardmasterUser[users.length + 1];
				System.arraycopy(users,0,tempusers,0,users.length);
				//	//System.out.println("Line " + inputLine);
				tempusers[users.length] = user;
				users = new CardmasterUser[tempusers.length];
				System.arraycopy(tempusers,0,users,0,tempusers.length);
			//	System.out.println(user);
				
				
							  
			}
			
		}
		catch(Exception e) {
			users = null;
			e.printStackTrace();
			
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(rs2 != null)rs2.close();}catch (Exception e) {e.printStackTrace();	}
			
			try{if(stmt2 != null)stmt2	.close();}catch (Exception e) {e.printStackTrace();	}
						
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return users;
		}
	}


	


	// This function adds to database first, if the file exists.  Then it moves the file to the 'oldusers' folder.


	

	public static CardmasterUser LoadUserReplace(String name) {
		File userfile = new File(CardmasterData.DIRECTORY + "users/" + name + ".usr");
		if (userfile.exists()) {
			
			AddExistingUserToDatabase(name);
			
			CardmasterUser olduser = CardmasterData.loadUserOld(name);
			CardmasterData.saveUserToOldFolder(olduser);
			
			userfile.delete();
			
			// remove OLD file and put in 
			
			
			
			
			
			
			
			
		}
		CardmasterUser user = null;
		for (int i=0;i<5;i++) {
			user = LoadUserDB(name);
			if (user != null) break;
		}
	
		return user;
	}	
	public static CardmasterUser LoadUserDB(String name) {
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		Connection con = null;
		Statement stmt = null;
		CardmasterUser user = null;
		
		File passfile = null;
		FileInputStream bin = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String userLoad = "SELECT * from "+dbname+".cmcusers AS t1"+
							  " where t1.username = \""+name+"\";";
			rs = stmt.executeQuery(userLoad);
			if (!rs.next()) {
				
				user = null;
			}
			else {
				
				String uname = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Date lastlogin = rs.getDate("lastlogin");
				 Calendar cal = Calendar.getInstance();
				cal.setTime(lastlogin);
				int month = cal.get(Calendar.MONTH)+1;
				int date = cal.get(Calendar.DAY_OF_MONTH);
				int year = cal.get(Calendar.YEAR);
				int points = rs.getInt("points");
				
				int wins = rs.getInt("wins");
				int losses = rs.getInt("loss");
				int verifcode = rs.getInt("verifcode");
				boolean verified = false;
				if (rs.getInt("verified") >= 1) verified = true;
				int prideckno = rs.getInt("prideckno");
				
				
				
				String deckLoad = "SELECT deckno,owner from "+dbname+".cmcdecks AS t1"+
								  " where t1.owner = \""+uname+"\";";
				String edLoad =   "SELECT edname,dataA,dataB,owner from "+dbname+".cmcextradata AS t1"+
								  " where t1.owner = \""+uname+"\";";
				
				ExtraData[] ed = new ExtraData[0];
				
				
				rs2 = stmt.executeQuery(edLoad);
				while (rs2.next()) {
				
					String edname = rs2.getString("edname");
					String dataA = rs2.getString("dataA");
					String dataB = rs2.getString("dataB");
					String edowner = rs2.getString("owner");
					if (!edowner.equals(uname)) continue;
					ExtraData newEd = new ExtraData(edname,dataA,dataB);
					
					ExtraData[] edt = new ExtraData[ed.length + 1];
					System.arraycopy(ed,0,edt,0,ed.length);
					edt[ed.length] = newEd;
					ed = new ExtraData[edt.length];
					System.arraycopy(edt,0,ed,0,edt.length);
				
				
				
				}		
				
				int[] decks = new int[1];
				
				rs3 = stmt.executeQuery(deckLoad);
				
				while (rs3.next()) {
					
					
					String edowner = rs3.getString("owner");
					if (!edowner.equals(uname)) continue;
					
					int[] tempdeck = new int[decks.length +1];
					System.arraycopy(decks,0,tempdeck,0,decks.length);
					tempdeck[decks.length] = rs3.getInt("deckno");
					decks = new int[decks.length +1];
					System.arraycopy(tempdeck,0,decks,0,tempdeck.length);
					
				}
				
				
				user = new CardmasterUser();
				
							
				user.ed = ed;
				user.decks = decks;
				user.password = password;
				user.name = uname;
				user.wins = wins;
				user.losses = losses;
				user.verifcode = verifcode;
				user.email = email;
				user.emailverified = verified;
				user.primarydeck = prideckno;
				user.points = points;
				user.month = month;
				user.year = year;
				user.day = date;
				if (user.password.equals("BYTEPASSWORD")) {
					 passfile = new File(CardmasterData.DIRECTORY + "users/password_" +uname+ ".bin");
					 bin = new FileInputStream(passfile);
					user.bytepassword = new byte[(int)(passfile.length())];
					bin.read(user.bytepassword);

				}
			
			}			
				
			
			//try{if(passfile != null)passfile.close();}catch(Exception e){e.printStackTrace(); }
			
		}
		catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(rs2 != null)rs2.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(rs3 != null)rs3.close();}catch (Exception e) {e.printStackTrace();	}
						
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
//			try{if(passfile != null)passfile.close();}catch(Exception e){e.printStackTrace(); }
			try{if(bin != null)bin.close();}catch(Exception e){e.printStackTrace(); }
			
					
			return user;
		}
		
	}
	public static boolean CreateDefaultUser(CardmasterUser user) {
		Connection con = null;
		Statement stmt = null;
		boolean success = true;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			 con = DriverManager.getConnection(url);
			
			 
			 
			 int verified = 0;
			 if (user.emailverified) verified = 1;
			 
			 String useryear = "" + user.year;
			 if (user.year < 1000) useryear = "1000";
			 String usermonth = "" + user.month;
			 if (user.month < 10) usermonth = " " + user.month;
			 
//			 System.out.println(user);
			 String createUser = 
			"REPLACE INTO "+CardmasterDatabase.dbname+".cmcusers(username,prideckno,password,wins,loss,points,lastlogin,verifcode,verified,email)"+
			"values ('"+user.name.replaceAll("'","''")+"',"+
			"'"+user.primarydeck+"',"+
			"'"+user.password.replaceAll("'","''")+"',"+
			"'"+user.wins+"',"+
			"'"+user.losses+"',"+
			"'"+user.points+"',"+
			"'"+useryear+"-"+usermonth+"-01',"+
			"'"+user.verifcode+"',"+
			"'"+verified+"',"+
			"'"+user.email+"');";
			
			
			
			 
			 stmt = con.createStatement();
    		 stmt.executeUpdate(createUser);
    	
			for (int i=1;i<user.decks.length;i++) {
				System.out.println("Doing deck " + user.decks[i]);
				String createDeck =
				"INSERT INTO "+CardmasterDatabase.dbname+".cmcdecks(deckno,owner)"+
				" VALUES ('"+user.decks[i]+"',"+
				        "'"+user.name.replaceAll("'","''")+"');";
				  // System.out.println(createDeck);     
				stmt.executeUpdate(createDeck);
	    		    
			}
			
			 
			 
			 
		
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
	public static String SaveUserDB(CardmasterUser user) {
	
		
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
			
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			 con = DriverManager.getConnection(url);
			 whathappened = "Connection made";
			 
			 
			 int verified = 0;
			 if (user.emailverified) verified = 1;
			 
			 
			 
			 String createUser = 
			"REPLACE INTO "+CardmasterDatabase.dbname+".cmcusers(username,prideckno,password,wins,loss,points,lastlogin,verifcode,verified,email)"+
			"values ('"+user.name.replaceAll("'","''")+"',"+
			"'"+user.primarydeck+"',"+
			"'"+user.password.replaceAll("'","''")+"',"+
			"'"+user.wins+"',"+
			"'"+user.losses+"',"+
			"'"+user.points+"',"+
			"'"+user.year+"-"+user.month+"-"+user.day+"',"+
			"'"+user.verifcode+"',"+
			"'"+verified+"',"+
			"'"+user.email+"');";
			
			
			
			 
			 stmt = con.createStatement();
    		 stmt.executeUpdate(createUser);
    		 whathappened = "User Added";
			
			for (int i=1;i<user.decks.length;i++) {
				System.out.println("Doing deck " + user.decks[i]);
				String createDeck =
				"REPLACE INTO "+CardmasterDatabase.dbname+".cmcdecks(deckno,owner)"+
				" VALUES ('"+user.decks[i]+"',"+
				        "'"+user.name.replaceAll("'","''")+"');";
				  // System.out.println(createDeck);     
				stmt.executeUpdate(createDeck);
	    		whathappened = "Deck Added";     
			}
			
			for (int i=0;i<user.ed.length;i++) {
				String createExtraData =
				"REPLACE INTO "+CardmasterDatabase.dbname+".cmcextradata(edname,dataA,dataB,owner)"+
				" VALUES ('"+user.ed[i].name.replaceAll("'","''").substring(0,Math.min(254,user.ed[i].name.replaceAll("'","''").length()))+"',"+
						"'"+user.ed[i].dataa.replaceAll("'","''").substring(0,Math.min(254,user.ed[i].dataa.replaceAll("'","''").length()))+"',"+
						"'"+user.ed[i].datab.replaceAll("'","''").substring(0,Math.min(254,user.ed[i].datab.replaceAll("'","''").length()))+"',"+
						"'"+user.name.replaceAll("'","''")+"')";
				stmt.executeUpdate(createExtraData);
	    		whathappened = "Extra Data Added";     
			}
			 
			 
			 
		
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
	
	public static String CreateCMCDatabase() {
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try{
		
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
						//System.out.println(url);
			 con = DriverManager.getConnection(url);
			 whathappened = "Connection made";
			 String createTableUsers = 
			 	" CREATE TABLE IF NOT EXISTS "+CardmasterDatabase.dbname+".cmcusers ("+
			 	" username VARCHAR(50) PRIMARY KEY NOT NULL,"+
			 	" prideckno INT,"+
			 	" password VARCHAR(50) NOT NULL,"+
			 	" wins INT,"+
			 	" loss INT,"+
			 	" points INT,"+
			 	" lastlogin DATE,"+
			 	" verifcode INT,"+
			 	" verified BOOL,"+
			 	" email VARCHAR(128),"+
			 	" FOREIGN KEY(prideckno) REFERENCES "+CardmasterDatabase.dbname+".cmcdecks(deckno));";
			 	
			 
			 String createTableDecks = 
			 	" CREATE TABLE IF NOT EXISTS cmcdecks (" +
			 	" deckno INT PRIMARY KEY NOT NULL,"+
			 	" owner VARCHAR(50),"+
			 	" FOREIGN KEY(owner) REFERENCES "+CardmasterDatabase.dbname+". cmcusers(username));";
			 
			 
			  String createTableED = 
			 	"CREATE TABLE IF NOT EXISTS cmcextradata (" +
			 	" edname VARCHAR(255) NOT NULL,"+
			 	" dataA VARCHAR(255),"+
			 	" dataB VARCHAR(255),"+
			 	" owner VARCHAR(50) NOT NULL,"+
			 	" PRIMARY KEY(owner,edname),"+
			 	" FOREIGN KEY(owner) REFERENCES "+CardmasterDatabase.dbname+". cmcusers(username));";
			 
			 	
			 stmt = con.createStatement();
    		 stmt.executeUpdate(createTableUsers);
    		 whathappened = "User Table Created";
    		 stmt.executeUpdate(createTableDecks);
    		 whathappened = "Decks Table Created";
    		 stmt.executeUpdate(createTableED);
    		 whathappened = "ED Table Created";
    		 
    		 
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
