<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<% if (session.getAttribute("loginname") == null) {%>
ERROR: You have to Login again.
<% } else { %>
Instructions:<Br>
Clicking on someones names challenges them.  You can only challenge one person at once.  If they challenge you back, the game starts.<br>
If YOUR name does not appear, then return to the lobby for approximately one minute and return.<br>
If you see just a grey box, you probably need the Java4 Runtime Environment.<br>
<a href="http://java.sun.com/j2se/1.4.2/download.html"> right here</a><br>
Under "Download J2SE v 1.4.2" the column "JRE" (NOTE: NOT JDK.  You don't need the development kit!) pick your platform.  It should be about 14 megs, at least for windows.<br><br>
If you get a white box with a red X, it means the server is probably down.<br>

<br>
<% webrunner.cardmaster.CardmasterUser user = webrunner.cardmaster.CardmasterData.loadUser((String)session.getAttribute("loginname"));
String jarname = user.readExtraA("ver");
if (jarname == null) jarname = "15";
%>
<APPLET
archive = "cmc<%=jarname%>.jar"
	codebase = "<%=webrunner.cardmaster.CardmasterData.codebase%>"
	code	= "CardmasterMatchClient"
	player = "<%=(String)session.getAttribute("loginname")%>"
	check = "<%=webrunner.cardmaster.CardmasterData.securityConvertToBinary((String)session.getAttribute("loginname"))%>"
	width	= "700"
	height	= "500"
	
	image = "images/exp_0.gif"
	port = "<%=webrunner.cardmaster.CardmasterData.MatchPort%>"
	<% String temp =webrunner.cardmaster.CardmasterData.host;
	  // if (session.getAttribute("loginname").toString().startsWith("webrunner")) temp = "localhost";  %>
	<% // { %>
	<% //address = "localhost": %>
	<% //} else {  %>
	<%//--address = "24.222.230.169"%>
	address = <%=temp%>
	<% //} %>
	>
<% } %>
</APPLET><br>

<iframe id="staylogged"
  name="staylogged"
  style="width:0px; height:0px; border: 0px"
  src="staylogged.jsp"></iframe>
