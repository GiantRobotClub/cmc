
<%if (( (String)request.getAttribute("admin") )  .equals("yes")) {%>
<% 
String oldname = request.getParameter("oldname");
String newname = request.getParameter("newname");
%>


<% webrunner.cardmaster.CardmasterUser olduser = webrunner.cardmaster.CardmasterData.loadUserOldFolder(oldname); 
olduser.name = newname;
 webrunner.cardmaster.CardmasterData.saveUserOld(olduser);%>

Complete!  Try viewing user here: <a href="page.jsp?pageurl=fulluserdata.jsp&name=<%=newname%>">Data for<%=newname%></a>








<% } else { %> not an admin <% } %>