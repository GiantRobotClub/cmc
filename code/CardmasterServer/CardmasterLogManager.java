package webrunner.cardmaster;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Calendar;

public class CardmasterLogManager {
	public static void WriteLog(String logline) {
		if (logline == null) return;
		if (logline.length() == 0) return;
		
		File file;
		FileChannel channel = null;
		FileLock lock = null;
		FileWriter writer = null;
		PrintWriter out = null; 
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH)+1;
		int year = rightNow.get(Calendar.YEAR);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		String daystring = ""+day;
		if (day <10) daystring = "0" + day;
		
		String monthstring = ""+month;
		if (month <10) monthstring = "0"+month;
		
		String datestring = year +  monthstring  + daystring;
		
		try {
			file = new File(CardmasterData.DIRECTORY + "cmclogs/cmclog_"+datestring+".log");
			if (!file.exists()) file.createNewFile();
			channel = new RandomAccessFile(file, "rw").getChannel();
	
			lock = channel.lock();
			
			writer = new FileWriter(file, true); 
			out = new PrintWriter(writer);
			//out.print(users[1]);
			String minutestring = rightNow.get(Calendar.MINUTE) + "";
			String hourstring = rightNow.get(Calendar.HOUR_OF_DAY) + "";
			String secondstring = rightNow.get(Calendar.SECOND) + "";
			
			if (rightNow.get(Calendar.MINUTE) < 10) minutestring = "0" + minutestring;
			if (rightNow.get(Calendar.HOUR_OF_DAY) < 10) hourstring = "0" + hourstring;
			if (rightNow.get(Calendar.SECOND) < 10) secondstring = "0" + secondstring;
			
			String timestring = "[" + hourstring + ":" + minutestring + ":" + secondstring + "] ";
			out.println(timestring + logline);

			if (lock != null) { lock.release();  }
			if (channel != null)channel.close();
			out.close();
			writer.close();
					
		
		}catch(Exception e) {
			//System.out.println("OMG!");
			e.printStackTrace();
		try {
			try{if (lock != null) { lock.release(); }}catch(Exception xe){xe.printStackTrace();	}
 			try{if (out != null) out.close();}catch(Exception xe){xe.printStackTrace();	}
			try{if (writer != null)	writer.close();	}catch(Exception xe){xe.printStackTrace();	}
			try{if (channel != null) channel.close();}catch(Exception xe){xe.printStackTrace();	}
			return;
		
		}
		catch (Exception be) {
			be.printStackTrace();
			return;
		}
		}
		
		
		
		
		
	}
	
	public  static String[] ReadLog(String logname) {
		
		File file = new File(CardmasterData.DIRECTORY + "cmclogs/cmclog_"+logname+".log");
		BufferedReader in = null;
		FileChannel channel = null;
		FileLock lock = null;
		if (!file.exists()) return new String[0];
		String[] logarray = new String[0];
		
		try {
			channel = new RandomAccessFile(file, "rw").getChannel();

			lock = channel.lock();	
			in = new BufferedReader(Channels.newReader(channel,"ISO-8859-1"));
			
			String inputLine;
			while (((inputLine = in.readLine()) != null)) {
				String[] temparray = new String[logarray.length+1];
				System.arraycopy(logarray,0,temparray,0,logarray.length);
				temparray[temparray.length-1] = inputLine;
				logarray = new String[logarray.length+1];
				System.arraycopy(temparray,0,logarray,0,temparray.length);
				
				
				
				
			}
			lock.release();
			in.close();
			channel.close();
			
			return logarray;
			
		}
		catch (Exception e){
			try {
				try{if (lock != null)lock.release();}catch(Exception xe){xe.printStackTrace();	}
				try{if (channel != null) channel.close();}catch(Exception xe){xe.printStackTrace();	}
			try{	if (in != null) in.close();}catch(Exception xe){xe.printStackTrace();	}
				return new String[0];
			}
			catch (Exception ee) {
				return new String[0];
				
				
			}
			
			
			
			
		}
		
		
		
		
	}
	
	public  static String[] GetLogList() {
		File logdir = new File(CardmasterData.DIRECTORY + "cmclogs/");
		File[] files = logdir.listFiles();
		String[] loglist = new String[files.length];
		for (int i=0;i<loglist.length;i++) {
			String logname=	files[i].getName();
			
			logname =logname.substring(7,15);
			
			loglist[i] = logname;	
			
		}
		return loglist;
		
	}

}