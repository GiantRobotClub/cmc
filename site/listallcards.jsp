<HTML>
<BODY>
MONSTERS:<br>
<%
webrunner.cardmaster.CardmasterServerCard[] cards = webrunner.cardmaster.CardmasterData.loadCardData();
for (int mana = 0;mana<60;mana++) {
	%>
	<table border=1><tr><td colspan=7>Mana : <%=mana%> </td></tr>
		<tr>
		
		<td>NAME</td>
		<td>COST</TD>
		<td>SAC</TD>
		<TD>type</td>
		<TD>TEXT</td>
		<TD>ATT</td>
		<TD>LIF</td>
	</tr> <%
	for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++)
		if (cards[i] != null) if (!cards[i].dummy) {
			webrunner.cardmaster.CardmasterServerCard card = cards[i];
			int totalmana = cards[i].Dcost + cards[i].Gcost + cards[i].Lcost;
			if ((totalmana == mana) && card.typecode.equals("m")) { %>
			
				<Tr>
		
				<td><%=card.name%></td>
				<td><%=card.Dcost%>D <%=card.Lcost%>L <%=card.Gcost%>G</td>
				<td><%=card.Dsac%>G <%=card.Lsac%>L <%=card.Gsac%>G</td>
				<td><%if (card.unique) {%>unique <%}%> <%=card.mtype%></td>
				<td><%=card.cardtext%></td>
				<td><%=card.attack%></td>
				<td><%=card.lifepoints%></td>
				</tr>



			<% }




		}




}
%>
<hr>

Effects:<br>
<%
for (int mana = 0;mana<20;mana++) {
	%>
	<table border=1><tr><td colspan=6>Mana : <%=mana%> </td></tr>
		<tr>
		
		<td>NAME</td>
		<td>COST</TD>
		<td>SAC</TD>
		
		<TD>TEXT</td>
		<TD>ATT</td>
		<TD>LIF</td>
	</tr> <%
	for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++)
		if (cards[i] != null) if (!cards[i].dummy) {
			webrunner.cardmaster.CardmasterServerCard card = cards[i];
			int totalmana = cards[i].Dcost + cards[i].Gcost + cards[i].Lcost;
			if ((totalmana == mana) && card.typecode.equals("e")) { %>
			
				<Tr>
		
				<td><%=card.name%></td>
				<td><%=card.Dcost%>D <%=card.Lcost%>L <%=card.Gcost%>G</td>
				<td><%=card.Dsac%>G <%=card.Lsac%>L <%=card.Gsac%>G</td>
				<td><%=card.cardtext%></td>
				<td><%=card.attack%></td>
				<td><%=card.lifepoints%></td>
				</tr>



			<% }




		}




}
%>

<hr>





LOCATION:<br>
<%
for (int mana = 0;mana<20;mana++) {
	%>
	<table border=1><tr><td colspan=3>Mana : <%=mana%> </td></tr>
		<tr>
		
		<td>NAME</td>
		<td>COST</TD>
		
		<TD>TEXT</td>
	</tr> <%
	for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++)
		if (cards[i] != null) if (!cards[i].dummy) {
			webrunner.cardmaster.CardmasterServerCard card = cards[i];
			int totalmana = cards[i].Dcost + cards[i].Gcost + cards[i].Lcost;
			if ((totalmana == mana) && card.typecode.equals("l")) { %>
			
				<Tr>
		
				<td><%=card.name%></td>
				<td><%=card.Dcost%>D <%=card.Lcost%>L <%=card.Gcost%>G</td>
				<td><%=card.cardtext%></td>
				</tr>



			<% }




		}

}
%>



<hr>

SPELLS:<br>
<%
for (int mana = 0;mana<20;mana++) {
	%>
	<table border=1><tr><td colspan=3>Mana : <%=mana%> </td></tr>
		<tr>
		
		<td>NAME</td>
		<td>COST</TD>
		
		<TD>TEXT</td>
	</tr> <%
	for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_CARDS;i++)
		if (cards[i] != null) if (!cards[i].dummy) {
			webrunner.cardmaster.CardmasterServerCard card = cards[i];
			int totalmana = cards[i].Dcost + cards[i].Gcost + cards[i].Lcost;
			if ((totalmana == mana) && card.typecode.equals("s")) { %>
			
				<Tr>
		
				<td><%=card.name%></td>
				<td><%=card.Dcost%>D <%=card.Lcost%>L <%=card.Gcost%>G</td>
				<td><%=card.cardtext%></td>
				</tr>



			<% }




		}

}
%>







</BODY>
</HTML>