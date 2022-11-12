<tr><td>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%String username = session.getAttribute("loginname").toString();%>
<% if (username.equals("webrunner")) { %>
<% String name = request.getParameter("n"); %>

<%
deck.loadUser(name);
deck.giveNewBooster(1);
deck.giveNewBooster(1);
deck.giveNewBooster(1);
webrunner.cardmaster.CardmasterData.userpatch(name,"addp",-2000,"x");



%>
<% } %>

</tr></td>