
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
deck.loadUser(name);
String code = request.getParameter("vcode");
if (code.startsWith("VCH")) {

	java.io.File file = new java.io.File(webrunner.cardmaster.CardmasterData.DIRECTORY + "userpatch.csc");
	if (file.exists()) { %> Already processing user data.  Please wait and try again. <% } else { %>
<%
if (deck.user.getED("voucher-" + code) != null && !name.equals("webrunner")) { %>
Sorry, you already have redeemed that particular voucher code.  Be on the look out for more!


<% } else {
%>
<%int deckno = deck.giveNewPrebuiltToUser(code);
if (deckno == -1) { %>
ERROR: That coded deck does not exist.
<% } else {
webrunner.cardmaster.CardmasterData.userpatch(name, "extr", 0, "voucher-" + code + "%yes%yes%");	
	webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " redeems voucher " + code);
            
	%>
Gave promotional deck with code: <%=code%> - in deck number <%=deckno%>

<% // deck.saveusers(); 
} } } } else {%>That is not a voucher code<% } %>
