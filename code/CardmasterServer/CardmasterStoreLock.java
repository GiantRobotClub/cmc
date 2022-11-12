package webrunner.cardmaster;

import java.io.*;

public class CardmasterStoreLock {

	public static void disableStore(String name) {
		try{
			File file = new File(CardmasterData.DIRECTORY +"store/exists/" + name);
			if (file.exists()) file.delete();
		} catch (Exception e) {
		}
	}
	public static void enableStore(String name) {
		try{
			File file = new File(CardmasterData.DIRECTORY +"store/exists/" + name);
			file.createNewFile();
		} catch (Exception e) {
		}
	}

	static  public boolean lockStore(String name) {
	try{
			File file = new File(CardmasterData.DIRECTORY +"wait/store_" + name);
			while (file.exists()) {
				
				try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
				
				
			}
			file.createNewFile();
			return true;
		} catch (Exception e) {
			return false;
		}
	

		
		
		
	}
	static public void unlockStore(String name) {
	try{
			File file = new File(CardmasterData.DIRECTORY +"wait/store_" + name);
			if (file.exists())	file.delete();
		} catch (Exception e) {
		}
	

		
		
		
	}
	
	}