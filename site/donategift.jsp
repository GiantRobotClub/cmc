
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%
String name =  request.getParameter("n");

deck.loadUser(name);

String yourname = session.getAttribute("loginname") + "";

if (( (String)request.getAttribute("admin") ).equals("yes")) {

int predeck = Integer.parseInt(request.getParameter("m")) / Integer.parseInt(request.getParameter("c"));

webrunner.cardmaster.CardmasterData.userpatch(name,"addp",predeck * 1000,"x");%>
Giving <%=predeck * 1000%> points to <%=name%><br><%
int giftno = Integer.parseInt(request.getParameter("d"));
//System.out.println(" ************** " + d + " " + predeck + " " + Integer.parseInt(request.getParameter("c"); + " " + name);
for (int i=0;i<predeck;i++) {
int newdeck = deck.giveNewPrebuiltToUser(giftno);
webrunner.cardmaster.CardmasterLogManager.WriteLog(yourname + " gives donation " + giftno + " to " + name + " for " + Integer.parseInt(request.getParameter("m")) + " dollar donation");
%>
Gave deck <%=newdeck%> to <%=name%><br>

<%
}
webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,"donor%yes%yes%");%> Set <%=name%> as Donor <br><%
} else { %>
ERROR
<%}%>
