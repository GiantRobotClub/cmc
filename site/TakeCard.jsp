<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> Manage your decks. </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>
<%String name = session.getAttribute("loginname").toString();%>
<%
if ( ((String)request.getAttribute("admin")).equals("yes")) {

int deck2 = -1;
int deck1 = Integer.parseInt(request.getParameter("from"));
int card = Integer.parseInt(request.getParameter("card"));
%>
<% if (deck1 != deck2) { %>
<% if (webrunner.cardmaster.CardmasterData.transferCard(deck1,deck2,card)) {
	
	webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " removes card " + card + " from " + deck1);
	
	%>
Card moved from <%=deck1%> to <%=deck2%>
<% } else { %>
Card could not be moved.
<%}
} else { 
%>
Cannot move to the same deck.
<% } } %>

</BODY>
</HTML>