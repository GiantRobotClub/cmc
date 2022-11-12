<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>CARDMASTER CONFLICT </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY text=white vlink=#AAB3FF alink=#AAB3FF link=#C0EDFE>
<jsp:useBean id="userd" class="webrunner.cardmaster.CardmasterUserData" scope="page"/>

<table bgcolor=black width=780><td colspan=2><a href="page.jsp?pageurl=news.htm"><img src="images/cmcslogan.gif" border=0 align=right><img src="images/cmclogo.gif" align=left border=0><img src="images/verstamp.gif" border=0></a></td></tr>
<tr>
<td bgcolor=#6B6B6B valign=top>


<jsp:include page="notice.html"/>
<table border=1>
<img src=images/spacera.gif><br>
<% boolean loggedin = true;
 if (session == null) loggedin=false; 
 if (session.getAttribute("loginname") == null) loggedin=false; 
 if (loggedin) { 
	String name = session.getAttribute("loginname").toString();
	
	userd.loadUser(name);
 %>


<tr><td>Logged in as <b><%=userd.user.name%></b></td></tr>
<tr><td>
<%=userd.user.wins%> wins - <%=userd.user.losses%> losses.<br><%=userd.user.points%> points<br>
Primary Deck: <%=userd.user.primarydeck%><br>
</td></tr>


<% } else { %>
<tr><td><FORM METHOD=POST ACTION="CMClogin.jsp">
Name: <INPUT TYPE=TEXT NAME=username SIZE=20><br>
Pass:<INPUT TYPE=PASSWORD NAME=password SIZE=20><INPUT TYPE=SUBMIT value="Login">
</FORM>
</td></tr>


<% } %>


<%

String pageurl = request.getParameter("pageurl");
if (pageurl == null) pageurl = "";
if (
 loggedin
|| pageurl.equals("news.htm") 
|| pageurl.startsWith("instructions.html") 
|| pageurl.startsWith("create.html")
|| pageurl.startsWith("createx.html")
|| pageurl.startsWith("createUser.jsp")
|| pageurl.startsWith("newbie.htm")
|| pageurl.startsWith("magic.htm")
|| pageurl.startsWith("verif.jsp")
|| pageurl.startsWith("verify.htm")){ 
	if (pageurl.indexOf('?') == -1) pageurl = pageurl + "?";
	else pageurl = pageurl + "&";
	java.util.Enumeration paramnames = request.getParameterNames();
	while (paramnames.hasMoreElements()) {
		String pname = (String)(paramnames.nextElement());
		String value = request.getParameter(pname);

		if (!(pname.equals("pageurl"))) {
			pageurl = pageurl + pname + "=" + value + "&";	


		}


	}


%>

<jsp:include page="<%=pageurl%>"/>
<% } else { %>
<jsp:include page="news.htm"/>
<% } %>
</table>
</td>
<td bgcolor=#6B6B6B valign=top>
<% if (!loggedin) { %>

<A href="page.jsp?pageurl=create.html"><img src=images/pickup.gif border=0></a><br>
<a href="http://forum.cmc.redleaf.de/index.php?c=4"><img src=images/forum.gif border=0></a><br>
<a href="page.jsp?pageurl=instructions.html"><img src=images/help.gif border=0></a>
<% } else { %>

<A href="page.jsp?pageurl=match.jsp"><img src=images/playnow.gif border=0></a><br>
<A href="page.jsp?pageurl=deckmanage.jsp"><img src=images/modifydeck.gif border=0></a><br>
<a href="page.jsp?pageurl=helpout.jsp"><img src="images/helpout.gif" border=0></a><br>


<A href="page.jsp?pageurl=store.jsp"><img src=images/store.gif border=0></a><br>
<A href="page.jsp?pageurl=storemanage.jsp"><img src=images/personalstore.gif border=0></a><br>
<a href="http://forum.cmc.redleaf.de/index.php?c=4"><img src="images/forum.gif" border=0></a><br>

<A href="page.jsp?pageurl=observelist.jsp"><img src=images/observe.gif border=0></a><br>

<a href="page.jsp?pageurl=story.html"><img src=images/universe.gif border=0></a><br>



<a href="page.jsp?pageurl=instructions.html"><img src="images/help.gif" border=0></a><br>



<% } %>
</BODY>
</HTML>
</td>
</tr>
</table>

<font size=-2 color=#B4B4B4>Cardmaster Conflict and everything therein is copyright 2003 Mark Shallow - - - VCHMEMSPR</font>
</body>