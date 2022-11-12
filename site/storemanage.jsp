
<jsp:useBean id="decklist" class="webrunner.cardmaster.CardmasterDeckList" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% decklist.loadUser(name);decklist.CountCards();%>

<%webrunner.cardmaster.CardmasterDBStore store = webrunner.cardmaster.CardmasterDBStoreSystem.loadStore(name);%>

<% if (store == null) {%>Creating your personal store with default settings...  
<%=webrunner.cardmaster.CardmasterDBStoreSystem.CreateDefaultStore(name)%>
refresh please.<%}else{%>
<a href="page.jsp?pageurl=ShowOwnStoreDB.jsp">View Store (and set Prices)</a><br>

Your Store:<br>
Store Name: <%=store.storename%><br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=setstorenamedb.jsp">
Change Store Name: <INPUT TYPE=TEXT name=storename width=40 value="<%=store.storename%>"><br>
<INPUT TYPE=SUBMIT value="Submit">
</FORM>
Store Enabled: <%=store.enabled%>  <a href="page.jsp?pageurl=enablestore.jsp&enable=1">[enable]</a> <a href="page.jsp?pageurl=enablestore.jsp&enable=0">[disable]</a><br>
Automatic C-Mail Notification: <%=store.cmail%> <a href="page.jsp?pageurl=enablecmail.jsp&enable=1">[enable]</a> <a href="page.jsp?pageurl=enablecmail.jsp&enable=0">[disable]</a><br>
0 Price cards for sale: <%=store.sellfree%> <a href="page.jsp?pageurl=enablefree.jsp&enable=1">[enable]</a> <a href="page.jsp?pageurl=enablefree.jsp&enable=0">[disable]</a><br>
Lifetime sales: <%=store.salesforever%><br>
Last Modified: <%=store.modmonth%>/<%=store.modday%>/<%=store.modyear%><Br>
Last Bought From: <%=store.buymonth%>/<%=store.buyday%>/<%=store.buyyear%><br>



<br>
Move Cards into your store<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=ShowDeck.jsp">
From: 
<SELECT NAME=deck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=HIDDEN value=true name=store>
<INPUT TYPE=SUBMIT value="Move Cards">
</FORM>
<br>
<br>
Move Cards from your store<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=ShowOwnStoreDB.jsp">
To: 
<SELECT NAME=todeck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="Retreive Cards">
</FORM>
<br>

<br>
Move Cards from your old store<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=ShowOwnStore.jsp">
To: 
<SELECT NAME=todeck>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="Retreive Cards">
</FORM>
<br>


<br><br>
Buy cards from other players' stores:<br>
<FORM METHOD=POST ACTION="page.jsp?pageurl=liststoresdb.jsp">
<SELECT NAME=to>
<%for (int i=1;i<decklist.numDecks();i++) { %>
<OPTION VALUE=<%=decklist.getDeck(i)%>><%=decklist.getDeck(i)%> - <%=decklist.getDeckName(decklist.getDeck(i))%> - <%=decklist.primaryDeckMarker(i)%> (<%=decklist.getDeckSize(i)%>)
<% } %>
</SELECT>
<INPUT TYPE=SUBMIT value="List Stores">
</FORM>


<a href="liststores_textdb.jsp">List Stores Text Output (for searchers)</a>

<% } %>