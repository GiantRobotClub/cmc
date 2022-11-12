<%String username = session.getAttribute("loginname").toString();
int id = Integer.parseInt(request.getParameter("id"));%>
<%webrunner.cardmaster.CardmasterMessage message = webrunner.cardmaster.CardmasterMessageSystem.GetMessage(id);%>
<% if ( !message.to.toLowerCase().equals(username.toLowerCase())) { %>
			This message ain't yours
		<% } else { %>
Deleting message....<br><br>
<%
	
	String returnval= webrunner.cardmaster.CardmasterMessageSystem.DeleteMessage(id);
%>
Got: <%=returnval%>
<% } %>