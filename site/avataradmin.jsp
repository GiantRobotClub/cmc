AVATAR ADMINISTRATION PAGE<br>
<% String name = session.getAttribute("loginname").toString(); %>

<% 	webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
		if (user == null) return;
		boolean test = true;
		if (user.readExtraA("avataradmin") == null) test = false;
		if (test) if (user.readExtraA("avataradmin").equals("yes")) test = true; else test = false;
		
		if (!test) if (user.readExtraA("admin") == null) return;
		if (!test) if (!user.readExtraA("admin").equals("yes")) return; else test=true;
	if (test) {%>

<a href="page.jsp?pageurl=avatarupload.htm">Upload Avatar Part Graphic File</a><br>
<br>
<a href="page.jsp?pageurl=parteditor.jsp">Edit avatar part data.</a><br><br>
Give Avatar Part:
<FORM METHOD=POST ACTION="page.jsp?pageurl=giveavatarpart.jsp">
<INPUT TYPE=TEXT name="name" value="<%=name%>">
<INPUT TYPE=SUBMIT value="Select Part">
</FORM>

	<% } else { %> You are not an avatar administrator <% } %>