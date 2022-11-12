package webrunner.cardmaster;
import java.sql.*;
import java.util.Calendar;
import java.io.*;


public class CardmasterMessageSystem
{
	
	
	
	
	public static String SendMessage(String to, String from, String message, String subject, int cardattach) {
		String returnstring = "none";
		if (CardmasterData.loadUser(to) == null) return "Recipient Not Found";
		if (CardmasterData.loadUser(from) == null) return "Sender Not Found";
		
		
		for (int i=0;i<5;i++) {
			returnstring = SendMessageDB(to, from, message, subject, cardattach);
			if (returnstring.equals("Success")) break;
		}
		
		
		return returnstring;
	}
	
	static String SendMessageDB(String to, String from, String message, String subject, int cardattach) {
	
		Connection con = null;
		Statement stmt = null;
		String whathappened = "nothing";
	
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			con = DriverManager.getConnection(url);
			
			
		
			
			Calendar rightNow = Calendar.getInstance();
			int month = rightNow.get(Calendar.MONTH) +1;
			int year = rightNow.get(Calendar.YEAR);
 			int day = rightNow.get(Calendar.DAY_OF_MONTH);
 			int minute = rightNow.get(Calendar.MINUTE);
 			int hour = rightNow.get(Calendar.HOUR);
 			int second = rightNow.get(Calendar.SECOND);
 			
				
			String createMessage =
			"REPLACE INTO "+CardmasterDatabase.dbname+".cmcmessage(touser,fromuser,messageread,archived,replied,messagesent,subject,message,cardattach)"+
			"values ('"+to.replaceAll("'","''")+"',"+
			"'"+from.replaceAll("'","''")+"',"+
			"'0',"+"'0',"+"'0',"+
			"'"+year+"-"+month+"-"+day+" "+ hour +":"+minute+":"+second +"',"+
			"'"+subject.replaceAll("'","''")+"',"+
			"'"+message.replaceAll("'","''")+"',"+
			"'"+cardattach+"');";
			 stmt = con.createStatement();
    		 stmt.executeUpdate(createMessage);
    		 
    		 whathappened = "Success";
    	
			
			
		}			 
		catch(Exception e) {
			e.printStackTrace();
			whathappened = "Database Error";
			}
		finally {
			try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
			 
			return whathappened;
		}	
	
	
	}
	
	
	
	public static CardmasterMessage[] GetMessageList(String user) {
		for (int i=0;i<5;i++) {
			CardmasterMessage[] messages = GetMessageListDB(user);
			if (messages != null) return messages;
			
		}
		
		return null;
	}
	
	static CardmasterMessage[] GetMessageListDB(String user) {
		ResultSet rs = null;

		Connection con = null;
		Statement stmt = null;
		CardmasterMessage[] messages = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String mesLoad = "SELECT messageid,subject,touser,fromuser,messagesent,messageread,replied,archived from "+CardmasterDatabase.dbname+".cmcmessage AS t1"+
							  " where t1.touser = \""+user+"\" order by messagesent ASC, messageid ASC;";
			rs = stmt.executeQuery(mesLoad);
			
			
			while (rs.next()) {
				if (messages == null)
					messages = new CardmasterMessage[0];
				
				String toname = rs.getString("touser");
				String fromname = rs.getString("fromuser");
				boolean messageread = rs.getInt("messageread") == 1;
				boolean archived = rs.getInt("archived") == 1;
				boolean replied = rs.getInt("replied") == 1;
				Date sentdate = rs.getDate("messagesent");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sentdate);
				int month = cal.get(Calendar.MONTH);
				int date = cal.get(Calendar.DAY_OF_MONTH);
				int year = cal.get(Calendar.YEAR);
				
			
				
				String subject = rs.getString("subject");
				CardmasterMessage message  = new CardmasterMessage();
				message = new CardmasterMessage();
				message.to = toname;
				message.from = fromname;
				message.subject = subject;
				
				message.month = month+1;
				message.day = date;
				message.year = year;
				message.read = messageread;
				message.archived = archived;
				message.replied = replied;
				message.messageid = rs.getInt("messageid");
			
			
				
				CardmasterMessage[] temp = new CardmasterMessage[messages.length];
				
				System.arraycopy(messages,0,temp,0,messages.length);
				messages = new CardmasterMessage[messages.length + 1];
				System.arraycopy(temp,0,messages,0,temp.length);
				messages[messages.length-1] = message;
				
				
				
			}
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
			messages = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
				
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return messages;
		}
	}
	
	
	public static CardmasterMessage GetMessage(int id) {
		
		for (int i=0;i<5;i++) {
			CardmasterMessage message = GetMessageDB(id);
			if (message != null) return message;
			
		}
		
		return null;
	}
	
	
	static CardmasterMessage GetMessageDB(int id) {
		
		ResultSet rs = null;

		Connection con = null;
		Statement stmt = null;
		CardmasterMessage message = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String mesLoad = "SELECT * from "+CardmasterDatabase.dbname+".cmcmessage AS t1"+
							  " where t1.messageid = \""+id+"\";";
			rs = stmt.executeQuery(mesLoad);
			if (!rs.next()) {
				
				message = null;
			}
			else {
				String toname = rs.getString("touser");
				String fromname = rs.getString("fromuser");
				boolean messageread = rs.getInt("messageread") == 1;
				boolean archived = rs.getInt("archived") == 1;
				boolean replied = rs.getInt("replied") == 1;
				Date sentdate = rs.getDate("messagesent");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sentdate);
				int month = cal.get(Calendar.MONTH);
				int date = cal.get(Calendar.DAY_OF_MONTH);
				int year = cal.get(Calendar.YEAR);
				int hour = cal.get(Calendar.HOUR);
				int minute = cal.get(Calendar.MINUTE);
				int second = cal.get(Calendar.SECOND);
				
				
				String subject = rs.getString("subject");
				String messagetext = rs.getString("message");
				int cardattach =  rs.getInt("cardattach");
				message = new CardmasterMessage();
				message.to = toname;
				message.from = fromname;
				message.subject = subject;
				message.message = messagetext;
				message.month = month+1;
				message.day = date;
				message.year = year;
				message.read = messageread;
				message.archived = archived;
				message.replied = replied;
				message.messageid = id;
				message.cardattach = cardattach;
				message.hour = hour;
				message.minute = minute;
				message.second = second;
				
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			message = null;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
					
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return message;
		}
	}
	
	
	
	
	
	public static String SetMessageRead(int id, boolean read) {
		
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			
 			
			whathappened = "Connected";
			int amount = 0;
			if (read) amount = 1;
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcmessage"+
	 								" SET messageread= '" + amount +"'"+
	 								" WHERE messageid = \""+id+"\";";
	 		stmt.executeUpdate(statement);
	 		
	 		
	 		whathappened = "Success";
		}
 		
 		catch(Exception e) {
 			
 			e.printStackTrace();
 		
 		
 			whathappened = whathappened + "\n" + e;
 		}
 		finally {
 		
 			try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
			return whathappened;
 		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public static String SetMessageArchived(int id, boolean archive) {
		
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			
 			
			whathappened = "Connected";
			int amount = 0;
			if (archive) amount = 1;
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcmessage"+
	 								" SET archived= '" + amount +"'"+
	 								" WHERE messageid = \""+id+"\";";
	 		stmt.executeUpdate(statement);
	 		
	 		
	 		whathappened = "Success";
		}
 		
 		catch(Exception e) {
 			
 			e.printStackTrace();
 		
 		
 			whathappened = whathappened + "\n" + e;
 		}
 		finally {
 		
 			try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
			return whathappened;
 		}
	
	}
	public static String SetMessageReplied(int id, boolean rep) {
		
		
		
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			
 			
			whathappened = "Connected";
			int amount = 0;
			if (rep) amount = 1;
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcmessage"+
	 								" SET replied= '" + amount +"'"+
	 								" WHERE messageid = \""+id+"\";";
	 		stmt.executeUpdate(statement);
	 		
	 		
	 		whathappened = "Success";
		}
 		
 		catch(Exception e) {
 			
 			e.printStackTrace();
 		
 		
 			whathappened = whathappened + "\n" + e;
 		}
 		finally {
 		
 			try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
			return whathappened;
 		}
	
	}
	
	public static String DeleteMessage(int id) {
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			whathappened = "Connected";
		
			String statement = "DELETE FROM "+CardmasterDatabase.dbname+".cmcmessage"+
	 								" WHERE messageid = \""+id+"\";";
	 		stmt.executeUpdate(statement);
	 		whathappened = "Success";
		}
		
 		catch(Exception e) {
 			
 			e.printStackTrace();
 		
 		
 			whathappened = whathappened + "\n" + e;
 		}
 		finally {
 		
 			try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
			return whathappened;
 		}
	}
	
	public static String RemoveAttachment(int id) {
		
		
		
		Connection con = null;
		Statement stmt = null;
		String whathappened = "Nothing";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
 			con = DriverManager.getConnection(url);
 			stmt = con.createStatement();
 			
 			
			whathappened = "Connected";
		
			String statement = "UPDATE "+CardmasterDatabase.dbname+".cmcmessage"+
	 								" SET attachcard= '0'"+
	 								" WHERE messageid = \""+id+"\";";
	 		stmt.executeUpdate(statement);
	 		
	 		
	 		whathappened = "Success";
		}
 		
 		catch(Exception e) {
 			
 			e.printStackTrace();
 		
 		
 			whathappened = whathappened + "\n" + e;
 		}
 		finally {
 		
 			try{if (stmt!=null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con!=null) con.close();}catch (Exception e) {e.printStackTrace();	}
			return whathappened;
 		}
	
	}	
	
	
	
	
	
	
	
		
	public static boolean HasNewMessage(String user) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean found = false;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
						////System.out.println(url);
			 con = DriverManager.getConnection(url);
			 String mesLoad = "SELECT touser,messageread from "+CardmasterDatabase.dbname+".cmcmessage AS t1"+
							  " where t1.messageread = '0' AND t1.touser = \""+user+"\";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(mesLoad);
			if (!rs.next()) {
				
				found = false;
			}
			else found = true;
			 
		}	
		catch (Exception e) {
			e.printStackTrace();
			found = false;
		}
		finally{
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
					
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			return found;
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
			 String createMessage = " CREATE TABLE IF NOT EXISTS "+CardmasterDatabase.dbname+".cmcmessage ("+
			 						" messageid INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,"+
			 						" touser VARCHAR(50) NOT NULL,"+
			 						" fromuser VARCHAR(50) NOT NULL,"+
			 						" messageread BOOL,"+
			 						" archived BOOL,"+
			 						" replied BOOL,"+
			 						" messagesent DATETIME,"+
			 						" subject VARCHAR(255),"+
			 						" message TEXT,"+
			 						" cardattach INT,"+
			 						
			 						" FOREIGN KEY(touser) REFERENCES "+CardmasterDatabase.dbname+".cmcusers(username),"+
									" FOREIGN KEY(fromuser) REFERENCES "+CardmasterDatabase.dbname+".cmcusers(username));";
			 stmt = con.createStatement();
    		 stmt.executeUpdate(createMessage);
    		 whathappened = "Message Table Created"; 	
    		 
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