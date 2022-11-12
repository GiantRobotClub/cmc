<FORM METHOD=POST ACTION="page.jsp?pageurl=profile.jsp">

<%
String name =  (String)(session.getAttribute("loginname"));
%><INPUT TYPE=TEXT name="name" value="<%=name%>">
<INPUT TYPE=SUBMIT value="View Profile"><br><br></FORM><br><br>
<%
webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser(name);
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

	%>

<FORM METHOD=POST ACTION="page.jsp?pageurl=profilechange.jsp">
Location: <INPUT TYPE=TEXT name="location" value="<%=location%>" size=100><br><br>

Profile Text: <input type=text name="proftext" value="<%=profile%>" size=100><br><br>

Quote (If your quote has Quotation Marks in it, it won't appear properly here): <input type=text name="quote" value="<%=quote%>" size=100><br><br> 

Full address to image:<br><input type=text name="image" value="<%=userimage%>" size=100><br><br>
<input type=SUBMIT value="Set Profile!">
</FORM>
<Br><br>
If your profile becomes uneditable, you can reset it by clicking <a href="page.jsp?pageurl=resetprofile.jsp"> here</a>


