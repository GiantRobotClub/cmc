<tr><td>
<%

String yourname = session.getAttribute("loginname") + "";
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
	webrunner.cardmaster.CardmasterData.convertAllDecks();
%>
Converted ALL decks!  ALL RIGHTY!

<%
} else { %>
ERROR
<%}%>
</td></tr>