Converting all users to the database.....

<% webrunner.cardmaster.CardmasterUser[] users = webrunner.cardmaster.CardmasterData.loadUserDataOld(); %>
Converting <%=users.length%> users.<br>
<% for (int i =0;i<users.length;i++) {%>
<% if (users[i] !=null) {%>
	<%=i%>: Converting <%=users[i].name%>... <%=webrunner.cardmaster.CardmasterDatabase.LoadUserReplace(users[i].name).name%> done!<br>
<% } %>
<% }%>



Done!  You should delete this file, probably.