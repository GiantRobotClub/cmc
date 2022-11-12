package webrunner.cardmaster;
import java.util.Calendar;
import java.io.*;

public class CardmasterMessage{
	
	public String message;
	public String from;
	public String to;
	public String subject;
	public int year;
	public int day;
	public int month;
	public int hour;
	public int minute;
	public int second;
	public boolean read;
	public boolean replied;
	public boolean archived;
	public int messageid;
	public int cardattach;
	
	public CardmasterMessage(){
		message = "";
		from = "";
		to = "";
		
		subject = "";
		year = 0;
		
		day = 0;
		month = 0;
		read = false;
		replied =false;
		archived = false;
		
		messageid = 1;	
		cardattach = 0;
	}
	
	public String getMessageHTML() {
		return message.replaceAll("\n","<br>");
		
	}
}