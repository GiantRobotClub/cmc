<HTML>
<BODY>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>

<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=session.getAttribute(\"loginname\")%>"/>

<% int num = Integer.parseInt(request.getParameter("deck")); %>
<%=session.getAttribute("loginname").toString()%>'s deck # <%=num%><br>
<% int todeck = Integer.parseInt(request.getParameter("deck2")); %>
<% if ((deck.loadData(num)) && (num != todeck)){ 
	for (int i = 0; i<deck.getDeckSize(); i++) {%>
		<table border=1><tr><td><a href="ShowCard.jsp?cardid=<%=deck.getCardID(i)%>" target=_new>
		<%= deck.getCardName(i) %><br>
		<img src="<%=deck.getImage(i)%>.gif" width=96 height=40 border=0></a><br>
		<a href="MoveCard.jsp?from=<%=num%>&to=<%=todeck%>&card=<%=deck.getCardID(i)%>">Move to <%=todeck%></a>
		</td></tr></table>
		


<%
	} } else {

%>
ERROR <% } %>
</body>
</html>