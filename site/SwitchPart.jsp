
<%String name = session.getAttribute("loginname").toString();%>
<%int partid = Integer.parseInt(request.getParameter("id"));%>
<%String type = request.getParameter("type");%>

<%
if (webrunner.cardmaster.CardmasterAvatarManager.setPart(name,type,partid)) { %>
Switched <%=type%> to part id <%=partid%>
<% } else { %>
Could not switch part. (either part does not exist, you do not own that part, or some other problem.)<br>
<% } %>
<a href="page.jsp?pageurl=avatars.jsp">Return to avatar page</a>
