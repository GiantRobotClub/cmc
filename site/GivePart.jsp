<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>
<%String name = session.getAttribute("loginname").toString();
int partid = Integer.parseInt(request.getParameter("id"));
String to = request.getParameter("to");
to = to.replace('+',' ');
	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
	 boolean admin = false;
boolean avataradmin = false;
	
		
		if (user.readExtraA("avataradmin") != null) 
		if (user.readExtraA("avataradmin").equals("yes")) avataradmin = true;
		
		 if (user.readExtraA("admin") != null)
		 if (user.readExtraA("admin").equals("yes")) admin = true;
		 if (admin || avataradmin) {
		 %>
			Giving part number <%=partid%> to <%=to%><br> <%

		if (webrunner.cardmaster.CardmasterAvatarManager.givePart(to,partid)) { %>
Gave <%=parts[partid].partname%> of type <%=parts[partid].type%>
<% webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " gives part " + parts[partid].partname + " to " + to); %>
<% } else { %>
Could not give part. (either it doesn't exist, the player already has one, or some othe error.)
<% } %>


<a href="page.jsp?pageurl=admin.jsp">Return to Administration</a>


		 <% } %>
		 