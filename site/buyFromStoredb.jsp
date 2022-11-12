

<%String name = session.getAttribute("loginname").toString();%>
<% 

	int deck1 = Integer.parseInt(request.getParameter("to"));
	int cardno = Integer.parseInt(request.getParameter("card"));

	int quant = Integer.parseInt(request.getParameter("quant"));

	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
	boolean success = true;
	
	String othername = request.getParameter("name");
	
	

	webrunner.cardmaster.CardmasterDBStoreCard card = webrunner.cardmaster.CardmasterDBStoreSystem.loadStoreCard(othername,cardno);

	 webrunner.cardmaster.CardmasterDBStore store = webrunner.cardmaster.CardmasterDBStoreSystem.loadStore(othername);
	 if (store == null) success = false;

	boolean cmail = false;
	if (success)
	 cmail = store.cmail;

	if (card == null) success = false;
	if (success)
	if (!store.enabled) success = false;
	
	int price = 0;
	int pricebreak = 0;
	if (success) {
	
	if (store.sellfree) pricebreak = -1;
	price =card.price * quant;
	if (price <= pricebreak) success = false;
	if (user.points < price) success = false;
	if (card.quantity < quant) success = false;
	
	if (!(user).hasDeck(deck1)) success = false;
	
	}
	if (success) {
		boolean success2 = true;
		int successcount = 0;
		success2 = webrunner.cardmaster.CardmasterDBStoreSystem.RemCard(othername,cardno,quant);
		if (success2) {
		for (int i=0;i<quant;i++) {
			webrunner.cardmaster.CardmasterData.transferCard(-1,deck1,cardno);
			
		}
		

		
		%>
		 Buying <%=quant%> cards from store for <%=price%> points <%
		if (cmail) webrunner.cardmaster.CardmasterDBStoreSystem.CMail(othername,name,cardno,price,quant);
			webrunner.cardmaster.CardmasterData.userpatch(name,"remp",price,"x");
			webrunner.cardmaster.CardmasterData.userpatch(othername,"addp",price,"x");
			webrunner.cardmaster.CardmasterDBStoreSystem.SetBuyTime(othername);
webrunner.cardmaster.CardmasterDBStoreSystem.IncrementSales(othername,quant);

		}	else { %> 
		error
		
		
	
	<% } %>
	<% }	else { %> 
		error2
		<% } %>
	

