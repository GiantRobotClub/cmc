<HTML>
<HEAD>
</HEAD>
<BODY>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
All Cards:
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>
<% 
for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_parts;i++) {
	boolean no = true;%>
<% if (parts[i] != null) { if (true) { %>
<%
request.setAttribute("thePart",parts[i]);%>


<jsp:include page="singleparts.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><DIV CLASS=blankpart>No Part #<%=i%></DIV> <%} } %>

</BODY>
</HTML>