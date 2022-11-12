<jsp:useBean id="movecard" class="webrunner.cardmaster.MoveCard" scope="page"/>
<%



String name = session.getAttribute("loginname").toString();

webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);

int deck[] = new int[2];

 deck[0] = Integer.parseInt(request.getParameter("d1"));

deck[1] = Integer.parseInt(request.getParameter("d2"));

if (deck[0] == deck[1]) {
%>
Error cannot move to same deck
<%


}

else if (user.hasDeck(deck[0]) && user.hasDeck(deck[1])) {
	%> Moving cards... <br \> <%

java.util.Enumeration paramnames = request.getParameterNames();
while (paramnames.hasMoreElements()) {
	String pname = (String)(paramnames.nextElement());
	if (pname.startsWith("card")) {

		int quant = Integer.parseInt(request.getParameter(pname));

		int thedeck = Integer.parseInt( ""+pname.charAt(5)) -1;
		int otherdeck = 0;
		if (thedeck == 0) otherdeck = 1;
		int card = Integer.parseInt( pname.substring(7));
		for (int i=0;i<quant;i++){if (movecard.moveCard(deck[thedeck],deck[otherdeck],name,card)){%><%=card%>-><%=deck[otherdeck]%> <% } else { %> <%=card%>->Fail <% }} 

	}
}






}

else {

%>
Error: No deck ownership
<%
}

%>
<br />
<a href="page.jsp?pageurl=movecardsbetween.jsp&d1=<%=deck[0]%>&d2=<%=deck[1]%>">Return to decks</a>