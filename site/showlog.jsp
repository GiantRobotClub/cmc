<%if ( ( (String)request.getAttribute("admin") )  .equals("yes")) {
String logname =  request.getParameter("log");
String[] loglines = webrunner.cardmaster.CardmasterLogManager.ReadLog(logname);%>

<table border=1 width=100%>

<% for (int i=0;i<loglines.length;i++) { %>
<tr><td><%=loglines[i]%></td></tr>


<% } %>
</table>



<% } else { %> You are not an administrator <% } %>