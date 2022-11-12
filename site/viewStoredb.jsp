
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

<jsp:useBean id="deck" class="webrunner.cardmaster.ShowCardDataBean" scope="page"/>

<% 
deck.loadCardData();
 int totalcards = 0;%>
<% String name = session.getAttribute("loginname").toString();%>

<% String othername = request.getParameter("name"); %> 

<% webrunner.cardmaster.CardmasterDBStore store = webrunner.cardmaster.CardmasterDBStoreSystem.loadStore(othername);

webrunner.cardmaster.CardmasterDBStoreCard[] cards = webrunner.cardmaster.CardmasterDBStoreSystem.loadStoreCards(othername);
if (cards == null) cards= new webrunner.cardmaster.CardmasterDBStoreCard[0];



	int quantitymin = 0;
	if (store.sellfree) quantitymin = -1;	
	//int nextcard = -1;

	int quantity = 1;
	for (int c = 0; c <cards.length; c++) {
		quantity = cards[c].quantity;
		int cost =cards[c].price;
			int i = c;
			//totalcards += quantity;
			if (quantity > 0 && cost > quantitymin) {
				request.setAttribute("theCard",deck.getCard(cards[i].cardno));

%>


<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b></b>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(cards[i].cardno).printed%><br>
Selling for <%=cost%> points<br>
Add: <%=cards[i].modmonth%>/<%=cards[i].modday%>/<%=cards[i].modyear%><Br>
<%
	 int deckto = Integer.parseInt(request.getParameter("to"));%>
Buy into deck <%=deckto%>: 
<a href="page.jsp?pageurl=buyFromStoredb.jsp&name=<%=othername%>&to=<%=deckto%>&card=<%=cards[i].cardno%>&quant=1">1x</a> 
<%if (quantity > 5) { %>
<a href="page.jsp?pageurl=buyFromStoredb.jsp&name=<%=othername%>&to=<%=deckto%>&card=<%=cards[i].cardno%>&quant=5">5x</a> 
<%if (quantity > 10) { %>
<a href="page.jsp?pageurl=buyFromStoredb.jsp&name=<%=othername%>&to=<%=deckto%>&card=<%=cards[i].cardno%>&quant=10">10x</a>
<%if (quantity > 25) { %>
<a href="page.jsp?pageurl=buyFromStoredb.jsp&name=<%=othername%>&to=<%=deckto%>&card=<%=cards[i].cardno%>&quant=25">25x</a>
<% }}}%>
</DIV></DIV>	


<%
		
			}} %><DIV STYLE="clear:left"><%=othername%>'s store <%=store.storename%><br>
	<br> Total Cards: <%=totalcards%>
	<% if (totalcards == 0) { %>
    <% 
		webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreEnabled(othername,false); 
		
	
	
	
	
	
	%>
	
	Disabling empty store....
	</DIV>


	<% } %>
	
	<% if (webrunner.cardmaster.CardmasterDBStoreSystem.TooOld(othername)) { %>
    <% 
		webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreEnabled(othername,false); 
		
	
	
	
	
	
	%>
	
	Disabling inactive store....
	</DIV>

 <% } %>
