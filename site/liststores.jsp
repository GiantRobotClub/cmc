<tr><td>


<%@ page import="java.io.*" buffer="256kb"  %>


<%String name = session.getAttribute("loginname").toString();%>
<%
int deckto = Integer.parseInt(request.getParameter("to"));
File userdir = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "store/exists/");


File[] files = userdir.listFiles();
if (files != null)
for (int i=0;i<files.length;i++) {
	
	String username = files[i].getName();
	
	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(username);


	if (user == null) continue;
	String enabled = user.readExtraA("store");
	String storename = user.readExtraB("store");
	if (enabled == null) continue;
	if (storename == null) continue;
	if (username == null) continue;

	if (enabled.equals("yes") && ! username.equals(name)) { %> <%=username%>'s store: <a href="page.jsp?pageurl=viewStore.jsp&name=<%=username%>&to=<%=deckto%>"><%=storename%></a> <br><% } %>
	 <%


}


%>




</td></tr>