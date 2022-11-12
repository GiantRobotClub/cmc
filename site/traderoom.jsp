<HTML>
<HEAD>
</HEAD>
<BODY BGCOLOR="white" text=WHITE link=WHITE alink=WHITE vlink=WHITE>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<CENTER>
<table bgcolor=black width=780><td><img src="images/cmclogo.gif" align=left><img src="images/cmcslogan.gif" align=right></td></tr>
<tr><td bgcolor=#6B6B6B>
<% if (session.getAttribute("loginname") == null || request.getParameter("room").toString().equals("") || request.getParameter("room") == null) {  %>
ERROR: Loginname or Opponent data null.  You may need to login again.<br>
<%=session.getAttribute("loginname")%> 
<%=request.getParameter("room")%>
<% } else { %>
<% webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser((String)session.getAttribute("loginname"));
String jarname = user.readExtraA("ver");
if (jarname == null) jarname = "15";
%>
<APPLET
	archive = "cmc<%=jarname%>.jar"
	codebase = "<%=webrunner.cardmaster.CardmasterData.codebase%>"
	code	= "CardmasterTradeClient"
	player = "<%=session.getAttribute("loginname")%>"
	checkcode = "<%=webrunner.cardmaster.CardmasterData.securityConvertToBinary((String)session.getAttribute("loginname"))%>"
	roomnumber = "<%=request.getParameter("room")%>"
	width	= "780"
	height	= "550"
	
	image = "images/exp_0.gif"
	port = "<%=webrunner.cardmaster.CardmasterData.GamePort%>"
	<%// if (session.getAttribute("loginname").toString().startsWith("webrunner")) { %>
	<%//address = "localhost":%>
	<% //} else {  %>
	address = <%=webrunner.cardmaster.CardmasterData.host%>
	<%// } %>
	>

</APPLET>
<% } %><br>
If you see just a grey box, you probably need java 1.4.1.<br>
<a href="http://java.sun.com/j2se/1.4.2/download.html"> right here</a><br>
Under "Download J2SE v 1.4.2" the column "JRE" (NOTE: NOT JDK.  You don't need the development kit!) pick your platform.  It should be about 14 megs, at least for windows.<br><br>
If you get a grey box with a red X, it means the server is probably down.<br><br>
</td></tr>
</CENTER>
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
