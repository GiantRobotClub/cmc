
<% if ( ((String)request.getAttribute("admin")).equals("yes")) { 
 String username = request.getParameter("name");
 webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(username); %>

<% if (user == null) { %> ERROR: USER NOT FOUND <% } else {%> 

NAME: <%=username%><br>
WINS: <%=user.wins%><br>
LOSSES: <%=user.losses%><br>
POINTS: <%=user.points%><br>
PRIMARY DECK: <a href="page.jsp?pageurl=ShowDeckAdmin.jsp&name=<%=username%>&deck=<%=user.primarydeck%>"><%=user.primarydeck%></a><br>
ALL DECKS: <% for (int i=1;i<user.decks.length;i++) { %> 
<a href="page.jsp?pageurl=ShowDeckAdmin.jsp&name=<%=username%>&deck=<%=user.decks[i]%> ">
<%=user.decks[i]%> 
</a>

<% } %><br>
VERIFCODE: <%=user.verifcode%><br>
VERIFIED: <%=user.emailverified%><br>
LAST LOGGED IN: <%=user.day%>/<%=user.month+1%>/<%=user.year%><br>
HAS ENCRYPTED PASSWORD: <%=user.password.equals("BYTEPASSWORD")%><br>
<% if ( !user.password.equals("BYTEPASSWORD") ) { %> PASSWORD: <%=user.password%><% } %><BR>
EMAIL ADDRESS: <%=user.email%><br>
EXTRA DATA FOLLOWS<br>
<%for (int i =0;i<user.ed.length;i++) { %>
|<%=user.ed[i].name%>|-|<%=user.ed[i].dataa%>|-|<%=user.ed[i].datab%>|<br>
<% } %>

<% } %>

<% } else { %> You are not an admin <% } %>