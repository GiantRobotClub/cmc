
<jsp:useBean id="deck" class="webrunner.cardmaster.ShowCardDataBean" scope="page"/>

<% 
deck.loadCardData();
 int totalcards = 0;%>
<% String name = session.getAttribute("loginname").toString();%>
<% webrunner.cardmaster.CardmasterPersonalStore store = new webrunner.cardmaster.CardmasterPersonalStore(name);%>
<%webrunner.cardmaster.CardmasterData.unlockStore(name);%>
<%=session.getAttribute("loginname").toString()%>'s store <%=store.getStoreName()%><br>
<%
	//int nextcard = -1;
	int quantity = 1;
	int c = Integer.parseInt(request.getParameter("cardid"));
	if (true){
		quantity = store.getNumber(c);
		int cost = store.getCost(c);
			int i = c;
			totalcards += quantity;
			if (quantity > 0) {
request.setAttribute("theCard",deck.getCard(i));
%>
<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b><</b><br>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(i).printed%><br>
Current: <%=cost%> points<br>
Store sell:: <%=deck.getSellCost(i)%> 
Store buy: <%=deck.getBuyCost(i)%><br>

<FORM METHOD=POST ACTION="page.jsp?pageurl=setprice.jsp&cardid=<%=c%>">
<INPUT TYPE=TEXT name="price" length=15>
<INPUT TYPE=SUBMIT value="Submit Price Change">
</FORM>
</DIV>
</DIV>




		


<%
		
	} } %>
	<br> Total Cards: <%=totalcards%>

