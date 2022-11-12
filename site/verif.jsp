
<% int verifcode = Integer.parseInt(request.getParameter("num"));
   String name = request.getParameter("name");
  
   if (webrunner.cardmaster.CardmasterData.userverify(name,verifcode)) { %>
   <font size=5>User Verification Complete!  You may now Login and play Cardmaster Conflict.<br />
   Now might be a good time to <a href="http:/http://cmc.mrx.ca:8000/wiki/index.php/Main_Page">Visit the Wiki</a>, read the <a href="http://cmc.mrx.ca:8000/wiki/index.php/Help">Help</a> and the <a href="http://cmc.mrx.ca:8000/wiki/index.php/Rules">Rules</a>
   </font>
   <% } else { %>
   <font size=5>User Verification Error.</font>
   <%=name%> <%=verifcode%>
   Please click <a href="verify.html">here</a> to attempt manual verification

  <% } %>
