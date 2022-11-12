<HTML>
<BODY>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>

Buy new cards:<br>
SET: Cardmasters Conflict (Original)<br>
<%int numcards; int color;%>
15 CARD BOOSTERS:
<%numcards = 15;%> 
<%color = 0;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">All Colors</a> (<%=deck.cost(color,numcards)%> points) | 
<%color = 4;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">Light Only</a> (<%=deck.cost(color,numcards)%> points) |
<%color = 2;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">Dark Only</a> (<%=deck.cost(color,numcards)%> points) |
<%color = 8;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">Grey Only</a> (<%=deck.cost(color,numcards)%> points)
<br>
40 CARD DECKS:<%numcards = 40;%> 
<%color = 0;%>
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">All Colors</a> (<%=deck.cost(color,numcards)%> points) | 
<%color = 4;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">Light Only</a> (<%=deck.cost(color,numcards)%> points) |
<%color = 2;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">Dark Only</a> (<%=deck.cost(color,numcards)%> points) |
<%color = 8;%> 
<a href="store.jsp?numcards=<%=numcards%>&color=<%=color%>">Grey Only</a> (<%=deck.cost(color,numcards)%> points)


</body>
</html>

