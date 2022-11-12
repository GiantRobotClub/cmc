package webrunner.cardmaster;
import java.io.*;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;
import javax.imageio.*;
//import


public class CardmasterAvatarManager {
	public static boolean buyPart(String name, String type, int partid) {
		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) return false;
 		CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
 		if (partdata[partid] == null) return false;
 		if (!partdata[partid].type.equals(type)) return false;
 		CardmasterAvatarPart[] ownedparts = getParts(name,type);
 		for (int i=0;i<ownedparts.length;i++) {
 			if (ownedparts[i].id == partid) return false;
 		}
		if (user.points < partdata[partid].price) return false;
		if (!partdata[partid].availableStore) return false;
		if (!givePart(name,partid)) return false;
		CardmasterData.userpatch(name,"remp",partdata[partid].price,"x");
		return true;
	}
	/*
	public static boolean setPart(String name, String type, int partid) {
		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) return false;
 		CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
 		//if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS) return false;
 		if (partdata[partid] == null) return false;
 		//if (partdata[partid].type.equals("xx")) return false;	
 	
 		if (!partdata[partid].type.equals(type) && !partdata[partid].type.equals("xx")) return false;
 		
 		String fieldname = "avatar_"+type;
 		String previous = user.readExtraB(fieldname);
 		if (previous.indexOf(","+partid+",") != -1) {
 			CardmasterData.userpatch(name,"extr",0,fieldname + "%"+partid+"%"+previous+"%");
 			return true;	
 		}
 		if (partid == 0) {
 			CardmasterData.userpatch(name,"extr",0,fieldname + "%0%"+previous+"%");
 			return true;
 		}
 		return false;
 		
	}
	
	
	public static boolean givePart(String name, int partid) {
	//	File file = new File(CardmasterData.DIRECTORY + "userpatch.csc");
		//File waitfile = new File(CardmasterData.DIRECTORY +"userpatchwait");
	//	while (file.exists()) {try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}}
	
		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) return false;
 		CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
 		if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS) return false;
 		if (partdata[partid] == null) return false;
 		if (partdata[partid].type.equals("xx")) return false;
 		String fieldname = "avatar_"+partdata[partid].type;
 		String previous = user.readExtraB(fieldname);
 		String original = user.readExtraA(fieldname);
 		if (previous == null) return false;
 		if (previous.indexOf(","+partid+",") == -1) {
 				CardmasterData.userpatch(name,"adav",partid,"x");
 				return true;
 		}	
 		return false;
	}
	*/
	public static boolean givePart(String name, int partid) {
		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) return false;
		CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
 		if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS) return false;
 		if (partdata[partid] == null) return false;
 		if (partdata[partid].type.equals("xx")) return false;
 		String fieldname = "avatar_"+partdata[partid].type;
 		String previous = user.readExtraB(fieldname);
 		if (previous != null)
 		if (previous.indexOf(","+partid+",") != -1) return false;
 		return CardmasterData.userpatch(name,"adav",partid,"x");
	}
	public static boolean setPart(String name, String type, int partid) {
		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) return false;
 	System.out.println("user oK");
		CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
 	//	if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS) return false;
 		if (partdata[partid] == null) return false;
 	System.out.println("partdata ok");
 		if (!partdata[partid].type.equals(type) && !partdata[partid].type.equals("xx")) return false;
 	System.out.println("type ok");
 		String fieldname = "avatar_"+type;
 		String previous = user.readExtraB(fieldname);
 		String original = user.readExtraA(fieldname);
 		if (previous == null) return false;
 	System.out.println("previous oK");
 		if (previous.indexOf(","+partid+",") == -1 && partid != 0) return false;
 	System.out.println("ownpart oK");	
		return CardmasterData.userpatch(name,"seav",partid,type);
		
		
	}
	
	
	public static CardmasterAvatarPart[] getParts(String name, String type) {
		CardmasterUser user = CardmasterData.loadUser(name);
 		if (user == null) return null;
 		CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
 	//	if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS) return false;
 	//	if (partdata[partid] == null) return false;
 	//	if (partdata[partid].type.equals("xx")) return false;
 		String fieldname = "avatar_"+type;
 		String previous = user.readExtraB(fieldname);
 		StringTokenizer tokenizer = new StringTokenizer(previous,",");
 		CardmasterAvatarPart[] parts = new CardmasterAvatarPart[0];
 		while (tokenizer.hasMoreTokens()) {
 			String tok = tokenizer.nextToken();
 		//		System.out.println(tok);
 			CardmasterAvatarPart[] temp = new CardmasterAvatarPart[parts.length+1];
 			System.arraycopy(parts,0,temp,0,parts.length);
 			temp[parts.length] = partdata[Integer.parseInt(tok)];
 			parts = new CardmasterAvatarPart[temp.length];
 			System.arraycopy(temp,0,parts,0,parts.length);
 			
 		}
		return parts;
	}
	
	
	
	
	
	public CardmasterAvatarManager() {
	}
	
	public boolean BuildAvatar(String name) {
		if (name == null) return false;
		
		BufferedImage finishedAvatar = new BufferedImage(60,95,BufferedImage.TYPE_3BYTE_BGR);
		CardmasterAvatar avatar = new CardmasterAvatar();
		if (!avatar.loadParts(name)) {System.out.println("Couldnt load parts"); return false;}
	
		BufferedImage avatar_background,avatar_back,avatar_basehead,avatar_baselegs,
			avatar_basebody,avatar_hands,avatar_pants,avatar_shirt,avatar_shoes,
			avatar_front,avatar_jacket,avatar_hair,avatar_hat,avatar_blank;
	
		try {
			avatar_blank = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/blank.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			avatar_background = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.background.filename + ".png"));
		}
		catch (Exception e) {
				avatar_background = avatar_blank;
		}
		
		
		
		try {
			avatar_back = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.back.filename + ".png"));
		}
		catch (Exception e) {
				avatar_back = avatar_blank;
		}
		
		
		
		
		try {
			avatar_basehead = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.basehead.filename + ".png"));
		}
		catch (Exception e) {
				avatar_basehead = avatar_blank;
		}
		
		
		try {
			avatar_basebody = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.basebody.filename + ".png"));
		}
		catch (Exception e) {
				avatar_basebody = avatar_blank;
		}

		
		
		try {
			avatar_baselegs = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.baselegs.filename + ".png"));
		}
		catch (Exception e) {
				avatar_baselegs = avatar_blank;
		}

		
		
		try {
			avatar_shoes = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.shoes.filename + ".png"));
		}
		catch (Exception e) {
				avatar_shoes = avatar_blank;
		}

		
		
		try {
			avatar_pants = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.pants.filename + ".png"));
		}
		catch (Exception e) {
				avatar_pants = avatar_blank;
		}

		

		
		try {
			avatar_shirt = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.shirt.filename + ".png"));
		}
		catch (Exception e) {
				avatar_shirt = avatar_blank;
		}

		
		
		try {
			avatar_jacket = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.jacket.filename + ".png"));
		}
		catch (Exception e) {
				avatar_jacket = avatar_blank;
		}

				
		try {
			avatar_hands = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.hands.filename + ".png"));
		}
		catch (Exception e) {
				avatar_hands = avatar_blank;
		}
		
		
		try {
			avatar_hair = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.hair.filename + ".png"));
		}
		catch (Exception e) {
				avatar_hair = avatar_blank;
		}

		
		
		try {
			avatar_hat = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.hat.filename + ".png"));
		}
		catch (Exception e) {
				avatar_hat = avatar_blank;
		}

		
		
		try {
			avatar_front = ImageIO.read(
					new File(CardmasterData.SITEFOLDER + "avatarparts/" +
					avatar.front.filename + ".png"));
		}
		catch (Exception e) {
				avatar_front = avatar_blank;
		}

		Graphics2D g2 = (Graphics2D)finishedAvatar.getGraphics();
		
		g2.drawImage(avatar_background,null,0,0);			
		g2.drawImage(avatar_back,null,0,0);
		g2.drawImage(avatar_basehead,null,0,0);			
		g2.drawImage(avatar_basebody,null,0,0);			
		g2.drawImage(avatar_baselegs,null,0,0);			
		g2.drawImage(avatar_shoes,null,0,0);
				
		g2.drawImage(avatar_pants,null,0,0);
		g2.drawImage(avatar_hands,null,0,0);
					
		g2.drawImage(avatar_shirt,null,0,0);			
		g2.drawImage(avatar_jacket,null,0,0);			
		g2.drawImage(avatar_hair,null,0,0);	
		g2.drawImage(avatar_hat,null,0,0);	
				
		g2.drawImage(avatar_front,null,0,0);
		g2.dispose();
		File f = new File(CardmasterData.SITEFOLDER + "avatars/" +name+ ".png");
		try{
			if (!ImageIO.write(finishedAvatar,"png",f)) {System.out.println("No imageio.write");return false;}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean TestCreateAvatar() {
		BufferedImage bi = new BufferedImage(60,95,BufferedImage.TYPE_3BYTE_BGR);
		try {
	
			Graphics2D g2 = (Graphics2D)bi.getGraphics();
			BufferedImage avatar_background = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/b_background_grey.png"));



			BufferedImage avatar_basehead = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/m_base_head_1.png"));
			
			
			BufferedImage avatar_basebody = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/m_base_body_1.png"));
			
			BufferedImage avatar_baselegs = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/m_base_legs_1.png"));
				
				
			
			BufferedImage avatar_shoes = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/b_shoes_running_black.png"));
			
			BufferedImage avatar_pants = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/b_pants_cargo_tan.png"));
						
			BufferedImage avatar_shirt = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/m_tshirt_white.png"));
					
						
			BufferedImage avatar_jacket = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/m_jacket_webrunner.png"));
						
						
			BufferedImage avatar_hair = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/b_hair_black_poofy.png"));
									
			BufferedImage avatar_front = ImageIO.read(
				new File(CardmasterData.SITEFOLDER + "avatarparts/b_front_sword_basic.png"));
			
			g2.drawImage(avatar_background,null,0,0);			
			g2.drawImage(avatar_basehead,null,0,0);			
			g2.drawImage(avatar_basebody,null,0,0);			
			g2.drawImage(avatar_baselegs,null,0,0);			
			g2.drawImage(avatar_shoes,null,0,0);			
			g2.drawImage(avatar_pants,null,0,0);			
			g2.drawImage(avatar_shirt,null,0,0);			
			g2.drawImage(avatar_jacket,null,0,0);			
			g2.drawImage(avatar_hair,null,0,0);			
			g2.drawImage(avatar_front,null,0,0);			
			
			
			File f = new File(CardmasterData.SITEFOLDER + "avatars/webrunnertest.jpeg");
			File f2 = new File(CardmasterData.SITEFOLDER + "avatars/webrunnertest.png");
			
			ImageIO.write(bi,"jpeg",f);
		
			ImageIO.write(bi,"png",f2);		
			g2.dispose();						
			return true;
		}
		catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	public boolean TestAvatarManager() {
		BufferedImage bi = new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
		try {
			Graphics2D g2 = (Graphics2D)bi.getGraphics();
		//	Toolkit toolkit = Toolkit.getDefaultToolkit();
			g2.setColor(Color.GRAY);
			g2.fillRect(10,10,80,80);
				
		//	g2.drawImage(toolkit.getImage(new URL("file:d:/cmc/site/images/exp_0.gif")),0,0,75,75,null);
		//	g2.drawImage(toolkit.getImage(new URL("file:d:/cmc/site/images/exp_1.gif")),25,25,75,75,null);
			
		//	RescaleOp op = new RescaleOp(1.0f,0f,null);
		//	ColorConvertOp op = new ColorConvertOp(bi.getColorModel(),null);
			
			BufferedImage bgraph2 = ImageIO.read(new File("d:/cmc/site/images/exp_0.gif"));
			g2.drawImage(bgraph2,null,0,0);
			
			
			BufferedImage bgraph = ImageIO.read(new File("d:/cmc/temp/test.png"));
			g2.drawImage(bgraph,null,25,25);
		
			
			
			File f = new File("D:/cmc/temp/testimage.jpg");
			ImageIO.write(bi,"jpeg",f);
		
			g2.dispose();
			
			return true;
			
		}
		catch (Exception e) {e.printStackTrace(); return false;}
	}
}