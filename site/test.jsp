<%@ page import="java.io.*" buffer="256kb"  %>
<% 
String username = request.getParameter("username");
webrunner.cardmaster.CardmasterUser checkuser = webrunner.cardmaster.CardmasterData.loadUser(username);
File oldfile = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "oldusers/" + username + ".usr");
File newfile = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "users/" + username + ".usr");
%>

<%
if (checkuser.year <=2004 && oldfile.exists() && !newfile.exists()) {
%> Copying <%
 // copy old file back!
	FileReader reader = new FileReader(oldfile);
	FileWriter writer = new FileWriter(newfile);
	BufferedReader min = new BufferedReader(reader);
	PrintWriter mout = new PrintWriter(writer);
	String readline;
	while ((readline = min.readLine()) != null) {
		%><%=readline%><%
		mout.println(readline);
	}
	min.close();
	mout.close();
	writer.close();
	reader.close();


}
%>
