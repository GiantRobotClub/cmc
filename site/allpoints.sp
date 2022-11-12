<HTML>
<HEAD>
</HEAD>
<BODY>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
All Cards:
<% webrunner.cardmaster.CardmasterServerCard[] cards =	 webrunner.cardmaster.CardmasterData.loadCardData(); %>
<% 
for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++) {
	boolean no = true;%>
<% if (cards[i] != null) { if (!cards[i].dummy) { %>
<%
request.setAttribute("theCard",cards[i]);%>


<jsp:include page="singlecard.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><DIV CLASS=blankcard>No card #<%=i%></DIV> <%} } %>

</BODY>
</HTML>