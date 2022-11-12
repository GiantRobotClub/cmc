
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>
<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=session.getAttribute(\"loginname\")%>"/>
<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>
<jsp:useBean id="movecard" class="webrunner.cardmaster.BuySellCard" scope="page"/>
<% movecard.loadCardData();

%>
<% int num = Integer.parseInt(request.getParameter("deck")); %>
Selling <%=name%>'s deck # <%=num%><br>
<br>
SELLING ENTIRE DECK....<br>

<% if (deck.loadData(num)) { 
	int value = movecard.sellDeck(name,num);
%> 
Got <%=value%> points!<%
	webrunner.cardmaster.CardmasterData.userpatch(name,"addp",value,"x"); 
} else {
%>
ERROR 
<% } } %>
