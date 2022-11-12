<% int num = Integer.parseInt(request.getParameter("deck"));  %>
Are you sure you want to delete deck number <%=num%>?<br>
<a href="page.jsp?pageurl=SellAllNew.jsp&deck=<%=num%>">Yes indeedy do!</a><br>
<a href="page.jsp?pageurl=deckmanage.jsp">No way, Jose!</a>
