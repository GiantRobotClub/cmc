<%String name = session.getAttribute("loginname").toString();%>
<%String code = request.getParameter("vcode");%>

<% int returncode = webrunner.cardmaster.CardmasterVouchers.Redeem(code,name);

if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_CHECKSUM) {
	%>Checksum error: Code not valid.<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_NODECK) {
	%>Deck error: Does not exist or other error.  Code possibly not valid.<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_ALREADY) {
	%>Error: That code has already been redeemed by you or another user.<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_CODELENGTH) {
	%>Code error:A redemption code should be 15 letters long<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_DECODE) {
	%>Decode error: Code not valid<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_MYSQL) {
	%>SQL Error: Error updating database.  Please try again<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_USER) {
	%>User error: Couldn't load user<br><%
} else if (returncode ==  webrunner.cardmaster.CardmasterVouchers.ERROR_OTHER) {
	%>Generic error<br><%
} else if (returncode <  0) {
	%>Unknown error.<br><%
} else { %>

Code redeemed!  Your new cards should be in deck number <%=returncode%>!

<% } %>