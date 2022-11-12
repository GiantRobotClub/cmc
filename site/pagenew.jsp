<HTML> 
<HEAD>
<%

String pageurl = request.getParameter("pageurl");
if (pageurl == null) pageurl = "";
%>
<TITLE>Cardmaster Conflict - <%=pageurl%></TITLE>
<link rel="shortcut icon" href="favicon.ico" >
<LINK REL="STYLESHEET" TYPE="text/css" HREF="cmc.css">
<LINK REL="ALTERNATE" TYPE="text/css" HREF="cmcfb.css">

<jsp:useBean id="userd" class="webrunner.cardmaster.CardmasterUserData" scope="page"/>
<script type="text/javascript"><!--//--><![CDATA[//><!--
startList = function() {
	if (document.all&&document.getElementById) {
		navRoot = document.getElementById("nav");
		for (i=0; i<navRoot.childNodes.length; i++) {
			node = navRoot.childNodes[i];
			if (node.nodeName=="LI") { 
				node.onmouseover=function() {
					this.className+=" over";
				}
				node.onmouseout=function() {
					this.className=this.className.replace(" over", "");
				}
			}
		}
	}
}
window.onload=startList;





//--><!]]></script>
<% boolean loggedin = true;
 if (session == null) loggedin=false; 
 if (session.getAttribute("loginname") == null) loggedin=false; 
 String name = "guy";
 boolean admin = false;
 boolean avataradmin = false;
 if (loggedin) { 
	name = session.getAttribute("loginname").toString();
	
	userd.loadUser(name); 

	
	
		
		
		if (userd.user.readExtraA("avataradmin") != null) 
		if (userd.user.readExtraA("avataradmin").equals("yes")) avataradmin = true;
		
		 if (userd.user.readExtraA("admin") != null)
		 if (userd.user.readExtraA("admin").equals("yes")) admin = true;

		if (admin) request.setAttribute("admin","yes");
			else request.setAttribute("admin","no");
	
	}
 %>



</HEAD>
<BODY bgcolor=#707078>

<div class=page>
<div class=top><%if (!loggedin) {%><img src="images/header.gif"><%}else{if(webrunner.cardmaster.CardmasterMessageSystem.HasNewMessage(name)){%><a href="page.jsp?pageurl=messages.jsp"><img src="images/header_newmessage.gif" border=0></a><%}else{%><img src="images/header.gif"><%}}%>
<div class=menu> 
<ul id="nav">
	<li>General
		<ul>
			<li><a href="page.jsp?pageurl=phpfeed.jsp&url=cmcnews.php" onmouseover="image.src='images/circleimage_news.gif';" onmouseout="image.src='images/circleimage_0.gif';">News</a></li>
			<%if (loggedin) {%>
			<li><a href="page.jsp?pageurl=match.jsp" onmouseover="image.src='images/circleimage_play.gif';" onmouseout="image.src='images/circleimage_0.gif';">Play</a></li>
			
			<li><a href="page.jsp?pageurl=observelist.jsp"  onmouseover="image.src='images/circleimage_observe.gif';" onmouseout="image.src='images/circleimage_0.gif';">Observe</a></li>

			<% } %>
			<li><a href="http://cmcforums.mrx.ca:8000/forumdisplay.php?fid=7"  onmouseover="image.src='images/circleimage_forum.gif';" onmouseout="image.src='images/circleimage_0.gif';">Forum</a></li>
			<li><a href="http://cmcforums.mrx.ca:8000/chat.php"  onmouseover="image.src='images/circleimage_forum.gif';" onmouseout="image.src='images/circleimage_0.gif';">IRC</a></li>
			<li><a href="page.jsp?pageurl=story.html" onmouseover="image.src='images/circleimage_2.gif';" onmouseout="image.src='images/circleimage_0.gif';">Story</a></li>
			<li><a href="page.jsp?pageurl=instructions.html" onmouseover="image.src='images/circleimage_help.gif';" onmouseout="image.src='images/circleimage_0.gif';">Help</a></li>
