
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
<% webrunner.cardmaster.CardmasterDBStore store = webrunner.cardmaster.CardmasterDBStoreSystem.loadStore(name);

webrunner.cardmaster.CardmasterDBStoreCard[] cards = webrunner.cardmaster.CardmasterDBStoreSystem.loadStoreCards(name);
if (cards == null) cards= new webrunner.cardmaster.CardmasterDBStoreCard[0];
%>

<%
	//int nextcard = -1;
	int quantity = 1;
	for (int c = 0; c <cards.length; c++) {
		quantity = cards[c].quantity;
		int cost =cards[c].price;
			int i = c;
			//totalcards += quantity;
			if (quantity > 0) {request.setAttribute("theCard",deck.getCard(cards[i].cardno));

%>


<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b></b>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(cards[i].cardno).printed%><br>
Current: <%=cost%><br>
Store&nbsp;sell:&nbsp;<%=deck.getSellCost(cards[i].cardno)%><br>
Store&nbsp;buy:&nbsp;<%=deck.getBuyCost(cards[i].cardno)%><br>
Add: <%=cards[i].modmonth%>/<%=cards[i].modday%>/<%=cards[i].modyear%><Br>

<FORM METHOD=POST ACTION="page.jsp?pageurl=setpricedb.jsp&cardid=<%=cards[i].cardno%>">
<INPUT TYPE=TEXT name="price" size=15>
<INPUT TYPE=SUBMIT value="Change Price">
</FORM>
<% if (request.getParameter("todeck") != null) { 
	 int deckto = Integer.parseInt(request.getParameter("todeck"));%>
<a href="page.jsp?pageurl=moveFromStoredb.jsp&to=<%=deckto%>&card=<%=cards[i].cardno%>">Move to <%=deckto%></a><%}%>

</div>
</div>




		


<%
		
	} } %>
	<DIV style="clear:left"> <%=session.getAttribute("loginname").toString()%>'s store <%=store.storename%><br>Total Cards: <%=totalcards%></div>

