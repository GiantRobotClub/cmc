package webrunner.cardmaster;
import java.io.*;
public class CardmasterEmergencyPasswordRecover {
	
	public static void main(String[] args) {
		//System.out.println("Unencrypting all passwords");
		CardmasterUser[] users = CardmasterData.loadUserData();
		for (int i =0;i<users.length;i++) {
			if (users[i] != null) {
				
				users[i].password = users[i].decryptedPassword();
				users[i].encryptedpassword = false;			
				
				
				
				
				
				
			}	
			
		}
		saveusers(users);
		
		
	}
		public static boolean saveusers(CardmasterUser[] users) {
					File waitfile = new File(CardmasterData.DIRECTORY +"userwait");
	
		try{
		while (waitfile.exists()) {try{Thread.currentThread().sleep(webrunner.cardmaster.CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
			 };
		waitfile.createNewFile();
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "users.csc"); 
			PrintWriter out = new PrintWriter(writer);
			out.print(users[1]);
			for (int i =2;i<users.length;i++) {
				out.print(System.getProperty("line.separator") + users[i]);
				
				
			}
			out.close();writer.close();
			waitfile.delete();
			return true;
			
	}catch(Exception e) {waitfile.delete();}
		return false;
	}


}
		
	
