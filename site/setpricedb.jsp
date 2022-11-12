

<%String name = session.getAttribute("loginname").toString();%>
<% 
	int price = Integer.parseInt(request.getParameter("price"));
	int card = Integer.parseInt(request.getParameter("cardid"));
//	boolean success = webrunner.cardmaster.CardmasterData.transferCard(deck1,-1,card);
		if (webrunner.cardmaster.CardmasterDBStoreSystem.SetPrice(name,card,price)) {

	//	System.out.println("Moving card to Store...");
		%> Price set to <%=price%>

	<% } else { %> Could not set price <% } %>
