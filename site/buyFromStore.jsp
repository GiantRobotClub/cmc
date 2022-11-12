<tr><td>

<%String name = session.getAttribute("loginname").toString();%>
<% 
	int deck1 = Integer.parseInt(request.getParameter("to"));
	int card = Integer.parseInt(request.getParameter("card"));
	boolean success = true;
	
	String othername = request.getParameter("name");
	
	
	webrunner.cardmaster.CardmasterDBStoreCard card = webrunner.cardmater.CardmasterDBStoreSystem.loadCard(othername,card);

	webrunner.cardmaster.CardmasterDBStore store = webrunner.cardmater.CardmasterDBStoreSystem.loadStore(othername);

	boolean cmail = store.cmail;

	if (card == null) success = false;

	if (store.enabled) success = false;
	

	
	String test = otheruser.readExtraA("store");
	if (test == null) success = false;
	else if (!test.equals("yes")) success = false;

	int price = store.getCost(card);
	if (user.points < price) success = false;
	if (store.getNumber(card) <= 0) success = false;
	
	if (!(user).hasDeck(deck1)) success = false;
	
	if (success) {
		boolean success2 = webrunner.cardmaster.CardmasterData.transferCard(-1,deck1,card);
		%> Now there's <%=store.getNumber(card) %> <%
		store.setNumber(card,store.getNumber(card) - 1);
		%> Now there's <%=store.getNumber(card) %> <%
		store.saveStore();
		webrunner.cardmaster.CardmasterData.unlockStore(name);

		if (success2) {
		webrunner.cardmaster.CardmasterData.userpatch(name,"remp",price,"x");
		webrunner.cardmaster.CardmasterData.userpatch(othername,"addp",price,"x");
		
		%> Buying card from store for <%=price%> points <%
		} }	else { webrunner.cardmaster.CardmasterData.unlockStore(othername); %> 
		error
		
	
	
	<% } %>
	
<% webrunner.cardmaster.CardmasterData.unlockStore(othername); %>

</td></tr>