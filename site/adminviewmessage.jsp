<% if ( ((String)request.getAttribute("admin")).equals("yes")) { 
	int id = Integer.parseInt(request.getParameter("id"));
	webrunner.cardmaster.CardmasterMessage message = webrunner.cardmaster.CardmasterMessageSystem.GetMessage(id);
	if (message == null) { %>
		Could not access message
	<% } else { %>
		ID: <%=message.messageid%><br>
		To: <%=message.to%><br>
		From: <%=message.from%><br>
		Sent: <%=message.month%>/<%=message.day%>/<%=message.year%><br>
		Subject: <%=message.subject%><br>
		Read: <%=message.read%> Replied: <%=message.replied%> Archived: <%=message.archived%><br>
		Message: <br><%=message.getMessageHTML()%><br>

		<!-- need to put card attach here -->


	<% } %>











<%} %>