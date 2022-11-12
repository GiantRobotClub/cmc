<TR><TD>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterListGames" scope="page"/>
Current games:<br>
<% deck.loadGames(); 

for (int i=0;i<deck.roomno.length;i++) {
%>
Room #<%=deck.roomno[i]%>: <a href="observe.jsp?number=<%=deck.roomno[i]%>"><%=deck.name1[i]%> versus <%=deck.name2[i]%></a><br>
<% } %>
</TD></TR>