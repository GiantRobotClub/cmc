package webrunner.cardmaster;
import java.io.*;

public class CardmasterAvatar {
	public CardmasterAvatarPart background;
	public CardmasterAvatarPart back;
	public CardmasterAvatarPart front;
	public CardmasterAvatarPart basehead;
	public CardmasterAvatarPart basebody;
	public CardmasterAvatarPart baselegs;
	public CardmasterAvatarPart shoes;
	public CardmasterAvatarPart hands;
	public 	CardmasterAvatarPart pants;
	public CardmasterAvatarPart shirt;
	public CardmasterAvatarPart jacket;
	public CardmasterAvatarPart hair;
	public CardmasterAvatarPart hat;
	public CardmasterAvatarPart[] partdata;
	public CardmasterAvatar() {
		
			
	}
	public boolean loadParts(String name) {
		CardmasterUser user = CardmasterData.loadUser(name);
		if (user == null) return false;
		partdata = CardmasterData.loadAvatarParts();
		
		
		String workstring;
		
		if (user.readExtraA("avatar_basehead") == null) {
		
			CardmasterData.GiveBasicAvatars(name);
			partdata = null;
			return false;
		}
		workstring = user.readExtraA("avatar_background");
		if (workstring == null) workstring = "0";
		background = partdata[Integer.parseInt(workstring)];
		
		

		
		
		
	
		workstring = user.readExtraA("avatar_basebody");
		if (workstring == null) workstring = "0";
		basebody = partdata[Integer.parseInt(workstring)];
		
		
		
		
		
	
		workstring = user.readExtraA("avatar_baselegs");
		if (workstring == null) workstring = "0";
		baselegs = partdata[Integer.parseInt(workstring)];
		
		
		
		
		
	
		workstring = user.readExtraA("avatar_basehead");
		if (workstring == null) workstring = "0";
		basehead = partdata[Integer.parseInt(workstring)];
		
		
		
		
		
	
		workstring = user.readExtraA("avatar_pants");
		if (workstring == null) workstring = "0";
		pants = partdata[Integer.parseInt(workstring)];
		
		
		
		
		
	
		workstring = user.readExtraA("avatar_hands");
		if (workstring == null) workstring = "0";
		hands = partdata[Integer.parseInt(workstring)];
		
		
		
		
		
	
		workstring = user.readExtraA("avatar_shoes");
		if (workstring == null) workstring = "0";
		shoes = partdata[Integer.parseInt(workstring)];
		
		
		
		
		
	
		workstring = user.readExtraA("avatar_shirt");
		if (workstring == null) workstring = "0";
		shirt = partdata[Integer.parseInt(workstring)];
		
		
		
		
	
		workstring = user.readExtraA("avatar_jacket");
		if (workstring == null) workstring = "0";
		jacket = partdata[Integer.parseInt(workstring)];
	//	System.out.println(jacket.filename);
		
		
	
		workstring = user.readExtraA("avatar_front");
		if (workstring == null) workstring = "0";
		front = partdata[Integer.parseInt(workstring)];
		
		
		
	
		workstring = user.readExtraA("avatar_back");
		if (workstring == null) workstring = "0";
		back = partdata[Integer.parseInt(workstring)];
		
		
		
		
	
		workstring = user.readExtraA("avatar_hair");
		if (workstring == null) workstring = "0";
		hair = partdata[Integer.parseInt(workstring)];
		
								
		
		
	
		workstring = user.readExtraA("avatar_hat");
		if (workstring == null) workstring = "0";
		hat = partdata[Integer.parseInt(workstring)];
		
		
		partdata = null;
		return true;
	}
	
	
}