<HTML>
<META HTTP-EQUIV="refresh" CONTENT="2;URL=page.jsp?pageurl=phpfeed.jsp&url=cmcnews.php">
<BODY>
<%@ page import="java.io.*" buffer="256kb"  %>
<jsp:useBean id="login" class="webrunner.cardmaster.CardmasterLogin" scope="session"/>
<jsp:setProperty name="login" property="*"/>
<% boolean nologin = true; %>
Logging In...



<% 
String username = request.getParameter("username");
String password = request.getParameter("password");
if (username == null || password == null) {
%>
Please enter all data.
<%

}
else {
webrunner.cardmaster.CardmasterUser checkuser = webrunner.cardmaster.CardmasterData.loadUser(username);
File oldfile = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "oldusers/" + username + ".usr");
File newfile = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "users/" + username + ".usr");
%>

<%
if (checkuser.year <=2004 && oldfile.exists() && !newfile.exists()) {
 // copy old file back!
 %> Found old (2004 or earlier) account with your name.  Attempting to fix. <%
	
	%> Removing decks that aren't yours... <%
		for (int i=0;i<checkuser.decks.length;i++) {
			webrunner.cardmaster.CardmasterData.userpatch(username,"rmdx",checkuser.decks[i],"x");
		}
	


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
	%>Should be fixed... wait a few seconds and try logging in again<%

} else { 
%>














<% if (request.getParameter("username").startsWith("webrunner") || nologin) { %>
<% webrunner.cardmaster.CardmasterUser newuser = webrunner.cardmaster.CardmasterData.loadUser(username); %>
<% if (newuser != null) { %>
	<% username = newuser.name; %>
	<% if (login.Login()) { 
		session.setAttribute("loggedin","yes");
		session.setAttribute("loginname",username);
		webrunner.cardmaster.CardmasterLogManager.WriteLog(session.getAttribute("loginname") + " has logged in");

		%>
		<%= session.getAttribute("loginname")%>
	 Success!
	<a href="page.jsp?pageurl=phpfeed.jsp&url=cmcnews.php">Continue...</a>

	<% } else { %>
	Could not be logged in.. invalid password, username, or an inactive account
	<% } } %>
	
<% } else { %>
SYSTEM FIXING IN PROGRESS, CANNOT LOG IN
<% } %>
<% } } %>
</body>
</html>