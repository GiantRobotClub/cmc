<HTML>
<BODY>
<% 
  String name = request.getParameter("a"); 
  String type = request.getParameter("type");
  String card = request.getParameter("card");
  
  
  %>

<%=name%> has won!<br />
<% if (type != null) {%>
Victory by card: <%=card%><br />
Victory condition:<%=type%><br />

<% } %>
<a href="page.jsp?pageurl=phpfeed.jsp&url=cmcnews.php">Return to Lobby</a>

</body>
</html>