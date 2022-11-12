<tr><td>
<%
String name =  request.getParameter("name");
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {%>
<FORM METHOD=POST ACTION="page.jsp?pageurl=userPatch.jsp">
Code: <INPUT TYPE=TEXT name=code><br>
Text: <INPUT TYPE=TEXT name=text><br>
Number: <INPUT TYPE=TEXT name=num><br>
Name: <INPUT TYPE=TEXT name=name><br>
<INPUT TYPE=SUBMIT>

	<%
} else { %>
ERROR
<%}%>
</td></tr>