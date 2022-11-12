<HTML>
<HEAD>
</HEAD>
<BODY>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
THIS IS A TEST
<% webrunner.cardmaster.CardmasterServerCard[] cards = webrunner.cardmaster.CardmasterData.loadCardData(); %>
<% for (int i=50;i<60;i++) {%>
<% if (cards[i] != null) { if (!cards[i].dummy) { %>
<%
request.setAttribute("theCard",cards[i]);%>

<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b><</b><br>
This is card number <%=i%>
<div class=times>
x10
</div>
</DIV>

</DIV>
<% } } } %>

</BODY>
</HTML>