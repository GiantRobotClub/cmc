package webrunner.cardmaster;
import java.sql.*;
import java.util.Calendar;
import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;
 public class CardmasterDatabaseUserpatch {
 	
 	synchronized public static boolean ChangeExtraData(String name, String edname, String dataA, String dataB, Statement stmt, Connection con) {
		try{
			String createExtraData =
				
				"REPLACE INTO "+CardmasterDatabase.dbname+".cmcextradata(edname,dataA,dataB,owner)"+
				" VALUES ('"+edname.replaceAll("'","''").substring(0,Math.min(edname.length(),254))+"',"+
						"'"+dataA.replaceAll("'","''")+"',"+
						"'"+dataB.replaceAll("'","''")+"',"+
						"'"+name.replaceAll("'","''")+"')";
				stmt.executeUpdate(createExtraData);
				CardmasterLogManager.WriteLog("Extra data change: " + name + " : "  + edname + " : " + dataA + " : " + dataB);
				return true;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return false;	
	}
	

	synchronized public static boolean ChangeExtraData(String name, String edname, String dataA, String dataB) {
		Connection con = null;
		Statement stmt = null;
		boolean success = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			success = ChangeExtraData(name,edname,dataA,dataB,stmt,con);
		
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
	    	
	    	return success;
	    }
	}
 	synchronized public static boolean userpatch(String name, String command, int amount, String text) {
 		if (name.length() == 0 || command.length() == 0)  return false;
 		if (text.length() == 0) text = " ";
 		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) {
 			CardmasterLogManager.WriteLog("Couldnt userpatch for: " + name + " " + command + " " + amount + " " + text + " ( user is null ) ");
 			
 			
 			
 			return false;
 			}
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
 			
	 		if (command.equals("addd")) {// add deck
	 			if (!user.hasDeck(amount)) {
		 			
		 			String statement = "REPLACE INTO "+CardmasterDatabase.dbname+".cmcdecks(deckno,owner)"+
					" VALUES ('"+amount+"',"+
					"'"+name.replaceAll("'","''")+"');";
					
		 			stmt.executeUpdate(statement);
		 			CardmasterLogManager.WriteLog(name + " gained deck " + amount);	
	 			}
	 			
	 			
	 		}else if (command.equals("setp")) { // set primary deck
	 			
	 			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET prideckno = "+ amount +
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " set primary deck to " + amount);	
	 		}else if (command.equals("setl")) { // set losses
	 		
	 			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET loss= " + amount +
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " losses set to " + amount);	
	 		}else if (command.equals("setw")) { // set  wins
	 		
	 			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET wins= " + amount +
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " wins set to " + amount);	
	 		
	 		
	 		}else if (command.equals("addl")) { // add loss
	 				String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET loss= loss + 1 " +
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " gains a loss");	
	 			
	 		}else if (command.equals("addw")) { // add win
	 					String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET wins = wins + 1 " +
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " gains a win");	
	 		
	 		
	 		}else if (command.equals("rmd!") || command.equals("rmdx")) {
	 				String statement = "DELETE FROM " + CardmasterDatabase.dbname+".cmcdecks"+
	 									" WHERE deckno = '"+amount+"' AND owner = '"+name+"';";
	 				stmt.executeUpdate(statement);
	 				CardmasterLogManager.WriteLog(name + " removes existing deck number " + amount);
	 			
	 		}else if (command.equals("remd")) { // remove deck
	 			File testfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + amount + ".cmc");
	 			boolean remove = true;
	 			if (testfile.exists()) { 
				 if (testfile.length() > 2) remove = false;
				 }
				 if (remove){
				 	String statement = "DELETE FROM " + CardmasterDatabase.dbname+".cmcdecks"+
	 									" WHERE deckno = '"+amount+"' AND owner = '"+name+"';";
	 				stmt.executeUpdate(statement);
				 
				 
				 
				 
				 
				 	CardmasterLogManager.WriteLog(name + " removes deck " + amount + "(Deck no longer exists)");
				 }
	 		}else if (command.equals("stpo")) { // set points
	 			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET points = " + amount + 
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " has points set to " + amount);	
	 				
	 		
	 		}else if (command.equals("addp")) { // add points
	 			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET points = points + " + amount + 
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " gains " + amount + " points");	
	 				
	 		}else if (command.equals("remp")) { // remove points
	 			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET points = points - " + amount + 
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " loses " + amount + " points");	
	 		}else if (command.equals("verf")) { // verify account
 				String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET verified = 1"+ 
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
	 			CardmasterLogManager.WriteLog(name + " verified account");	
 			}	
			else if (command.equals("pass")) { // set password (dont say what it is)
 				String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET password = '"+text.replaceAll("'","''")+"'"+ 
	 								" WHERE username = \""+name+"\";";
	 			stmt.executeUpdate(statement);
 			
 				CardmasterLogManager.WriteLog(name + " password set");	
 				System.out.println(text);
 			}
			else if (command.equals("pssw")) { // set password (say what it is)
 				String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET password = '"+text.replaceAll("'","''")+"'"+ 
	 								" WHERE username = \""+name+"\";";
 				stmt.executeUpdate(statement);
 			
 				CardmasterLogManager.WriteLog(name + " password changed by admin to " + text);	
 			}
 			else if (command.equals("logn")) { // set last login to now
 				Calendar rightNow = Calendar.getInstance();
				int month = rightNow.get(Calendar.MONTH);
				int year = rightNow.get(Calendar.YEAR);
 				int day = rightNow.get(Calendar.DAY_OF_MONTH);
 				
 				
 				String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET lastlogin = '"+year+"-"+month+"-"+day+"'"+ 
	 								" WHERE username = \""+name+"\";";
 				stmt.executeUpdate(statement);
 				
 				
 			
 			}else if (command.equals("encp")) { // run password encrypty
 				if (!( user.password.equals("BYTEPASSWORD") && user.bytepassword != null)) {
					if(CardmasterData.createUserBinaryPassword(user)) {
					
				
						String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcusers"+
	 								" SET password = 'BYTEPASSWORD'"+ 
	 								" WHERE username = \""+name+"\";";
	 					stmt.executeUpdate(statement);
	 				}
				
				}
 				
 			}
			else if (command.equals("adav")) { // add avatar part
 				
 				boolean suc = true;
				int partid = amount;
				CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
			 	if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS)  suc = false;
			 	
			 
			 	if (suc) if (partdata[partid] == null) suc= false;
			 	if (suc) if (partdata[partid].type.equals("xx")) suc = false;
				
				if (suc) {
				
				
					String fieldname = "avatar_"+partdata[partid].type;
					String previous = user.readExtraB(fieldname);
			 		String original = user.readExtraA(fieldname);
			 		if (fieldname != null && previous != null && original != null && stmt != null && con != null){
				 		}
						if (previous.indexOf(","+partid+",") == -1) {
							ChangeExtraData(name,fieldname,original,previous+partid+",",stmt,con);	
							
						}
						CardmasterLogManager.WriteLog("Adding avatar part for " + name + ":"+partid+":"  + partdata[partid].partname);
					}
						
 			}
			else if (command.equals("seav")) { // set avatar part
 				boolean suc = true;
 				int partid = amount;
				String type = text;
				CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
				if (partdata[partid] == null) suc = false;
				if (suc) if (!partdata[partid].type.equals(type) && !partdata[partid].type.equals("xx")) suc = false;
				if (suc) {
				
					String fieldname = "avatar_"+type;
		 			String previous = user.readExtraB(fieldname);
		 				if (fieldname != null && previous != null ){
				 	
		 			
		 			if (previous.indexOf(","+partid+",") != -1) {
	 					ChangeExtraData(name,fieldname,partid+"",previous,stmt,con);
	 					
		 			}
		 			if (partid == 0) {
		 				
		 				
		 				ChangeExtraData(name,fieldname,partid+"",previous,stmt,con);
		 			
		 			}
		 			}
		 			
	 				CardmasterLogManager.WriteLog("Setting avatar part for "+user.name +":" + type +" :" + partid+":"  + partdata[partid].partname);	
				}
 			}
			else if (command.equals("extr")) { // set extra data
				
				StringTokenizer blah = new StringTokenizer(text,"%");
			
 				ChangeExtraData(name,blah.nextToken(),blah.nextToken(),blah.nextToken(),stmt,con);
	 			
 			}
 			else {
 				System.out.println("Command Not Found: " + command);System.out.flush();
 				success = false;
 			}		
 			
 			
 			
 			
 			
 			
 			
 			
 			}
 		
 		catch(Exception e) {
 			
 			e.printStackTrace();
 			CardmasterLogManager.WriteLog("Couldnt userpatch for: " + name + " " + command + " " + amount + " " + text + " ( exception : "+e+" ) ");
 			
 		
 			success =false;
 		}
 		finally {
 			//System.out.println("Exiting userpatch for " + name + " (command: " + command+")");
 			try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
			return success;
 		}
 		
 		
 		
 		
 	}	
 	
 	
 }
 	
 	
 	