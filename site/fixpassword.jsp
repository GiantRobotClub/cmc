
<HTML>

<BODY>

<%String name = request.getParameter("user");%>

<%String pass = request.getParameter("pass");%>

<% webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);%>




<%  if (name.length() > 0 && pass.length() >0) {
	

	
	if (user.password == null) {
	
	webrunner.cardmaster.CardmasterData.userpatch(name,"pass",0,pass);	
	%> Try logging in in about 10 seconds.<%

	} else if (user.password.equals("null")) {
		webrunner.cardmaster.CardmasterData.userpatch(name,"pass",0,pass);
	
	%> Try logging in in about 10 seconds.<%
	} else { %>Your password must be broken/forgotten in a different way.  Or, this isn't your account and you're trying to naughtily remove someone else's password.  Please contact webrunner.<% } %>

<% } else { %> Try entering something instead of nothing <% } %> 

</BODY>

</HTML>