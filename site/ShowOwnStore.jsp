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

<jsp:useBean id="deck" class="webrunner.cardmaster.ShowCardDataBean" scope="page"/>

<% 
deck.loadCardData();
 int totalcards = 0;%>
<% String name = session.getAttribute("loginname").toString();%>
<% webrunner.cardmaster.CardmasterPersonalStore store = new webrunner.cardmaster.CardmasterPersonalStore(name);
webrunner.cardmaster.CardmasterData.unlockStore(name);
%>

<%
	//int nextcard = -1;
	int quantity = 1;
	for (int c = 0; c<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS; c++) {
		quantity = store.getNumber(c);
		int cost = store.getCost(c);
			int i = c;
			//totalcards += quantity;
			if (quantity > 0) {request.setAttribute("theCard",deck.getCard(i));

%>


<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b><</b><br>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(i).printed%><br>
Current: <%=cost%><br>
Store&nbsp;sell:&nbsp;<%=deck.getSellCost(i)%><br>
Store&nbsp;buy:&nbsp;<%=deck.getBuyCost(i)%><br>


<FORM METHOD=POST ACTION="page.jsp?pageurl=setprice.jsp&cardid=<%=c%>">
<INPUT TYPE=TEXT name="price" size=15>
<INPUT TYPE=SUBMIT value="Change Price">
</FORM><br>
<% if (request.getParameter("todeck") != null) { 
	 int deckto = Integer.parseInt(request.getParameter("todeck"));%>
<a href="page.jsp?pageurl=moveFromStore.jsp&to=<%=deckto%>&card=<%=deck.getCard(i).cardid%>">Move to <%=deckto%></a><%}%>

</div>
</div>



	 <br>

		


<%
		
	} } %>
	<DIV style="clear:left"> <%=session.getAttribute("loginname").toString()%>'s store <%=store.getStoreName()%><br>Total Cards: <%=totalcards%></div>

</td></tr>