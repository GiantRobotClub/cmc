<tr><td>
<%
String name =  request.getParameter("name");
if (true) {

String text = request.getParameter("text");
int num = Integer.parseInt(request.getParameter("num"));
String code = request.getParameter("code");

webrunner.cardmaster.CardmasterDatabaseUserpatch.userpatch(name,code,num,text);

%>
<%=name%> <%=code%> <%=num%> <%=text%>

<%
} else { %>
ERROR
<%}%>
</td></tr>