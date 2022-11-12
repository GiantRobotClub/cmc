package webrunner.cardmaster;

import java.io.*;
import java.util.StringTokenizer;

public class CardmasterAvatarPartEditor {
	public CardmasterUser user;
	public CardmasterAvatarPart[] partdata;
	public boolean login = false;

	public void login(String name) {
		if (login){	partdata = CardmasterData.loadAvatarParts(); return; } // already logged in
		user = CardmasterData.loadUser(name);
		if (user == null) return;
		boolean test = true;
		if (user.readExtraA("avataradmin") == null) test = false;
		if (test) if (user.readExtraA("avataradmin").equals("yes")) test = true; else test = false;
		
		if (!test) if (user.readExtraA("admin") == null) return;
		if (!test) if (!user.readExtraA("admin").equals("yes")) return; else test=true;
		if (!test) return;
		login = true;
		partdata = CardmasterData.loadAvatarParts();
	}
	
	public CardmasterAvatarPartEditor() {
		login = false;
	}
	
	public boolean CreateNewPart(int id, String name, String filename, String type,
									int price, boolean availableStore, boolean availableBooster,
									String gender){
	//	System.out.println("Starting");
		if (!login) return false;								
		

		if (partdata[id] != null) return false; // part already exists for part id
	
	//	System.out.println(name + filename + type + gender);
		if (name == null || filename == null || type == null || gender == null) return false;
	
		if (name.length() == 0 || filename.length() == 0 || type.length() ==0 || gender.length() == 0);
	
		CardmasterAvatarPart part = new CardmasterAvatarPart();
		part.partname = name;
		part.filename = filename;
		part.id = id;
		part.type = type;
		part.gender = gender;
		part.price = price;
		part.availableStore = availableStore;
		part.availableBooster = availableBooster;
		partdata[id] = part;
		return true;	
	}
	
	
	
	
	// to use this function part = getNewPartCopy[partid], edit part, then partdata[partid] = part;
	public CardmasterAvatarPart getNewPartCopy(int id) {
		if (!login) return null;
		CardmasterAvatarPart part = new CardmasterAvatarPart();
		if (partdata[id] == null) return null;
		CardmasterAvatarPart part2 = partdata[id];
		part.partname = part2.partname;
		part.filename = part2.filename;
		part.id = part2.id;
		part.type = part2.type;
		part.gender = part2.gender;
		part.price = part2.price;
		part.availableStore = part2.availableStore;
		part.availableBooster = part2.availableBooster;
		return part;
	}
	public boolean SaveParts() {
		if (!login) return false;
		try{
			
			File file1 = new File(CardmasterData.SITEFOLDER + "avatar.cmc");
			File file2 = new File(CardmasterData.DIRECTORY + "avatar.cmc");
			FileWriter writer = new FileWriter(file1);
			PrintWriter out = new PrintWriter(writer);
			FileWriter writer2 = new FileWriter(file2);
			PrintWriter out2 = new PrintWriter(writer2);
			for (int i=0;i<partdata.length;i++) {
				if (partdata[i] != null) {
					out.println(partdata[i]);
					out2.println(partdata[i]);
				}
			}
			
			out.close();
			out2.close();
			writer.close();
			writer2.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
}