<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
deck.loadUser(name);
int numcards = Integer.parseInt(request.getParameter("numcards"));
int color = Integer.parseInt(request.getParameter("color"));%>
<%int deckno = deck.buyNewDeck(color,numcards,0);
if (deckno == -1) { %>
ERROR: Couldn't buy cards.
<% } else { %>
Bought <%=numcards%> cards, for <%=deck.cost(color,numcards)%> points: now in deck # <%=deckno%>

<% // deck.saveusers(); 
} %>
</td></tr>