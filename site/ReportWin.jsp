<HTML>
<BODY>
<% int victory = Integer.parseInt(request.getParameter("a")); 
   int points = Integer.parseInt(request.getParameter("points")); 
   
  String type = request.getParameter("type");
  String card = request.getParameter("card");
   
   %>

<% if (victory ==1) { %>
You have won!<br>
<% } else { %>
You have lost.<br>
<% } %>

<% if (type != null) {%>
Victory by card: <%=card%><br />
Victory condition:<%=type%><br />

<% } %>
Points earned: <%=points%><br>
<a href="page.jsp?pageurl=phpfeed.jsp&url=cmcnews.php">Return to Lobby</a>

</body>
</html>