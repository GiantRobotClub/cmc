
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>
<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=session.getAttribute(\"loginname\")%>"/>
<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>
<jsp:useBean id="killdeck" class="webrunner.cardmaster.BuySellCard" scope="page"/>
<% killdeck.loadCardData(); %>

<jsp:useBean id="movecard" class="webrunner.cardmaster.MoveCard" scope="page"/>

<% int num = Integer.parseInt(request.getParameter("deck")); 
int deck1 = num;
int deck2 = Integer.parseInt(request.getParameter("to"));
%>
Moving <%=session.getAttribute("loginname").toString()%>'s deck # <%=num%> to deck # <%=deck2%><br>
<br>
<% if ( deck1 != deck2 ) { %>
<% if (deck.loadData(num)) { 
		if (movecard.moveCard(deck1,deck2,name,-1)) { %>
		Moved deck....
		<br>
		Deleting Empty Deck... 
		<%killdeck.killDeck(session.getAttribute("loginname").toString(), num); %>
<%
	} else { %> ERROR <% }%>

<%
 
} else {
%>
ERROR 
<% } %>
<% } else { %> Cannot move deck into itself <% } %>
<%} %>
