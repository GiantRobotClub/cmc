package webrunner.cardmaster;
import java.io.*;
import java.util.StringTokenizer;
public class CardmasterPersonalStore {
	
	public StoreEntry[] fullStore;
	String owner;
	public boolean initfinish = false;
	String accessor;
	String storename;

	public boolean storeactive = false;
	public CardmasterPersonalStore(String name) {
		int count = 0;
		while(! CardmasterData.lockStore(name)) {
			count++;
			if (count >= 100000) {
				 CardmasterData.unlockStore(name);
				return;
			}
			
		}
		owner = name;
		CardmasterUser user = CardmasterData.loadUser(owner);
		
		if (user == null) { CardmasterData.unlockStore(name); return; }
		

		if (user.readExtraA("store") != null)
			if (user.readExtraA("store").equals("yes"))
				storeactive = true; else storeactive = false;
		
				
		if (user.readExtraB("store") != null)
			storename = user.readExtraB("store");
		else storename = "Generic Store";
			
		fullStore = new StoreEntry[CardmasterData.NUMBER_OF_CARDS];
		for (int i=0;i<CardmasterData.NUMBER_OF_CARDS;i++) fullStore[i] = new StoreEntry();
		CardmasterData.unlockStore(name);
		if (loadStore()) {
		
		//if (! loadStore()) if (!createNewStore()) return;
				
		initfinish = true;
		}
	}
	public boolean lockStore() {
		return CardmasterData.lockStore(owner);
		
	}
		
	synchronized public boolean loadStore() {
		FileReader reader = null;
		BufferedReader in = null;
		File file = new File(CardmasterData.DIRECTORY +"store/store_" + owner + ".csc");
		if (! file.exists()) return false;
		try{
		
			reader = new FileReader(file);
			 in = new BufferedReader(reader);
			try{
			
		//	storename = in.readLine();
			String inputline;
			while ((inputline = in.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(inputline);
				int cardid = Integer.parseInt(tokenizer.nextToken());
				int number = Integer.parseInt(tokenizer.nextToken());
				int cost = Integer.parseInt(tokenizer.nextToken());
				fullStore[cardid].amount = number;
				fullStore[cardid].cost =cost;
				
				
				
				
			}
			in.close();
			reader.close();
			} catch (Exception e) {
				in.close();
				reader.close();
				
			}
		} catch (Exception e) { 
				if (in != null) {
				try{in.close();}catch(Exception ex){ex.printStackTrace();}
				
				
			}
			if (reader != null) {
				try{reader.close();}catch(Exception ex){ex.printStackTrace();}
			}
		
		
		
		
		return false; }
		
		return true;
		
	}
	public String getStoreName() {
		return storename;
		
	}
	
	public void setNumber(int cardid, int val) {
		fullStore[cardid].amount = val;
	}
	
	public void setCost(int cardid, int val) {
		fullStore[cardid].cost = val;
	}
	
	public int getNumber(int cardid) {
		return fullStore[cardid].amount;
	}
	
	public int getCost(int cardid) {
		return fullStore[cardid].cost;
	}

	synchronized public boolean saveStore() {
		FileWriter writer = null;
		PrintWriter out = null;
		File file = new File(CardmasterData.DIRECTORY + "store/store_" + owner + ".csc");
		try{
			
			writer = new FileWriter(file);
			out = new PrintWriter(writer);
		//	out.println(storename);
			try {
			
			
			for (int i=0;i<CardmasterData.NUMBER_OF_CARDS;i++) {
				out.println(i + " " + fullStore[i].amount + " " + fullStore[i].cost);
			}
				
				
				
			
			out.close();
			writer.close();
			CardmasterData.unlockStore(owner);
			
			return true;
			
			} catch (Exception e) {
			out.close();
			writer.close();
				return true;
			}
			
			
		} catch (Exception e) { 
			if (out != null) {
				try{out.close();}catch(Exception ex){ex.printStackTrace();}
				
				
			}
			if (writer != null) {
				try{writer.close();}catch(Exception ex){ex.printStackTrace();}
			}
		
		CardmasterData.unlockStore(owner); return false; 
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}

class StoreEntry {
	public StoreEntry() {
		amount = 0;
		cost = 0;
	}
	public StoreEntry(int amount, int cost) {
		this.amount = amount;
		this.cost = cost;
	}

	public int amount;
	public int cost;
}