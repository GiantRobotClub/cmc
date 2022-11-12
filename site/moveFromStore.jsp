<tr><td>

<%String name = session.getAttribute("loginname").toString();%>
<% 
	int deck1 = Integer.parseInt(request.getParameter("to"));
	int card = Integer.parseInt(request.getParameter("card"));
	boolean success = true;
	webrunner.cardmaster.CardmasterPersonalStore store = new webrunner.cardmaster.CardmasterPersonalStore(name);
	if (!store.lockStore()) success = false;
	if (store.getNumber(card) <= 0) success = false;
	if (!(webrunner.cardmaster.CardmasterData.loadUser(name).hasDeck(deck1))) success = false;
	
	if (success) {
		webrunner.cardmaster.CardmasterData.transferCard(-1,deck1,card);

		%> Now there's <%=store.getNumber(card) %> <%
		store.setNumber(card,store.getNumber(card) - 1);
		%> Now there's <%=store.getNumber(card) %> <%
		store.saveStore();
		 webrunner.cardmaster.CardmasterData.unlockStore(name);

		
		%> Moving card from store <%
	}	else { webrunner.cardmaster.CardmasterData.unlockStore(name);
 %> 
		error
		
	
	
	<% } %>
	
<% webrunner.cardmaster.CardmasterData.unlockStore(name); %>

</td></tr>