
<%if (( (String)request.getAttribute("admin") )  .equals("yes")) {%>

<% 
String oldname = request.getParameter("oldname");
String newname = request.getParameter("newname");
if (newname == null) newname = oldname; %>

Are you sure you want to copy old folder user <%=oldname%> over <%=newname%>?

<% if (webrunner.cardmaster.CardmasterData.loadUser(newname) == null) { %> (New user doesn't exist, so it should be safe <% } else { %> (New user chosen still exists, so you might want to check and be careful. <% } %>

<a href="page.jsp?pageurl=CopyOldUser.jsp&oldname=<%=oldname%>&newname=<%=newname%>">Yes!</a><br>
<a href="page.jsp?pageurl=admin.jsp">No!</a><br>

<% } else { %> not an admin <% } %>