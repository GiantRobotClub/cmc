<tr><td>
<%
String name =  request.getParameter("name");
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
String text = request.getParameter("text").replace(':','^');

String dataa = request.getParameter("dataa").replace(':','^');
String datab = request.getParameter("datab").replace(':','^');

webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,text + "%" + dataa + "%" + datab + "%");
%>
<%=name%>  <%=text%> <%=dataa%> <%=datab%>

<%
webrunner.cardmaster.CardmasterLogManager.WriteLog(yourname + " changes extra data of " + name + "["+text+"]["+dataa+"]["+datab+"]");


} else { %>
ERROR
<%}%>
</td></tr>