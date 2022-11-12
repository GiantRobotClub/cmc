package webrunner.cardmaster;
import java.util.Calendar;
import java.io.*;

public class CardmasterDBStore{
	public int modmonth;
	public int modday;
	public int modyear;
	
	public int buymonth;
	public int buyday;
	public int buyyear;
	
	public boolean enabled;
	public boolean sellfree;
	public boolean cmail;
	
	
	public String name;
	public String storename;
	public int salesforever;
	
	
	
	public CardmasterDBStore() {
		modmonth = 0;
		modday = 0;
		modyear = 0;
		buymonth = 0;
		buyyear = 0;
		buyday = 0;
		enabled = false;
		sellfree = false;
		cmail = false;
		name = "";
		storename = "";
		salesforever = 0;
	}
}

