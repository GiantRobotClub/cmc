<td><tr>
<jsp:useBean id="movecard" class="webrunner.cardmaster.BuySellCard" scope="page"/>
<% movecard.loadCardData(); %>
<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>
<%int deck1 = Integer.parseInt(request.getParameter("to"));
int card = Integer.parseInt(request.getParameter("card"));
%>
<% int price = movecard.buyCard(deck1,name,card);
if (price > 0) {%>
Bought into <%=deck1%> for <%=price%>
<%
webrunner.cardmaster.CardmasterData.userpatch(name,"remp",price,"x");	} else { %>
Card could not be bought.
<%}}%>
</td></tr>