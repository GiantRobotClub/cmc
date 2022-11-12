
<jsp:useBean id="deckswitcher" class="webrunner.cardmaster.SwitchPrimaryDeckBean" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<%int deckno = Integer.parseInt(request.getParameter("deck"));%>
<%
if (deckswitcher.switchPrimaryDeckTo(name,deckno) == -1) { %>
Couldn't switch deck.
<% } else { %>
Switched playing deck to <%=deckno%>

<% } %>

