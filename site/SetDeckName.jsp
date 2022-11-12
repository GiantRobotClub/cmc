<HTML>
<BODY>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterSetDeckName" scope="page"/>

<%deck.name = (String)(session.getAttribute("loginname"));

int num = Integer.parseInt(request.getParameter("deck")); 
 String deckname = request.getParameter("deckname"); 
 if (deck.setDeckName(num,deckname)) { %>
 Deck Name Set
 <% } else { %>
 Error: Could not set deck name
 <% } %>
</body>
</html>