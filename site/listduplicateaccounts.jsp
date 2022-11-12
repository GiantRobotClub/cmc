<%@ page import="java.io.*" buffer="256kb"  %>

<%
String[] userstring = new String[0];

File userdir = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "/oldusers/");
File[] files = userdir.listFiles();
for (int i=0;i<files.length;i++) {
	%>
	<%
	String newstring = files[i].getName().substring(0,files[i].getName().length()-4);
	boolean stringfound = false;
	for (int j=0;j<userstring.length;j++) {
		
		if (userstring[j].substring(0,userstring[j].indexOf(",")).toLowerCase().equals(newstring.toLowerCase())) {
			stringfound= true;
			userstring[j] = userstring[j] + newstring + ",";
			break;
		}
	
		
	}
	if (!stringfound) {
			String[] temp = new String[userstring.length];
			System.arraycopy(userstring,0,temp,0,userstring.length);
			
			
			userstring = new String[userstring.length+1];
			System.arraycopy(temp,0,userstring,0,temp.length);
			userstring[userstring.length-1] = newstring + ",";
			
			

		}
}
	%> <br>Processed.  Here's the list:<Br><%
	for (int k=0;k<userstring.length;k++) {
		if (userstring[k].indexOf(',',userstring[k].indexOf(',')+1) != -1) {
			%><%=userstring[k]%><br><%
		}


	}

		%>