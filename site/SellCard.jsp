<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> Manage your decks. </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>
<jsp:useBean id="movecard" class="webrunner.cardmaster.BuySellCard" scope="page"/>
<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>
<% movecard.loadCardData(); %>
<%int deck1 = Integer.parseInt(request.getParameter("from"));
int card = Integer.parseInt(request.getParameter("card"));
%>
<% int price = movecard.sellCard(deck1,name,card);
if (price > 0) {%>
Sold from <%=deck1%> for <%=price%>
<%
	webrunner.cardmaster.CardmasterData.userpatch(name,"addp",price,"x"); } else { %>
Card could not be sold.
<%}}%>

</BODY>
</HTML>