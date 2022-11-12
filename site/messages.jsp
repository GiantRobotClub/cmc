
<%String username = session.getAttribute("loginname").toString();%>
<% webrunner.cardmaster.CardmasterMessage[] messages = webrunner.cardmaster.CardmasterMessageSystem.GetMessageList(username); %>

Your messages:<br>
<table border=1>
<tr>
<td width=25> </td>
<td width=25> </td>
<td width=300>Subject</td>
<td width=100>Date</td>
<td width=100>From</td>
<td width=50>Delete</td>
</tr>
<%

if (messages != null)
for (int i=messages.length-1;i>=0;i--) {

%>

<tr>
<td><img src="images/read_<%=messages[i].read%>.gif"></td>
<td><img src="images/reply_<%=messages[i].replied%>.gif"></td>

<td>
<a href="page.jsp?pageurl=readmessage.jsp&id=<%=messages[i].messageid%>">
<%=messages[i].subject%><%if (messages[i].subject.length() == 0) { %>[no subject]<%}%>
</a>
</td>

<td><%=messages[i].month%>/<%=messages[i].day%>/<%=messages[i].year%></td>
<td><%=messages[i].from%></td>
<td><a href="page.jsp?pageurl=delmessage.jsp&id=<%=messages[i].messageid%>">Delete</td>
</tr>


<%


} %>
</table>
<br>
<a href="page.jsp?pageurl=newmessage.jsp">New Message</a>
