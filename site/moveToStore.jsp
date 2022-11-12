

<%String name = session.getAttribute("loginname").toString();%>
<% 
	int deck1 = Integer.parseInt(request.getParameter("from"));
	int card = Integer.parseInt(request.getParameter("card"));
	boolean success = true;
	if (!(webrunner.cardmaster.CardmasterData.loadUser(name).hasDeck(deck1))) success = false;
	
	if (success) success = webrunner.cardmaster.CardmasterData.transferCard(deck1,-1,card);
	
	
	if (success) {
		
		if (!webrunner.cardmaster.CardmasterDBStoreSystem.AddCard(name,card,1)) {
			%> ERROR.  Check to make sure the card is still in your deck, please. <%
			webrunner.cardmaster.CardmasterData.transferCard(-1,deck1,card);

		}

		else {
		
		%> Moving card to store <%
		}	}	else {  %> 
		error
	
	
	
	<% } %>
	


