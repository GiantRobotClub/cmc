package webrunner.cardmaster;
import java.io.*;
import java.util.StringTokenizer;

public class CardmasterAvatarPart {
	public String filename;
	public String partname;
	public int price;
	public boolean availableStore;
	public boolean availableBooster;
	public String type;
	public String gender;
	public int id;
	
	
	public String toString() {
		int as = 0;
		if (availableStore) as = 1;
		int ab = 0;
		if (availableBooster) ab = 1;
		return "" + id + "#" + partname + "#" + type + "#" + filename + "#" + price +
			"#" + as + "#" + ab + "#" + gender + "#"; 
	}
	
	public CardmasterAvatarPart() {
		filename ="blank";
		partname = "Blank";
		price = 0;
		availableStore = false;
		availableBooster = false;
		type = "xx";
		gender = "b";
		id = 0;
	}
	
	public CardmasterAvatarPart(String avatarstring) {
		if (!ParseString(avatarstring)) {
			filename ="blank";
			partname = "Blank";
			price = 0;
			availableStore = false;
			availableBooster = false;
			type = "xx";
			gender = "b";
			 id = 0;
		}
	}
	public boolean ParseString(String avatarstring) {
		if (avatarstring == null) return false;
		try{
		
			StringTokenizer token = new StringTokenizer(avatarstring,"#");
			id = Integer.parseInt(token.nextToken());
			partname = token.nextToken();
			type = token.nextToken();
			filename = token.nextToken();
			price = Integer.parseInt(token.nextToken());
			availableStore = (Integer.parseInt(token.nextToken()) == 1) ? true : false;
			availableBooster = (Integer.parseInt(token.nextToken()) == 1) ? true : false;
			gender = token.nextToken();
			return true;
		} catch( Exception e) {
			return false;
		}
	}
	
}