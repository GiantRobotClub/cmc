<tr><td>
<style>
.text1 {
	font-family: sans-serif;
	font-size: 7pt	
}
.text2 {
	font-family: sans-serif;
	font-size: 8pt
}
.attdef {
	font-family: serif;
	font-size: 12pt
}
</style>
<%@ page buffer="256kb" %>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>
<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=session.getAttribute(\"loginname\")%>"/>
<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>
<% int num = 99999;	
 int totalcards = 0;%>
<% int deckto = Integer.parseInt(request.getParameter("deck")); %>
Singles Available:<br>
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
		else { request.setAttribute("theCard",deck.getCard(i));

%>
<% if ((deck.getCardID(i) < webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS) &&
		(deck.getCardID(i) > 10))%>
<% if (deck.getCard(i) != null)
	 {%>


<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b><</b><br>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(i).printed%><br>

<a href="page.jsp?pageurl=BuyCard.jsp&to=<%=deckto%>&card=<%=deck.getCard(i).cardid%>">Buy for <% if (deck.getCard(i).available) { %> <%=deck.getBuyCost(i)%> <% } else { %> 2000 <% } %> points</a><br>
</DIV>
</DIV>


<% } %>		


<%		quantity = 1;	 
	}
	}
%> <div style="clear:left"> Total Cards: <%=totalcards%></div> <%} else {
%>
ERROR 
<% }} %>
</td></tr>