<HTML>
<HEAD>
</HEAD>
<BODY>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
All Cards:
<% webrunner.cardmaster.CardmasterServerCard[] cards =	 webrunner.cardmaster.CardmasterData.loadCardData(); %>
<% 
int exp = -1;
if (request.getParameter("exp") != null) {
exp = Integer.parseInt(request.getParameter("exp"));
}
for (int i=1;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++) {
	boolean no = true;%>
<% if (cards[i] != null) { if (!cards[i].dummy) {  if (exp == -1 || exp == cards[i].expansioncode) { %>
<%
request.setAttribute("theCard",cards[i]);%>

<% if (false) { %><DIV CLASS=blankcard><img src=images/exp_<%=cards[i].expansioncode%>.gif><br>SPOILER: <%=i%></DIV><% } else { %>
<jsp:include page="singlecard.jsp"/> <% } %>
<% } } else { no = false; } } else {no=false;} if (!no) { %><%} } %>

</BODY>
</HTML>