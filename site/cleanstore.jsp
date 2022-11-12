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
		else {

%>
<% if ((deck.getCardID(i) < webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS) &&
		(deck.getCardID(i) > 10))%>
<% if (deck.getCard(i) != null)
	 {%>
 <%=deck.getCardName(i)%>

</td><td>
<% totalcards += quantity; %>
<font size=+4>x<%=quantity%></font><br>

<% if (quantity > 50) {

for (int b=quantity; b>=50;b--) { %>
	number now.... <%=b%>
	
<%	webrunner.cardmaster.CardmasterData.transferCard(99999, 0, deck.getCardID(i)); } %>
 
<% } } %>		


<%		quantity = 1;	 
	}
	}
%> <br> Total Cards: <%=totalcards%> <%} else {
%>
ERROR 
<% }} %>
</td></tr>