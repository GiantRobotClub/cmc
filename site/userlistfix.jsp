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
for (int i=1;i<users.length;i++) {
%>
<% int gamesplayed = users[i].wins + users[i].losses;%>
<tr <% if (!users[i].emailverified) { %>bgcolor=#990033<% } else if (gamesplayed < 2) {%>bgcolor=#8A5853<%}%>>
<% 
accounts++; 
if (users[i].emailverified) verified++; 
if (gamesplayed >= 2) playedmorethanfive++;

if (users[i].points < 0 || users[i].points > 5000) {
%>

<td><a href="page.jsp?pageurl=profile.jsp&name=<%=users[i].name%>"><%=users[i].name%></a></td><td><%=users[i].wins%></td><td><%=users[i].losses%></td><td><%=users[i].points%></td>
<%


	if (users[i].points < 0)
	webrunner.cardmaster.CardmasterData.userpatch(users[i].name,"addp",-users[i].points,"x");
	else
	webrunner.cardmaster.CardmasterData.userpatch(users[i].name,"addp",5000-users[i].points,"x");



if (( (String)request.getAttribute("admin") )  .equals("yes")) {
	%>
	<td><a href="page.jsp?pageurl=verif.jsp&num=<%=users[i].verifcode%>&name=<%=users[i].name%>"> <%=users[i].verifcode%></a></td><td> <a href="mailto=<%=users[i].email%>"><%=users[i].email%></a> </td><td> <a href="page.jsp?pageurl=userPatch.jsp&name=<%=users[i].name%>&code=delu&text=delu&num=1"><%=users[i].month +1%> / <%=users[i].year%></a></td>

	<% } %>
	</tr>


	<% } } %>

</table>
Total: <%=accounts%> accounts, <%=verified%> verified, <%=playedmorethanfive%> played 2 games or more.

</td></tr>
