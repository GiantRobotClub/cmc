<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%
String name =  request.getParameter("n");

deck.loadUser(name);
String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
int predeck = Integer.parseInt(request.getParameter("m")) / 10;
webrunner.cardmaster.CardmasterData.userpatch(name,"addp",predeck * 2000,"x");
int giftno = Integer.parseInt(request.getParameter("d"));
for (int i=0;i<predeck;i++) {
int newdeck = deck.giveNewPrebuiltToUser(giftno);
%>
Gave deck <%=newdeck%> to <%=name%>

<%
}
} else { %>
ERROR
<%}%>
</td></tr>