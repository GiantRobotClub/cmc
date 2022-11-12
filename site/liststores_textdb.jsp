
<%@ page import="java.io.*" buffer="256kb"  %>
<% webrunner.cardmaster.CardmasterDBStore[] stores = webrunner.cardmaster.CardmasterDBStoreSystem.loadEnabledStores();
if (stores == null) stores = new webrunner.cardmaster.CardmasterDBStore[0];
for (int i=0;i<stores.length;i++) {
	if (stores[i] == null) continue;
//	if (stores[i].sellfree == null) continue;
	if (stores[i].name == null) continue;


	String username = stores[i].name;
	%>name,<%=username%>,;<%
	webrunner.cardmaster.CardmasterDBStoreCard[] cards = webrunner.cardmaster.CardmasterDBStoreSystem.loadStoreCards(username);
	if (cards == null) cards= new webrunner.cardmaster.CardmasterDBStoreCard[0];
	for (int c=0;c<cards.length;c++) {
		if (cards[c] == null) continue;


		int quantity = cards[c].quantity;
		int cardid = cards[c].cardno;
		int price = cards[c].price;
		int mincost = 0;
		if (stores[i].sellfree) mincost = -1;
		if (quantity > 0 && price > mincost) {%><%=cardid%>,<%=quantity%>,<%=price%>,;<%}

	}
			
		
}
%>