
<% String name = session.getAttribute("loginname").toString(); %>

<jsp:useBean id="editor" scope="page" class="webrunner.cardmaster.CardmasterAvatarPartEditor"/>

<% editor.login(name); %>
<% String toname = request.getParameter("to");
webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(toname);
if (toname.equals(user.name)) {%>

<% if (editor.login) {%>


All parts:
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); 

%>
<% 
for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_PARTS;i++) {
	boolean no = true;%>
<% if (parts[i] != null) { if (true) { %>
<%
String artistname = "?";
	if (! (parts[i].gender.equals("b") || parts[i].gender.equals("m") || parts[i].gender.equals("f") ))
			artistname = parts[i].gender;


request.setAttribute("thePart",parts[i]);
	request.setAttribute("theUnderText", "Type: " + parts[i].type+"<br>Artist: " + artistname+"<br><a href=page.jsp?pageurl=GivePart.jsp&id="+i+"&to="+toname.replace(' ','+')+">Give this part to "+toname+"</a>");



%>

	


<jsp:include page="singlepart.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><DIV CLASS=blankpart>No Part <%=i%></DIV> <%} } %>


<% }else { %> Naughty, naughty! <% } %>
<% } else { %> Couldn't load user - make sure the capitalization is correct. <% } %>