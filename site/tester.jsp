<TR><TD>
<% int verifcode = Integer.parseInt(request.getParameter("num"));
   String name = request.getParameter("name");
   if (webrunner.cardmaster.CardmasterData.userverify(name,verifcode)) { %>
   <font size=5>User Verification Complete!  You may now Login and play Cardmasters Conflict.</font>
   <% } else { %>
   <font size=5>User Verification Error.</font>
   <%=name%> <%=verifcode%>
   Please click <a href="verify.html">here</a> to attempt manual verification

  <% } %>
</TD></TR>