<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
deck.loadUser(name);
int deckno = deck.giveEmptyDeck();
if (deckno == -1) { %>
ERROR: Couldn't create empty deck.
<% } else { %>
Empty deck # <%=deckno%>

<% // deck.saveusers(); 
} %>


</td></tr>