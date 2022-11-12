package webrunner.cardmaster;

import java.io.*;
import java.util.StringTokenizer;


public class CardmasterListGames {
	
	public String[] name1;
	public String[] name2;
	public String[] roomno;
	String[] lines;
	
		
	
public 	CardmasterListGames(){}
	
	public void parseLines() {
		for (int i=0;i<lines.length;i++) {
			StringTokenizer tokenizer = new StringTokenizer(lines[i],"#");
			String type = tokenizer.nextToken();
			if (type.equals("GAME")) {
				//System.out.println("Parsing : " + lines[i]);
				String[] test1 = new String[name1.length];
				 String[] test2 = new String[name2.length];
				 String[] testno = new String[roomno.length];
				System.arraycopy(name1,0,test1,0,name1.length);
				System.arraycopy(name2,0,test2,0,name2.length);
				System.arraycopy(roomno,0,testno,0,roomno.length);
				name1 = new String[name1.length+1];
				name2 = new String[name2.length+1];
				roomno = new String[roomno.length+1];
				System.arraycopy(test1,0,name1,0,test1.length);
				System.arraycopy(test2,0,name2,0,test2.length);
				System.arraycopy(testno,0,roomno,0,testno.length);			
				
				roomno[roomno.length-1] = tokenizer.nextToken();
				name1[name1.length-1] = tokenizer.nextToken();
				name2[name2.length-1] = tokenizer.nextToken();
				
			}
		
		
		}
		
	}
	
	public void loadGames() {
		name1 = new String[0];
		name2 = new String[0];
		roomno = new String[0];
		try{
		File file = new File(CardmasterData.DIRECTORY + "games.csc");
		if (!(file.exists())) return;
		FileReader reader = new FileReader(	CardmasterData.DIRECTORY + "games.csc");
		BufferedReader in = new BufferedReader(reader);
		String inputLine;
		lines = new String[0];
		while (((inputLine = in.readLine()) != null)) {
			if (inputLine.indexOf('#') == -1) continue;
			//System.out.println("Read Game : "+ inputLine);
			String[] temp = new String[lines.length];
			System.arraycopy(lines,0,temp,0,lines.length);
			lines = new String[lines.length+1];
			System.arraycopy(temp,0,lines,0,temp.length);
			lines[lines.length-1] = inputLine;
			
			
			
		}
		in.close();
		reader.close();
		parseLines();
		} catch (Exception e) {
		}	
	}
	
	
	
	
}