
<% if ( ((String)request.getAttribute("admin")).equals("yes")) { %>

<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterShowDeckBean" scope="page"/>
<jsp:setProperty name="deck" property="*"/>
<jsp:setProperty name="deck" property="name" value="<%=request.getParameter("name")%>"/>
<% int num = Integer.parseInt(request.getParameter("deck"));
 int totalcards = 0;%>
<% String name = request.getParameter("deck");

boolean store = false;


if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>

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

request.setAttribute("theCard",deck.getCard(i));%>


<DIV class=cardbox>
<jsp:include page="singlecard.jsp"/>
<DIV class=carddata><b></b><br>
<div class=times>
<% totalcards += quantity; %>
x<%=quantity%>
</div>
Rarity: <%=101-deck.getCard(i).printed%><br>


</DIV>

</DIV>






<%
		quantity = 1;	 
	}
	}
	%> <div style="clear:left">  Total Cards: <%=totalcards%> <br>
	
<%=request.getParameter("name")%>'s deck # <%=num%></div><br>

<%} else {
%>
ERROR 
<% } } }  %>
