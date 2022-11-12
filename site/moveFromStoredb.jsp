

<%String name = session.getAttribute("loginname").toString();%>
<% 
	int deck = Integer.parseInt(request.getParameter("to"));
	int card = Integer.parseInt(request.getParameter("card"));
	boolean success = true;
	if (!(webrunner.cardmaster.CardmasterData.loadUser(name).hasDeck(deck))) success = false;
	
	if (success) success =webrunner.cardmaster.CardmasterDBStoreSystem.RemCard(name,card,1);
	
	
	
	
	
	if (success) {
		
		if (!webrunner.cardmaster.CardmasterData.transferCard(-1,deck,card)) {
			%> ERROR.  Check to make sure the card is still in your store, please. <%
			webrunner.cardmaster.CardmasterDBStoreSystem.AddCard(name,card,1);

		}

		else {
			webrunner.cardmaster.CardmasterDBStoreSystem.SetModTime(name);
		%> Moving card from store <%
		}	}	else {  %> 
		error
	
	
	
	<% } %>
	


