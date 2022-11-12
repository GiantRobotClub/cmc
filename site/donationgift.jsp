<%

String toname=request.getParameter("toname");

int amount = Integer.parseInt(request.getParameter("amount"));


if (( (String)request.getAttribute("admin") )  .equals("yes")) {

int current = 529;
int last1 = current - 1;
int last2 = current -2;
int classic = 520;
int divisor = 5;
%> Donation for <%=toname%> <%
for (int i =current;i>=500;i--) {

	%>
	<% if (i == current) {%> <br><br>CURRENT DONATIONS:<br><br><%}
	 else if (i == last2-1) { %> <br><br>RECENT DONATIONS:<br><br> <% }
	 else if (i == classic) { divisor = 10; %> <BR><BR>CLASSIC DONATION:<br><br> <% }
	 else if (i == classic-1) { %> <BR><BR>OLD DONATIONS:<BR><BR> <% } %>

	 
<img src="images/gift_<%=i%>.gif">	<%=i%> <a href="page.jsp?pageurl=donategift.jsp?d=<%=i%>&n=<%=toname%>&m=<%=amount%>&c=<%=divisor%>">Give <%=amount/divisor%></a>
<br>
<% } %>

	<% } else {%> Admins Only <% } %>