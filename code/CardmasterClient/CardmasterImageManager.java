import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.HashMap;

import java.awt.image.BufferedImage;
import javax.imageio.*;

public class CardmasterImageManager {

//*1.5	
	HashMap <String,Image> images;
//1.5*/	
	String urlcodebase= "/";
	public CardmasterImageManager(String codebase) {
		urlcodebase = codebase;
  //*1.5
		images = new HashMap<String,Image>();
		//1.5*/
	}
	
	
	
	Font small_f;
	Font small_f3;
	Font small_diconfont;
	Font large_f;
	Font large_f2;
	Font large_f3;
	Font large_diconfont;
	Font serif;
	Font sans;
	Font damfont;
		
	public void LoadFonts() {
		
				try{
			
				   java.io.InputStream fontStream = getClass().getClassLoader().getResourceAsStream( "FreeSerif.ttf" );
                     serif  =
                            Font.createFont( Font.TRUETYPE_FONT, fontStream );
					fontStream.close();
				
				fontStream = getClass().getClassLoader().getResourceAsStream( "FreeSans.ttf" );
                    sans =
                            Font.createFont( Font.TRUETYPE_FONT, fontStream );
					fontStream.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
			
			small_f = sans.deriveFont(Font.PLAIN,11);
			small_f3 = serif.deriveFont(Font.PLAIN,17);
			
			
			large_f = sans.deriveFont(Font.PLAIN,12);
			large_f2 = sans.deriveFont(Font.PLAIN,10);
			large_f3 = serif.deriveFont(Font.PLAIN,18);
		
			damfont = sans.deriveFont(Font.PLAIN,30);
		
		
	}
	public Font getFont(String fname) {
		if (fname.equals("sf")) {
			return small_f;
		}
		else if (fname.equals("sf3")) {
			return small_f3;
		}
		else if (fname.equals("lf")) {
			return large_f;
		}
		else if (fname.equals("lf2")) {
			return large_f2;
		}
		else if (fname.equals("lf3")) {
			return large_f3;
		}

		else if (fname.equals("dam")) {
			return damfont;
		}
		return large_f;
	}
	
	public boolean LoadImage(String filename) {
	
//*1.5	

		if (images.get(filename) != null) return false;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		try{
			Image image = toolkit.getImage(new URL(urlcodebase  + filename));
			images.put(filename,image);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;
//1.5*/
		
		/*1.4
		return true;	
		//1.4*/

	}
	
	public Image getImage(String filename) {
		
		//1.5
//*1.5	
		if (!images.containsKey(filename) || images.get(filename) == null) {
			LoadImage(filename);
		} 
				
		Image image = images.get(filename);
		
		return image;
//1.5*/
		
		/*1.4
		
		 
		 try{
		 	Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage(new URL(urlcodebase  + filename));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		//1.4*/
		 
		 
	}
	
	public void LoadCommonCMCImages() {
		LoadFonts();
//*1.5
		
		LoadImage("images/cardback.gif");
		
		LoadImage("images/cardback_l_2.jpg");
		LoadImage("images/cardback_l_6.jpg");
		LoadImage("images/cardback_l_8.jpg");
		LoadImage("images/cardback_l_10.jpg");
		LoadImage("images/cardback_l_12.jpg");
		LoadImage("images/cardback_l_14.jpg");
		
		LoadImage("images/cardback_s_2.jpg");
		LoadImage("images/cardback_s_6.jpg");
		LoadImage("images/cardback_s_8.jpg");
		LoadImage("images/cardback_s_10.jpg");
		LoadImage("images/cardback_s_12.jpg");
		LoadImage("images/cardback_s_14.jpg");		
		
		LoadImage("images/blockdizzy.gif");
		LoadImage("images/dizzy.gif");
		
		LoadImage("images/abilitydizzy.gif");
		LoadImage("images/abilitypointer.gif");
		
		
		LoadImage("images/dmana.gif");
		LoadImage("images/lmana.gif");
		LoadImage("images/gmana.gif");
		
		
		LoadImage("images/exp_0.gif");
		LoadImage("images/exp_1.gif");
		LoadImage("images/exp_2.gif");
		LoadImage("images/exp_3.gif");
		LoadImage("images/exp_4.gif");
		
		LoadImage("images/speed_0.gif");
		LoadImage("images/speed_1.gif");
		LoadImage("images/speed_2.gif");
		LoadImage("images/speed_3.gif");
		LoadImage("images/speed_4.gif");
		LoadImage("images/speed_5.gif");
//1.5*/
	}
	
	
}