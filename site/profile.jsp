<tr><td>
<%String name = request.getParameter("name"); 
webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
if (user == null) { %> USER NOT FOUND <% } else {
	String userimage = user.readExtraA("image");
	if (userimage == null) userimage = "/images/cardback.gif";
	userimage = userimage.replace('^',':');

	String location = user.readExtraA("location");
	if (location == null) location = "Not Entered";
    location = location.replace('^',':');

	String quote = user.readExtraA("profile");
	if (quote == null) quote = "Not Entered";
	quote = quote.replace('^',':');

	String profile = user.readExtraB("profile");
	if (profile == null) profile = "Not Entered";
	profile = profile.replace('^',':');

	boolean admin = false;
	if (user.readExtraA("admin") != null)
		if (user.readExtraA("admin").equals("yes"))
		  admin = true;

	int totalgames = user.wins + user.losses;
	String userstring = "Citizen";
	if (totalgames > 10 && totalgames <50) userstring = "Acolyte";
    else if (totalgames >= 50 && totalgames < 100) userstring = "Apprentice";
	else if (totalgames >= 100 && totalgames < 200) userstring = "Warrior";
	else if (totalgames >= 200 && totalgames < 300) userstring = "Channeler";
	else if (totalgames >= 300 && totalgames < 400) userstring = "General";
	else if (totalgames >= 400 && totalgames < 500) userstring = "Golem";

	else if (totalgames >= 500 && totalgames < 600) userstring = "Phoenix";
	else if (totalgames >= 600 && totalgames < 700) userstring = "Abomination";
	else if (totalgames >= 700 && totalgames < 800) userstring = "Hawk";
	else if (totalgames >= 800 && totalgames < 900) userstring = "Dragon";
    else if (totalgames >= 900 && totalgames < 1000) userstring = "Unique";
	else if (totalgames >= 1000) userstring = "CMC Legend";
	if (admin) userstring = userstring + "<br>ADMINISTRATOR";
    if (user.wins == user.losses) userstring = userstring + "<br>Special Balance Award";
	if (totalgames > user.wins * 25) userstring = userstring + "<br>Special Loser Award";
	if (totalgames > user.losses * 25) userstring = userstring + "<br>Special Winner Award";
	if (user.points >=10000) userstring = userstring + "<br>Pocket Money Award";
	if (user.points >= 50000) userstring = userstring + "<br>Rich as Kings";
	if (user.points >= 1000000) userstring = userstring + "<br>Millionare";
	if (user.points < 0) userstring = userstring + "<br>In Debt";
	
	if (user.decks.length >= 25) userstring = userstring + "<br>Cluttered Cardbox";
	if (user.losses == 0) userstring = userstring + "<br>Undefeated!";
	if (user.wins == 0) userstring = userstring + "<br>Unvictorious";
	%>
<table><tr><td width=1>
 <img src="<%=userimage%>"><img src="avatars/<%=user.name%>.png">
   </td>
   <td>
  <font size=+1><%=user.name%></font><br>
   Wins: <%=user.wins%> Losses: <%=user.losses%><br>
   Total Games: <%=user.wins + user.losses%><br>
	
   <b><%=userstring%></b>
   
   
   </td></tr>
   <tr><td colspan=2>
   Location: <%=location%><br>
   
   Profile: <%=profile%><br>
   


   </td>
   </tr>
   <tr><td colspan=2><b><i><font size=+1><%=quote%></font></i></b></td></tr>
   </table>
   
		













<% } %>
</td></tr>