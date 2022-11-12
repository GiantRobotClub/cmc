<HTML>
<HEAD>
</HEAD>
<BODY>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
All parts:
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>
<% 
for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_PARTS;i++) {
	boolean no = true;%>
<% if (parts[i] != null) { if (true) { %>
<%
String artistname = "?";
	if (! (parts[i].gender.equals("b") || parts[i].gender.equals("m") || parts[i].gender.equals("f") ))
			artistname = parts[i].gender;


request.setAttribute("thePart",parts[i]);
	request.setAttribute("theUnderText","Available: " + parts[i].availableStore + "<br>Type: " + parts[i].type + "<br>Artist: " + artistname);
	
	%>


<jsp:include page="singlepart.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><DIV CLASS=blankpart>No Part #<%=i%></DIV> <%} } %>

</BODY>
</HTML>