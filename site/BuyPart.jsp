
<%String name = session.getAttribute("loginname").toString();%>
<%int partid = Integer.parseInt(request.getParameter("id"));%>
<%String type = request.getParameter("type");%>
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>

<%
if (webrunner.cardmaster.CardmasterAvatarManager.buyPart(name,type,partid)) { %>
Bought <%=parts[partid].partname%> of type <%=type%> for <%=parts[partid].price%>
<% } else { %>
Could not buy part. (either it doesn't exist, you cant afford it, or you already have one, or some other error.)
<% } %>
<a href="page.jsp?pageurl=avatars.jsp">Return to Avatar Management</a>