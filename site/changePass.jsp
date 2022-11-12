<tr><td>
<%
String name =  request.getParameter("n");
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
String predeck = Integer.parseInt(request.getParameter("d"));
CardmasterData.userpatch(name,"pass",0,predeck);
%>
Change password for <%=name%> to <%=predeck%> to 

<%
} else { %>
ERROR
<%}%>
</td></tr>