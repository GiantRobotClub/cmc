<%if ( ( (String)request.getAttribute("admin") )  .equals("yes")) {%>
<% String[] loglist = webrunner.cardmaster.CardmasterLogManager.GetLogList(); 
java.util.Arrays.sort(loglist);
%>
CARDMASTER CONFLICT LOG FILES:<br>
<% for (int i=0;i<loglist.length;i++) { %>
	<a href="page.jsp?pageurl=showlog.jsp&log=<%=loglist[i]%>"><%=loglist[i]%></a><br>
	
<% } %>

<% } else { %> You are not an administrator <% } %>