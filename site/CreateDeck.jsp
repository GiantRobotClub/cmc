<HTML>
<BODY>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterDeckFactory" scope="session"/>
<jsp:setProperty name="deck" property="*"/>
<% boolean nologin = true; %>
Logging In...
<% if (true || nologin) { %>
<% deck.init(); %>
Creating new Starter Deck..... 
<%    
boolean success = true;
String pass = request.getParameter("pass");
 if (pass.equals("")) success =false;
  String email = request.getParameter("email");
   if (email.equals("")) success =false;

   String passcheck = request.getParameter("passtest");
   String emailcheck  = request.getParameter("emailtest");
   boolean test = false;
   if (passcheck.equals(pass) && emailcheck.equals(email)) test = true;
   String name = request.getParameter("name");
   if (!webrunner.cardmaster.CardmasterData.isAlphaNumeric(name)) { success = false; test = false; }
   if (!test) {

%>
ERROR: Pass or email double check incorrect OR name not alphanumeric and spaces <% } else { %>
<% int deckno =  deck.newPreCopy(Integer.parseInt(request.getParameter("deckn"))); 
   
   if (deckno == -1) success = false;
   session.setAttribute("decknumber", ""+deckno);
   

  

   if (name.equals("")) success =false;

   session.setAttribute( "username", name); 
  

   session.setAttribute( "pass", pass);
 

   session.setAttribute("email",email);
   %>
<% if (success) {%>
<%=deckno%>

<a href="page.jsp?pageurl=createUser.jsp">click to continue...</a><br>
<%=session.getAttribute("username")%>
<% } else { %>
ERROR: Invalid data
<%=deckno%> <%=pass%> <%=name%> <%=email%>
<% } } }else  {%>
Cannot create character at this time.  Server is not fully up, yet.
<% } %>
</body>
</html>