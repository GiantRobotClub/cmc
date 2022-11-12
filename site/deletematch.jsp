<tr><td>
<% 
String yourname = session.getAttribute("loginname") + "";
int accounts = 0;
int verified = 0;
int playedmorethanfive = 0;
%>
<table><tr>
<td>
Name</td><td>Wins</td><td>Losses</td><td>Points</td>
<%
if (( (String)request.getAttribute("admin") )  .equals("yes")) { %>
<td>Verifcode</td>

<td>email</td>
<td>Last Login</td>
<% } %>
<% webrunner.cardmaster.CardmasterUser[] users = webrunner.cardmaster.CardmasterData.loadUserData();
int year = Integer.parseInt(request.getParameter("year"));
int mo = Integer.parseInt(request.getParameter("mo"));
int v = Integer.parseInt(request.getParameter("v"));
int gp = Integer.parseInt(request.getParameter("gp"));
for (int i=1;i<users.length;i++) {
%>
<% int gamesplayed = users[i].wins + users[i].losses;%>
<tr <% if (!users[i].emailverified) { %>bgcolor=#990033<% } else if (gamesplayed < 2) {%>bgcolor=#8A5853<%}%>>
<% 
accounts++; 
if (users[i].emailverified) verified++; 
if (gamesplayed >= 2) playedmorethanfive++;
%>

<td><%=users[i].name%></td><td><%=users[i].wins%></td><td><%=users[i].losses%></td><td><%=users[i].points%></td>
<%
if (( (String)request.getAttribute("admin") )  .equals("yes")) {
	%>
	<td> <a href="page.jsp?pageurl=userPatch.jsp&name=<%=users[i].name%>&code=delu&text=delu&num=1"><%=users[i].month +1%> / <%=users[i].year%></a></td> <td>
	<%if (users[i].year == year && users[i].month+1 == mo && gp >= gamesplayed && ( (v == 1 && users[i].emailverified) || (v == 0 && !users[i].emailverified))) {
		webrunner.cardmaster.CardmasterData.userpatch(users[i].name,"delu",1,"delu"); %>
	Deleted
	
	<%} else { %>
	
	Not Deleted
	
	<% } %>
		</td>
	<% } %>
	</tr>


	<% } %>

</table>
Total: <%=accounts%> accounts, <%=verified%> verified, <%=playedmorethanfive%> played 2 games or more.

</td></tr>
