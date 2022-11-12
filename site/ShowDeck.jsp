<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>
<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=session.getAttribute(\"loginname\")%>"/>
<% int num = Integer.parseInt(request.getParameter("deck"));
 int totalcards = 0;%>
<% String name = session.getAttribute("loginname").toString();
boolean store = false;
if (request.getParameter("store") != null) if (request.getParameter("store").equals("true")) 
	store = true;
if (!webrunner.cardmaster.CardmasterData.isTrading(name)) { %>

<% if (deck.loadData(num)) { 
	int nextcard = -1;
	int quantity = 1;
	for (int c = 0; c<deck.getDeckSize(); c++) {
		int i = c;
		if (c == deck.getDeckSize()-1) nextcard = -1;
		else {nextcard = c+1; }
		if (nextcard != -1 && (deck.getCardID(c) == deck.getCardID(nextcard))) {
			quantity++;
		}
		else {
request.setAttribute("theCard",deck.getCard(i));%>
<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b></b><br>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(i).printed%><br>
<% if (!store) { %> 
	<a href="page.jsp?pageurl=SellCard.jsp&from=<%=num%>&card=<%=deck.getCard(i).cardid%>">Sell: <%=deck.getSellCost(i)%> pt</a><br>
	<% if (request.getParameter("todeck") != null) { 
		 int deckto = Integer.parseInt(request.getParameter("todeck"));%>
	<a href="page.jsp?pageurl=MoveCard.jsp&to=<%=deckto%>&from=<%=num%>&card=<%=deck.getCard(i).cardid%>">Move to Deck<%=deckto%></a><%}%>
<% } else { %>
		<a href="page.jsp?pageurl=moveToStoredb.jsp&from=<%=num%>&card=<%=deck.getCard(i).cardid%>">Move to Store</a>

<% } %>
</DIV>
</DIV>
<%
		quantity = 1;	 
	}
	}
	%> <div style="clear:left">  Total Cards: <%=totalcards%> <br>
	
<%=session.getAttribute("loginname").toString()%>'s deck # <%=num%><br>
<% if (!store && request.getParameter("todeck") == null) { %>
 <a href="page.jsp?pageurl=SellAll.jsp&deck=<%=num%>">Sell Entire Deck...</a>
 <% } %>
 </div>
<%} else {
%>
ERROR 
<% } } %>
