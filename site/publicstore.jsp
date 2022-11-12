<jsp:useBean id="decklist" class="webrunner.cardmaster.CardmasterDeckList" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
decklist.loadUser(name);decklist.CountCards();%>

Buy Singles:<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=BuyCards.jsp">
Buy Into Deck: 
<SELECT NAME=deck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%>(<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>

<INPUT TYPE=SUBMIT value="Buy Cards">
</FORM>
