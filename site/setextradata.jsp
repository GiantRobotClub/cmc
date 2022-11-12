<tr><td>
<%
String name =  request.getParameter("name");
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {%>
<FORM METHOD=POST ACTION="page.jsp?pageurl=edPatch.jsp">


Name: <INPUT TYPE=TEXT name=name><br>
Dataname: <INPUT TYPE=TEXT name=text><br>
Dataa: <INPuT TYPE=TEXT name=dataa><br>
Datab: <INPUT TYPE=TEXT name=datab><br>
<INPUT TYPE=SUBMIT>

	<%
} else { %>
ERROR
<%}%>
</td></tr>