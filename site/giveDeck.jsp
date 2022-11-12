<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%
String name =  request.getParameter("n");
deck.loadUser(name);
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
int predeck = Integer.parseInt(request.getParameter("d"));
int newdeck = deck.giveNewPrebuiltToUser(predeck);
webrunner.cardmaster.CardmasterLogManager.WriteLog(yourname + " Gave prebuilt deck " +newdeck+ " to " +name);
%>
Gave deck <%=newdeck%> to <%=name%>

<%
} else { %>
ERROR
<%}%>
</td></tr>