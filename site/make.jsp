<% webrunner.cardmaster.CardmasterServerCard[] cards = webrunner.cardmaster.CardmasterData.loadCardData();
for (int i=0;i<cards.length;i++) {
if (cards[i] == null || cards[i].dummy) continue;
%>
<%=i%> 10<br>
<% } %>



