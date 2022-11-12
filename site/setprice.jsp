<tr><td>

<%String name = session.getAttribute("loginname").toString();%>
<% 
	int price = Integer.parseInt(request.getParameter("price"));
	int card = Integer.parseInt(request.getParameter("cardid"));
//	boolean success = webrunner.cardmaster.CardmasterData.transferCard(deck1,-1,card);
	
	
		webrunner.cardmaster.CardmasterPersonalStore store = new webrunner.cardmaster.CardmasterPersonalStore(name);
		
		store.setCost(card,price);
		store.saveStore();

		webrunner.cardmaster.CardmasterData.unlockStore(name);

	//	System.out.println("Moving card to Store...");
		%> Price set to <%=price%>

	


</td></tr>