<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> Manage your decks. </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>
<jsp:useBean id="movecard" class="webrunner.cardmaster.MoveCard" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<%int deck1 = Integer.parseInt(request.getParameter("from"));
int deck2 = Integer.parseInt(request.getParameter("to"));
int card = Integer.parseInt(request.getParameter("card"));
%>
<% if (deck1 != deck2) { %>
<% if (movecard.moveCard(deck1,deck2,name,card)) { %>
Card moved from <%=deck1%> to <%=deck2%>
<% } else { %>
Card could not be moved.
<%}
} else { 
%>
Cannot move to the same deck.
<% } %>

</BODY>
</HTML>