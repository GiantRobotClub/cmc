
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
deck.loadUser(name);

int expan = Integer.parseInt(request.getParameter("x"));%>
<%int deckno = deck.buyNewBoosterBox(expan);
if (deckno == -1) { %>
ERROR: Couldn't buy cards.
<% } else { %>
Bought a booster box (10x 15 card boosters), for <%=(webrunner.cardmaster.CardmasterData.BOOSTCOST*10)%> points: now in deck # <%=deckno%>
<%webrunner.cardmaster.CardmasterData.userpatch(name,"addd",deckno,"x");%>
<% // deck.saveusers(); 
} %>