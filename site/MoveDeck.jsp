<% String name = session.getAttribute("loginname").toString();
if (webrunner.cardmaster.CardmasterData.isTrading(name)) { %>
ERROR: TRADING
<% } else { %>


<% int num = Integer.parseInt(request.getParameter("deck")); 
int deck1 = num;
int deck2 = Integer.parseInt(request.getParameter("to"));%>
<% if (deck1 == deck2) { %>
Error: cannot empty into same deck <% } else { %>
Caution: You are about to empty your entire deck number <%=deck1%> into deck <%=deck2%>.  <%=deck1%> will be DESTROYED as a result.<br>
Are you sure you wish to do this?<br>
<a href="page.jsp?pageurl=MoveDeckYes.jsp&deck=<%=deck1%>&to=<%=deck2%>">Yes!</a><br>
<a href="page.jsp?pageurl=deckmanage.jsp">Oh man, no way!</a><br>
<% } } %>