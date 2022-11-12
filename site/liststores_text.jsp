
<%@ page import="java.io.*" buffer="256kb"  %>


<% 
File userdir = new File(webrunner.cardmaster.CardmasterData.DIRECTORY + "store/exists/");


File[] files = userdir.listFiles();
if (files != null)
for (int i=0;i<files.length;i++) {
String username = files[i].getName();

	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(username);


	if (user == null) continue;
	String enabled = user.readExtraA("store");
	String storename = user.readExtraB("store");
	if (enabled == null) continue;
	if (storename == null) continue;
	if (username == null) continue;
%>name,<%=username%>,;
<% webrunner.cardmaster.CardmasterPersonalStore store = new webrunner.cardmaster.CardmasterPersonalStore(username);%>
<% if (!store.initfinish) {continue;} %>
<%webrunner.cardmaster.CardmasterData.unlockStore(username);%>
<%int quantity = 1;
	for (int c = 0; c<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS; c++) {
		quantity = store.getNumber(c);
		int cost = store.getCost(c);
			%><% if (quantity > 0 && cost > 0){%><%=c%>,<%=quantity%>,<%=cost%>,;<% } %><% } %>

<% } %>