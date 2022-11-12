

<jsp:useBean id="userfactory" class="webrunner.cardmaster.CardmasterUserFactory" scope="session"/>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>

<jsp:setProperty name="userfactory" property="username" value="<%=session.getAttribute(\"username\")%>"/>  
<jsp:setProperty name="userfactory" property="password" value="<%=session.getAttribute(\"pass\")%>"/>  
<jsp:setProperty name="userfactory" property="email" value="<%=session.getAttribute(\"email\")%>"/>
<jsp:setProperty name="userfactory" property="deckNumber" value="<%=session.getAttribute(\"decknumber\")%>"/>  
<% userfactory.setPassword(session.getAttribute("pass").toString()); 

%>
<% 
String password = session.getAttribute("pass").toString();

 userfactory.setPassword(password); 
if (password == null || password.toString().equals("null")) { %> ERROR: Password NULL for some reason. <% } else { %>
<% if (userfactory.createUserFromData()) { %>
Character created!  You should receive an email shortly at your email address to verify your account.  <br><br>
While you're waiting, you may want to read the <a href="page.jsp?pageurl=instructions.html">Instructions</a> - taking note of the rules and stuff like avatar creation and whatnot.  Unless, you already have, in which case, um, the email should arrive shortly, I guess.

<% String to=session.getAttribute("email").toString();

webrunner.cardmaster.CardmasterLogManager.WriteLog(session.getAttribute("username") + " has created an account.  email: " + to + " verif: " +  userfactory.regcode );


%>
	

<%@ page import="sun.net.smtp.SmtpClient, java.io.*" %>
<%
	//String pass = session.getAttribute("pass").toString();
String name = session.getAttribute("username").toString();

String from=webrunner.cardmaster.CardmasterData.fromaddress;

try{

//SmtpClient client = new SmtpClient("smtp");
//client.from(from);
//client.to(to + ", " + from);
Process ls_proc = Runtime.getRuntime().exec("/usr/sbin/sendmail " + to);
//PrintStream message = client.startMessage();
PrintStream message = new PrintStream(ls_proc.getOutputStream());
message.println("From: " + from);
message.println("To: " +to);
message.println("Cc: " + from);
message.println("Subject: Cardmaster Conflict Registration");
message.println("This email has been sent from Cardmaster Conflict.");
message.println("To activate your account, go to the following address:");
message.println(webrunner.cardmaster.CardmasterData.urlhost + "/" + "page.jsp?pageurl=verif.jsp&name=" + java.net.URLEncoder.encode(session.getAttribute("username").toString()) + "&num=" + userfactory.regcode);
message.println("If you have not signed up for an account, please disregard this message"); 
message.println("If your email has broken, go to the following url:");
message.println(webrunner.cardmaster.CardmasterData.urlhost + "/" + "verify.htm");
message.println("And enter in your username " + session.getAttribute("username").toString() + " and the number " + userfactory.regcode);
message.println(".");

message.close();
//client.closeServer();

}
catch (Exception e){ 
System.out.println("ERROR SENDING EMAIL:"+e);
}
%>






<% if (!(userfactory.saveUser())) { %>
ERROR

<% } else { %>



<%
	//Random random = new Random();
	//int randomno = random.nextInt(15) +1;
	//int deckno = 600 + randomno;
	//while ( !deck.loadUser(session.getAttribute("username") + "") );
	//int newdeck = deck.giveNewPrebuiltToUser(deckno);
	
//	int boostno = deck.buyNewBooster(0);
//	webrunner.cardmaster.CardmasterData.userpatch(session.getAttribute("username") + "","addp",webrunner.cardmaster.CardmasterData.BOOSTCOST + 1000,"x");


}
} else { %>
Could not create character: Character/Email Already Exists or Invalid Name/Password

<% userfactory.deleteFile(); } %>

<% 
	session.invalidate(); 
	%>


<% } %>