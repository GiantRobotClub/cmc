<HTML>
<HEAD>
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">

</HEAD>
<BODY>

<% webrunner.cardmaster.CardmasterServerCard[] cards = (webrunner.cardmaster.CardmasterServerCard[])session.getAttribute("carddata");


if (cards == null) {

 cards = webrunner.cardmaster.CardmasterData.loadCardData(); 
 session.setAttribute("carddata",cards);
}
String ids = request.getParameter("id");
if (ids == null) {
%><DIV CLASS=blankcard>No card Specified</DIV><%

} else if (ids.length()==0) {
%><DIV CLASS=blankcard>No card Specified</DIV><%

	
} else {
	int id = 0;
	try{
 id = Integer.parseInt(ids);
	}catch (NumberFormatException e) {
		id = 0;
	}
	if (id < 0 || id > webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS) {
	%><DIV CLASS=blankcard>Out of Bounds</DIV><%
	} 
	else 
if (cards[id] == null) {
	%><DIV CLASS=blankcard>No card #<%=id%></DIV><%
} else {



%>
Copy the following into the card's 


<% } } %>
</BODY>
</HTML>