<li><a href="http://cmc.mrx.ca:8000/wiki/" onmouseover="image.src='images/circleimage_help.gif';" onmouseout="image.src='images/circleimage_0.gif';">Wiki</a></li>
		<li><a href="page.jsp?pageurl=goodies.html"  onmouseover="image.src='images/circleimage_goodies.gif';" onmouseout="image.src='images/circleimage_0.gif';">Goodies</a></li>
		<% if (admin || avataradmin) { %>
		<li><a href="page.jsp?pageurl=admin.jsp"  onmouseover="image.src='images/circleimage_admin.gif';" onmouseout="image.src='images/circleimage_0.gif';">Admin</a></li>
<% } %>


		</ul>
	</li>
	<%if (loggedin) {%>
	<li>Account
		<ul>
			<li><a href="page.jsp?pageurl=setprofile.jsp" onmouseover="image.src='images/circleimage_profile.gif';" onmouseout="image.src='images/circleimage_0.gif';">Profile</a></li>
			<li><a href="page.jsp?pageurl=deckmanage.jsp" onmouseover="image.src='images/circleimage_deck.gif';" onmouseout="image.src='images/circleimage_0.gif';">Decks</a></li>
			<li><a href="page.jsp?pageurl=voucher.jsp" onmouseover="image.src='images/circleimage_voucher.gif';" onmouseout="image.src='images/circleimage_0.gif';">Vouchers</a></li>
			<li><a href="page.jsp?pageurl=avatars.jsp" onmouseover="image.src='images/circleimage_avatar.gif';" onmouseout="image.src='images/circleimage_0.gif';">Avatars</a></li>
			<li><a href="page.jsp?pageurl=options.jsp" onmouseover="image.src='images/circleimage_profile.gif';" onmouseout="image.src='images/circleimage_0.gif';">Options</a></li>
			
			<li><a href="page.jsp?pageurl=messages.jsp" onmouseover="image.src='images/circleimage_messages.gif';" onmouseout="image.src='images/circleimage_0.gif';">C-Mail</a></li>

			<!--<li><a href="" >Avatar</a></li>-->
		</ul>
	</li>

	<li>Store
		<ul>
			<li><a class=menu href="page.jsp?pageurl=storemanage.jsp" onmouseover="image.src='images/circleimage_personal.gif';" onmouseout="image.src='images/circleimage_0.gif';">Personal</a></li>
			<li><a href="page.jsp?pageurl=publicstore.jsp" onmouseover="image.src='images/circleimage_public.gif';" onmouseout="image.src='images/circleimage_0.gif';">Public</a></li>
			<li><a href="page.jsp?pageurl=store.jsp" onmouseover="image.src='images/circleimage_packs.gif';" onmouseout="image.src='images/circleimage_0.gif';">Packs</a></li>
			<li><a href="http://cmc.mrx.ca:8000/cubecart/" onmouseover="image.src='images/circleimage_packs.gif';" onmouseout="image.src='images/circleimage_0.gif';">Donations</a></li>
			<li><a href="page.jsp?pageurl=allcards.jsp" onmouseover="image.src='images/circleimage_public.gif';" onmouseout="image.src='images/circleimage_0.gif';">Cardlist</a></li>
		</ul>
	</li><% } %>

</ul>
</div>



<div class=circle><img src="images/circleimage_0.gif" name="image"></div>

<script Language=Javascript><!--
image.src = "images/circleimage_0.gif";
-->
</script>

</div>
<div class=box1>
<div class=boxheader>
<a href=# class=helpblock><img align=right src="images/helptool.gif" border=0><span style="top:10px;
">Click on "Log Out" to log out of CMC.  Logs out automatically in approximately 30 minutes.</span>

</a>
Login Information
</div>
<% if (loggedin) { %>
Logged in as <%=name%><br>
<%=userd.user.wins%> wins - <%=userd.user.losses%> losses.<br><%=userd.user.points%> points<br>
Primary Deck: <%=userd.user.primarydeck%><br>
<a href="page.jsp?pageurl=logout.jsp">Log Out</a>
<%} else { %>
<FORM METHOD=POST ACTION="CMClogin.jsp">
Id:   <INPUT TYPE=TEXT NAME=username SIZE=15><br>
Pass: <INPUT TYPE=PASSWORD NAME=password SIZE=15><INPUT TYPE=SUBMIT value="Login"><a href="page.jsp?pageurl=create.html">Create New</a></form>

<%}%>
</div>

<div class=box2>
<div class=boxheader>
<a href=# class=helpblock><img align=right src="images/helptool.gif" border=0><span style="top:10px;
">Use the Cubecart interface to donate using paypal to receive cards and stuff!</span>

</a>
Donation Store
</div>

<div class=scroll>

<jsp:include page="donate.jsp"/>

</div>
</div>

<div class=box3>
<div class=boxheader>
<a href=# class=helpblock><img align=right src="images/helptool.gif" border=0><span style="top:10px;
">Major headlines here.  Minor updates and such will not be posted in this box.</span></a>
News Headlines
</div>
<jsp:include page="headlines.html"/>
</div>



<div class=text>

<%

if (pageurl == null) pageurl = "";
if (
 loggedin
|| pageurl.equals("news.htm") ||
( pageurl.equals("phpfeed.jsp") && request.getParameter("url") == "cmcnews.php")
|| pageurl.startsWith("instructions.html") 
|| pageurl.startsWith("create.html")
|| pageurl.startsWith("createx.html")
|| pageurl.startsWith("createUser.jsp")
|| pageurl.startsWith("newbie.htm")
|| pageurl.startsWith("magic.htm")
|| pageurl.startsWith("story.htm")
|| pageurl.startsWith("exp_")
|| pageurl.startsWith("goodies.html")

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
<jsp:include page="phpfeed.jsp?url=cmcnews.php"/>
<% } %>
</div>
</div>
</div>
</BODY>
</HTML> 