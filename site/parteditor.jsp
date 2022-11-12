
<% String name = session.getAttribute("loginname").toString(); %>

<jsp:useBean id="editor" scope="session" class="webrunner.cardmaster.CardmasterAvatarPartEditor"/>
<% if (editor.login) 	editor.partdata = webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>
<% editor.login(name); %>


<% if (editor.login) {%>


All parts:
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>
<% 
for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_PARTS;i++) {
	boolean no = true;%>
<% if (parts[i] != null) { if (true) { %>
<%
request.setAttribute("thePart",parts[i]);
String artistname = "?";
	if (! (parts[i].gender.equals("b") || parts[i].gender.equals("m") || parts[i].gender.equals("f") ))
			artistname = parts[i].gender;
	request.setAttribute("theUnderText", "Type: " + parts[i].type+  "<br>Art:"+artistname+ "<br>Store: " + parts[i].availableStore+"<br><a href=page.jsp?pageurl=editpart.jsp&id="+i+">Edit this part</a>");



%>

	


<jsp:include page="singlepart.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><DIV CLASS=blankpart><a href="page.jsp?pageurl=createpart.jsp&id=<%=i%>">Create new part <%=i%></a></DIV> <%} } %>


<% }else { %> Naughty, naughty! <% } %>