package webrunner.cardmaster;

import java.util.HashMap;
import java.util.Random;
import java.text.DecimalFormat;
import java.sql.*;

public class CardmasterVouchers {


	public static int ERROR_CHECKSUM = -1;
	public static int ERROR_NODECK = -2;
	public static int ERROR_ALREADY = -3;
	public static int ERROR_CODELENGTH = -4;
	public static int ERROR_DECODE = -5;
	public static int ERROR_MYSQL = -6;
	public static int ERROR_USER = -7;
	public static int ERROR_OTHER  =0;

	CardmasterVouchers(){
		};
	
	
	
	public static int Redeem(String voucher, String name) {
		int returnval = ERROR_OTHER;
		
		voucher = voucher.toUpperCase();
		
		if (voucher.length() != 15) return ERROR_CODELENGTH;
		
		CardmasterUser user = CardmasterData.loadUser(name);
		if (user == null) return ERROR_USER;
		
		
		int deck =  GetDeck(voucher);
		if (deck == -3) return ERROR_CHECKSUM;
		if (deck < 0) return ERROR_DECODE; 
		
		
		if (!NotRedeemed(voucher)) {
			return ERROR_ALREADY;
		}
		
		
		for (int i=0;i<3;i++) {
			returnval = RedeemDB(voucher,name);
			if (returnval != ERROR_MYSQL) break;
		}
		if (returnval == ERROR_MYSQL) return returnval;
		
		if (returnval != 1) return returnval;
		

		// give deck
		CardmasterNewDeck ndeck  = new CardmasterNewDeck();
		String deckname = "VOUCHER_" + deck;
		ndeck.loadUser(name);
		int deckno = ndeck.giveNewPrebuiltToUser(deckname);
		if (deckno == -1) return ERROR_NODECK;
		if (deckno > 0) returnval = deckno;
		
		
		return returnval;
	}
	
