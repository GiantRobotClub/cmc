<HTML>
<BODY>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>
<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=session.getAttribute(\"loginname\")%>"/>
<% int num = Integer.parseInt(request.getParameter("deck")); %>
<%=session.getAttribute("loginname").toString()%>'s deck # <%=num%><br>
<a href="SellAll.jsp?deck=<%=num%>">Sell All</a>
<br>
<% if (deck.loadData(num)) { 
	for (int i = 0; i<deck.getDeckSize(); i++) {%>
		<table border=1 bgcolor=<%=deck.getBackColor(i)%>><tr><td><a href="ShowCard.jsp?cardid=<%=deck.getCardID(i)%>" target=_new>
		<%= deck.getCardName(i) %><br>
		<img src="<%=deck.getImage(i)%>.gif" width=96 height=40 border=0></a><br>
		Sell price: <%= deck.getSellCost(i) %> <a href="SellCard.jsp?from=<%=num%>&card=<%=deck.getCardID(i)%>">SELL!</a>
		</td></tr></table>
		


<%
	} } else {
%>
ERROR 
<% } %>
</body>
</html>