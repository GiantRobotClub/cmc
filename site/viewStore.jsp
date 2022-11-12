
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

<% webrunner.cardmaster.CardmasterDBStore store = webrunner.cardmaster.CardmasterDBStoreSystem.loadStore(name);

webrunner.cardmaster.CardmasterDBStoreCard[] cards = webrunner.cardmaster.CardmasterDBStoreSystem.loadStoreCards(othername);
if (cards == null) cards= new webrunner.cardmaster.CardmasterDBStoreCard[0];



<%

	int quantitymin = 0;
	if (store.sellfree) quantitymin = -1;	
	//int nextcard = -1;
	int quantity = 1;
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
<DIV class=carddata><b></b><br>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(cards[i].cardid%).printed%><br>
Selling for <%=cost%> points<br>
<% if (request.getParameter("to") != null) { 
	 int deckto = Integer.parseInt(request.getParameter("to"));%>
<a href="page.jsp?pageurl=buyFromStoredb.jsp&name=<%=othername%>&to=<%=deckto%>&card=<%=cards[i].cardid%>">Buy into Deck Number <%=deckto%></a><%}%>
</DIV></DIV>	


<%
		
	} } %><DIV STYLE="clear:left"><%=othername%>'s store <%=store.getStoreName()%><br>
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


   <% } else { %>
   Could not load store <% } %>
