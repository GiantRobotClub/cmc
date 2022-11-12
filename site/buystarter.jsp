<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>
<% 
webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
deck.loadUser(name);
if (user.points >= 3000) {
int deckn = Integer.parseInt(request.getParameter("n"));
%>
<%int deckno = deck.buyNewPrebuilt(deckn);

if (deckno == -1) { %>
ERROR: Couldn't buy cards.

<% } else { %>
Bought the deck for 3000 points: now in deck # <%=deckno%>
<%webrunner.cardmaster.CardmasterData.userpatch(name,"addd",deckno,"x");%>
<% // deck.saveusers(); 
}
} else { %> Not enough points <% } %>

<%} %>
</td></tr>