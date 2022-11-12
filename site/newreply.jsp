<%String username = session.getAttribute("loginname").toString();
	int id = Integer.parseInt(request.getParameter("replyto"));
	webrunner.cardmaster.CardmasterMessage message = webrunner.cardmaster.CardmasterMessageSystem.GetMessage(id);
%>

<%if (message == null) { %>
	Could not access message
<% } else { %>
<% if ( !message.to.toLowerCase().equals(username.toLowerCase())) { %>
			This message ain't yours
		<% } else { %>
<FORM METHOD=POST ACTION="page.jsp?pageurl=sendmessage.jsp">
From: <%=session.getAttribute("loginname")%><br>
To: <%=message.from%><INPUT TYPE=HIDDEN NAME=toname size=50 value="<%=message.from%>"><br>
Subject: <INPUT TYPE=TEXT NAME=subject size=130 value ="<%="Re:"+message.subject%>"><br>
<INPUT TYPE=HIDDEN NAME=attachcard value=0>
<INPUT TYPE=HIDDEN NAME=replyto value=<%=id%>>


Message:<br>
<TEXTAREA name=message rows=20 cols=60>Insert your message here</TEXTAREA>
<INPUT TYPE=SUBMIT value="Send Reply">
</FORM>



		<% } %>
	<% } %>
