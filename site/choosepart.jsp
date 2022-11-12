
<% String name = session.getAttribute("loginname").toString(); %>
<% String type = request.getParameter("type"); %>
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterAvatarManager.getParts(name,type); 

int totalparts = 0;%>
Parts for <%=type%>
<%
for (int i=0;i<parts.length;i++) {
	boolean no = true;%>
<% if (parts[i] != null) { if (parts[i].type.equals(type) || parts[i].type.equals("xx")) { %>
<%
		totalparts++;

String artistname = "?";
	if (! (parts[i].gender.equals("b") || parts[i].gender.equals("m") || parts[i].gender.equals("f") ))
			artistname = parts[i].gender;


request.setAttribute("thePart",parts[i]);
	request.setAttribute("theUnderText","Artist: " + artistname+"<br><a href=page.jsp?pageurl=SwitchPart.jsp&type="+type+"&id="+parts[i].id+">Wear This!</a>");
	
	%>

<jsp:include page="singlepart.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><%} } %>
<div style="clear:left"> Total parts: <%=totalparts%></div>