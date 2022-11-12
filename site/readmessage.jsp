<%String username = session.getAttribute("loginname").toString();%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	webrunner.cardmaster.CardmasterMessage message = webrunner.cardmaster.CardmasterMessageSystem.GetMessage(id);
%>
<%if (message == null) { %>
	Could not access message
<% } else { %>
	
		<% if ( !message.to.toLowerCase().equals(username.toLowerCase())) { %>
			This message ain't yours
		<% } else { %>
			<%webrunner.cardmaster.CardmasterMessageSystem.SetMessageRead(id,true);%>
			<b>ID:</b> <%=message.messageid%><br>
			<b>From:</b> <%=message.from%><br>
			<b>Sent:</b> <%=message.month%>/<%=message.day%>/<%=message.year%><br>
			<b>Subject:</b> <%=message.subject%><br>
			<b>Replied:</b> <%=message.replied%><br>
			<b>Message:</b> <hr><%=message.getMessageHTML()%><hr>
			<a href="page.jsp?pageurl=newreply.jsp?replyto=<%=message.messageid%>">Reply to this message</a>
			
		<!-- need to put card attach here -->

		<% } %>
	<% } %>
