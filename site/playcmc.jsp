<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	session.setMaxInactiveInterval(3600);
	%>

<% if (session.getAttribute("loginname") == null || request.getParameter("room").toString().equals("") || request.getParameter("room") == null) {  %>
ERROR: Loginname or Opponent data null.  You may need to login again.<br>
<%=session.getAttribute("loginname")%> 
<%=request.getParameter("room")%>
<% } else { %>
<% webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser((String)session.getAttribute("loginname"));
String jarname = user.readExtraA("ver");
if (jarname == null) jarname = "15";
%>

<HTML>
<HEAD>
<TITLE>Cardmaster Conflict - GAME IN ROOM <%=request.getParameter("room")%></TITLE>
</HEAD>
<BODY bgcolor=#707078>

<CENTER>


<APPLET
archive = "cmc<%=jarname%>.jar"
	codebase = "<%=webrunner.cardmaster.CardmasterData.codebase%>"
	code	= "CardmasterClient"
	player = "<%=session.getAttribute("loginname")%>"
	checkcode = "<%=webrunner.cardmaster.CardmasterData.securityConvertToBinary((String)session.getAttribute("loginname"))%>"
	roomnumber = "<%=request.getParameter("room")%>"
	observer = "no"
	width	= "780"
	height	= "550"
	image = "images/exp_0.gif"
	port = "<%=webrunner.cardmaster.CardmasterData.GamePort%>"
		<% String temp =webrunner.cardmaster.CardmasterData.host;
	   //if (session.getAttribute("loginname").toString().startsWith("webrunner")) temp = "localhost";  %>
	address = <%=temp%>
	<%// } %>
	>

</APPLET>
<% } %>

<br>
If you see just a grey box, you probably need java 1.4.1.<br>
<a href="http://java.sun.com/j2se/1.4.2/download.html" target="new"> right here</a><br>
Under "Download J2SE v 1.4.2" the column "JRE" (NOTE: NOT JDK.  You don't need the development kit!) pick your platform.  It should be about 14 megs, at least for windows.<br><br>
If you get a grey box with a red X, it means the server is probably down.<br><br><br><br>
Utilizes Java implementation of the <a href="http://www.cs.umd.edu/users/seanl/gp/">Mersenne Twister random number algorithm </a>Copyright (c) 2003 by Sean Luke.  Portions copyright (c) 1993 <br>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-1997921-1";
urchinTracker();
</script>

<iframe id="staylogged"
  name="staylogged"
  style="width:0px; height:0px; border: 0px"
  src="staylogged.jsp"></iframe>


</BODY>
</HTML>
