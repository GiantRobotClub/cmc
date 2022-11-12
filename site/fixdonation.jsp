<% String name = "Argeth"; %>
<% String donname = "World's Biggest e (donation)";%>
<% webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name); %>
<% for (int i=0;i<user.decks.length;i++) {
	int deckno = user.decks[i];

	String deckname = webrunner.cardmaster.CardmasterData.getDeckName(deckno);
	if (donname.equals(deckname)) {
	%>Removed deck <%=deckno%><br><%
	webrunner.cardmaster.CardmasterData.userpatch(name,"rmdx",deckno,"x");


	}
	

}%>