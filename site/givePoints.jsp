<tr><td>
<%
String name =  request.getParameter("n");
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
int predeck = Integer.parseInt(request.getParameter("d"));
webrunner.cardmaster.CardmasterData.userpatch(name,"addp",predeck,"x");

webrunner.cardmaster.CardmasterLogManager.WriteLog(yourname + " gives " + predeck + " to " + name);
%>
Gave points <%=predeck%> to <%=name%>

<%
} else { %>
ERROR
<%}%>
</td></tr>