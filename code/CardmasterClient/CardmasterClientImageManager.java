import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class CardmasterClientImageManager {
	public static final int GMANADOT = 1;
	public static final int DMANADOT = 2;
	public static final int LMANADOT = 3;
	public static final int ABILARROW= 4;
	public static final int ABILDIZZY= 5;
	public static final int DIZZY    = 6;
	public static final int DBLDIZZY = 7;
	
	
	public static final int SPEED_S = 10;
	public static final int SPEED_A = 11;
	public static final int SPEED_B = 12;
	public static final int SPEED_C = 13;
	public static final int SPEED_D = 14;
	public static final int SPEED_E = 15;
		
	
	
	public String urlcodebase = "";
	
	public CardmasterClientImageManager(String urlcodebase) {
		this.urlcodebase =urlcodebase;
	}
	public BufferedImage GetImage(int type) {
		String urlfilename;
		switch (type) {
			case GMANADOT : urlfilename = "images/gmana.gif"; break;
			case DMANADOT : urlfilename = "images/dmana.gif"; break;
			case LMANADOT : urlfilename = "images/dmana.gif"; break;
			case SPEED_S  : urlfilename = "images/speed_0";   break;
			case SPEED_A  : urlfilename = "images/speed_1";   break;
			case SPEED_B  : urlfilename = "images/speed_2";   break;
			case SPEED_C  : urlfilename = "images/speed_3";   break;
			case SPEED_D  : urlfilename = "images/speed_4";   break;
			case SPEED_E  : urlfilename = "images/speed_5";   break;
			
			
		}
		
		
		
		
		
		
		
		return null;
	}
	
	
	
	public BufferedImage GetBackground(int backgroundset, int color ) {
		
		return null;
	}
	
	
	
}