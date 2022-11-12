
<% String name = session.getAttribute("loginname").toString(); %>
Rebuilding....
<% webrunner.cardmaster.CardmasterAvatarManager manager = new webrunner.cardmaster.CardmasterAvatarManager();
boolean bool = manager.BuildAvatar(name);
%>


<%if (bool) { %>
Complete!  If this avatar hasn't updated, just go back to the last page and refresh.<br>
<img src="avatars/<%=name%>.png">
<br>
<% } else {%>
Possible failure.<br>
TRY AGAIN, it might be building your basic avatar right now<br>
If there it IS a failure then there's something wrong.. could be any number of things.<br>
<br>
<% } %>
<a href="page.jsp?pageurl=avatars.jsp">Return to Avatar Management</a>