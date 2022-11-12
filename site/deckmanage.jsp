
<jsp:useBean id="decklist" class="webrunner.cardmaster.CardmasterDeckList" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% decklist.loadUser(name);decklist.CountCards();%>


View Deck:<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=ShowDeck.jsp">
<SELECT NAME=deck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="View Deck">
</FORM>
<br>
Set Primary Deck:<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=SwitchDeck.jsp">
<SELECT NAME=deck>
<%

for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="Switch Deck">
</FORM>

<br>
Move Cards<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=movecardsbetween.jsp">
From: 
<SELECT NAME=d1>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
To:  
<SELECT NAME=d2>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%>(<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="Move Cards">
</FORM>
<br>

Move Cards (Old Style)<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=ShowDeck.jsp">
From: 
<SELECT NAME=deck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
To: 
<SELECT NAME=todeck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%>(<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="Move Cards">
</FORM>
<br>
<br>
Move Entire Deck (Moves all cards from From deck to To Deck and deletes From deck)<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=MoveDeck.jsp">
From: 
<SELECT NAME=deck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%>(<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
To: 
<SELECT NAME=to>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%>(<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="Move Deck">
</FORM>
<br>


Set Deck Name:<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=SetDeckName.jsp">
<SELECT NAME=deck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%>(<%=decklist.getDeckSize(i)%>)
<% } %>

</SELECT>
<INPUT TYPE=TEXT name=deckname>
<INPUT TYPE=SUBMIT value="Set Deck Name">
</FORM>
<br>

<br>
<a href="page.jsp?pageurl=emptydeck.jsp">Create Empty Deck</a>
