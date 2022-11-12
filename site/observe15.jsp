<%
	session.setMaxInactiveInterval(3600);
	%>
	<HTML>
<HEAD>
</HEAD>
<BODY bgcolor=#707078>

<CENTER>

<% if (session.getAttribute("loginname") == null || request.getParameter("number").toString().equals("") || request.getParameter("number") == null) {  %>
ERROR: NO VALUE
<% } else { %>

<APPLET
	archive = "cmc15test.jar"
	code	= "CardmasterClient"
	
	codebase = "/cmc/"
	roomnumber = "<%=request.getParameter("number")%>"
	observer = "yes"
	width	= "780"
	height	= "550"

	image = "images/exp_0.gif"
	myname = "<%=session.getAttribute("loginname")%>"
	port = "4170"
	<% //if (session.getAttribute("loginname").toString().startsWith("webrunner")) { %>
	<%//address = "localhost":%>
	<% //} else {  %>
	<% String temp =webrunner.cardmaster.CardmasterData.host;
	  //if (session.getAttribute("loginname").toString().startsWith("webrunner")) temp = "localhost";  %>
	address = <%=temp%>

	<%// } %>
	
	>

</APPLET>
<% } %><br>
If you see just a grey box, you probably need java 1.4.1.<br>
<a href="http://java.sun.com/j2se/1.4.2/download.html"> right here</a><br>
Under "Download J2SE v 1.4.2" the column "JRE" (NOTE: NOT JDK.  You don't need the development kit!) pick your platform.  It should be about 14 megs, at least for windows.<br><br>
If you get a grey box with a red X, it means the server is probably down.<br><br>
If the interface is empty, wait, the client is waiting for an update from the server.<br><br>
<iframe id="staylogged"
  name="staylogged"
  style="width:0px; height:0px; border: 0px"
  src="staylogged.jsp"></iframe>
</BODY>
</HTML>
