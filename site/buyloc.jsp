<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
deck.loadUser(name);

//int expan = Integer.parseInt(request.getParameter("x"));%>
<%

int deckno = deck.buyThree(2);
if (deckno == -1) { %>
ERROR: Couldn't buy cards.
<% } else { %>
Bought a 3 card Expedition Pack, for 250 points: now in deck # <%=deckno%>
<%webrunner.cardmaster.CardmasterData.userpatch(name,"addd",deckno,"x");%>
<% // deck.saveusers(); 
} %>
</td></tr>