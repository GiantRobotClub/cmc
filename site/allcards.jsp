<HTML>
<HEAD>
</HEAD>
<BODY>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
All Cards:
<% webrunner.cardmaster.CardmasterServerCard[] cards =	 webrunner.cardmaster.CardmasterData.loadCardData(); %>
<% 
for (int i=1;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++) {
	boolean no = true;%>
<% if (cards[i] != null) { if (!cards[i].dummy) { %>
<%
request.setAttribute("theCard",cards[i]);%>

<% if (!cards[i].available || cards[i].expansioncode == 6) { %><DIV CLASS=blankcard><img src=images/exp_<%=cards[i].expansioncode%>.gif><br>SPOILER: <%=i%></DIV><% } else { %>
<jsp:include page="singlecard.jsp"/> <% } %>
<% } else { no = false; } } else {no=false;} if (!no) { %><DIV CLASS=blankcard>No card #<%=i%></DIV> <%} } %>

</BODY>
</HTML>