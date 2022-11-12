<tr><td>
<%
String name =  request.getParameter("name");
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {

String text = request.getParameter("text");
int num = Integer.parseInt(request.getParameter("num"));
String code = request.getParameter("code");

webrunner.cardmaster.CardmasterData.userpatch(name,code,num,text);
webrunner.cardmaster.CardmasterLogManager.WriteLog(yourname + " makes userpatch request: name:" + name + " code:" + code + " num:" + num + " text:" + text);
%>
<%=name%> <%=code%> <%=num%> <%=text%>

<%
} else { %>
ERROR
<%}%>
</td></tr>