
<% webrunner.cardmaster.CardmasterUser[] users = webrunner.cardmaster.CardmasterData.loadUserData();
for (int i=1;i<users.length;i++) {%>
Setting email for.... <%=users[i].name%><br>
<%webrunner.cardmaster.CardmasterData.SaveEmailFor(users[i]);%>
<% } %>