	static int RedeemDB(String voucher, String name) {
		int returnval = 1;
		Connection con = null;
		Statement stmt = null;
		boolean success = true;
		int intcodes[] = GetIntCodes(voucher);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
						"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			 con = DriverManager.getConnection(url);
			 
			 String sqlstatement = "REPLACE INTO "+CardmasterDatabase.dbname+".cmcvouchers(voucher,name,deck,series,checksum	)"+
			 					   " values "+
			                       "('"+voucher.replaceAll("'","''")+"',"+
			                        "'"+name.replaceAll("'","''")+"',"+
			                        "'"+intcodes[0]+"',"+
			                        "'"+intcodes[1]+"',"+
			                        "'"+intcodes[2]+"');";
			 stmt = con.createStatement();
    		 stmt.executeUpdate(sqlstatement);
    	
			          
			          
		 }              
			catch(Exception e) {
			e.printStackTrace();
			returnval= ERROR_MYSQL;
			}
		finally {
			try{if (stmt != null) stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if (con != null) con.close();}catch (Exception e) {e.printStackTrace();	}
			 
			return returnval	;
		}
		
	}
	public static boolean NotRedeemed(String voucher) {
		for (int i=0;i<3;i++) {
			if (NotRedeemedDB(voucher)) return true;
		}	
		
		return false;
		
	}
	
	public static boolean NotRedeemedDB(String voucher) {
		boolean returnval = false;
		ResultSet rs = null;	
		Connection con = null;
		Statement stmt = null;
		voucher = voucher.toUpperCase();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+CardmasterDatabase.dbhost+"/"+CardmasterDatabase.dbname+
							"?user="+CardmasterDatabase.dbuser+"&password="+CardmasterDatabase.dbpass;
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			String checkDB = "SELECT * from "+CardmasterDatabase.dbname+".cmcvouchers where voucher = '" + voucher + "';";
			rs = stmt.executeQuery(checkDB);
			if (!rs.next()) {
				returnval = true;
			}
			else returnval = false;
			
			
		} catch(Exception e) {
			returnval = false;
			e.printStackTrace();
			
		}
		finally {
			try{if(rs != null) rs.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(stmt != null)stmt.close();}catch (Exception e) {e.printStackTrace();	}
			try{if(con != null)con.close();}catch (Exception e) {e.printStackTrace();	}
					
			
			return returnval;
			
		}
	}

	
	
	public static String ShuffleCode(String code1, String code2, String code3) {
		if (code1 == null) return null;
		if (code2 == null) return null;
		if (code3 == null) return null;
		
		if (code1.length() != 5) return null;
		if (code2.length() != 5) return null;
		if (code3.length() != 5) return null;
		
		String shuffledcode = "";
		for (int i=0;i<5;i++) {
			shuffledcode += code1.charAt(i);
			shuffledcode += code2.charAt(i);
			shuffledcode += code3.charAt(i);
			
		}
		return shuffledcode;
	}
	
	public static String[] GetShuffledCodes(String shuffledcode) {
		String[] codes = new String[3];
		codes[0] = "";
		codes[1] = "";
		codes[2] = "";
		if (shuffledcode.length() != 15) return null;
		for (int i=0;i<15;i++) {
			int codenum = i%3;
			codes[codenum] = codes[codenum] + shuffledcode.charAt(i);
		}
		return codes;
		
	}
	
	public static boolean Checksum(int seed, int series, int check) {
		Random random = new Random(seed);
		int code = 0;
		for (int i=0;i<series;i++) {
			code = random.nextInt(100000);
		}
		if (code == check) return true;
		else return false;
	}
	public static int GenerateChecksum(int seed, int series) {
		Random random = new Random(seed);
		int code = 0;
		for (int i=0;i<series;i++) {
			code = random.nextInt(100000);
		}
		return code;	
	}
	public static String GenerateCode(int deck, int series) {
		int code1 = deck;
		int code2 = series;
		int code3 = GenerateChecksum(deck, series);
		String subcode1 = ConvertSubCode(code1,1);
		String subcode2 = ConvertSubCode(code2,2);
		String subcode3 = ConvertSubCode(code3,3);
		
		String code = ShuffleCode(subcode1,subcode2,subcode3);
		
		return code;
		
	}
	
	public static int GetDeck(String code) {
		String[] codes = GetShuffledCodes(code);
		if (codes == null) return -1;
		if (codes.length != 3) return -2;
		
		int code1 = ConvertSubCode(codes[0],1);
		int code2 = ConvertSubCode(codes[1],2);
		int code3 = ConvertSubCode(codes[2],3);
		
		boolean check = Checksum(code1,code2,code3);
		if (!check) return -3;
		
		return code1;
		
		
	}
	public static int[] GetIntCodes(String code) {
		String[] codes = GetShuffledCodes(code);
		if (codes == null) return null;
		if (codes.length != 3) return null;
		
		int code1 = ConvertSubCode(codes[0],1);
		int code2 = ConvertSubCode(codes[1],2);
		int code3 = ConvertSubCode(codes[2],3);
		
		boolean check = Checksum(code1,code2,code3);
		if (!check) return null;
		
		int[] intcodes = new int[3];
		
		intcodes[0] = code1;
		intcodes[1] = code2;
		intcodes[2] = code3;
		
		
		
		return intcodes;
		
		
	}
	public static String ConvertSubCode(int number, int set) {
		if (number >= 100000) return null;
		if (number < 0) return null;
		
		DecimalFormat FiveDigits = new DecimalFormat("00000");
		
		String codestring = FiveDigits.format(number);
		
		String outstring = "";
		
		for (int i=0;i<codestring.length();i++) {
			String letter = "" + codestring.charAt(i);
			int digit = Integer.parseInt(letter);
			outstring = outstring + GetRandomLetterReplacement(digit,set);
		} 

		return outstring;
	}
	
	public static int ConvertSubCode(String code, int set) {
		if (code.length() != 5) return -1;
		int decode =0;
		for (int i=0;i<code.length();i++) {
			String letter = "" + code.charAt(i);
			int digit = GetLetterReplacement(letter,set);
			
			int exp = (int)java.lang.Math.pow(10,4-i);
			
			int amount = digit * exp;
			
	if (CardmasterData.DEBUGMODE)		System.out.println(i +":" + digit +":"+ amount + ":" + exp);
			decode = decode + amount;
			
		}
		
		return decode;
	}
	
	
	public static String[][] GetLetterSet(int set) {
	
		if (set == 2) {
		String[][]	letter=
						{ {"A","C","M"},
			
						{"V","L"},
						{"R","B","Z"},
					 	{"F","N"},
			 			{"Q","E","J"},
						{"S","W"},
			 			{"D","K","X"},
						{"T","G"},
			 			{"U","O","Y"},
			 			{"H","P","I"}};
			 			
			 		
			 		return letter;
			
			
		}
		else if (set == 1) {
			
			String[][]	letter=
			
			{{"E","U","C"},
			{"A","F","L"},
			{"Z","T"},
			{"G","B","K"},
			{"R","S"},
			{"M","H","X"},
			{"N","D"},
			{"O","J","I"},
			{"P","V"},
			{"Q","W","Y"}};
				return letter;
			
		}	
		
		else {
			
			String[][]	letter=
			
			
			{{"D","H"},
			{"W","I","Z"},
			{"X","A"},
			 {"F","S","Y"},
			 {"L","T"},
			 {"J","G","B"},
			{"U","M"},
			{"C","E","Q"},
			{"V","R","N"},
			{"K","P","O"}};
			
			
				return letter;
			
		}
	}
	
	public static String GetRandomLetterReplacement(int number, int set) {
		String[] letters = GetLetterReplacement(number,set);
		
		Random rand = new Random();
		
		int i = rand.nextInt(letters.length);
		String letter = letters[i];
		
		return letter;
	}
	public static String[] GetLetterReplacement(int number,int set) {
		String[][] letters = GetLetterSet(set);
		
		return  letters[number];	
	}
	
	public static int GetLetterReplacement(String letter, int set) {
		
			String[][] letters = GetLetterSet(set);
			for (int i=0;i<10;i++) {
				String[] temp = letters[i];
				for (int j=0;j<temp.length;j++) {
					if (temp[j].equals(letter)) return i;
				}
			}
		return -1;
	}
	
	
	
}