<%@ page import="sun.net.smtp.SmtpClient, java.io.*" %>
<%
String from="webrunner@cmc.redleaf.de";
String to="webrunner@cmc.redleaf.de";
%>
TEST
<%
try{
SmtpClient client = new SmtpClient("localhost");

client.from(from);
client.to(to);
%>
Okay...
<%
PrintStream message = client.startMessage();
message.println("From: " + from);
message.println("To: " + to);
message.println("Subject: Cardmasters Conflict Registration");
message.println("This email has been sent from Cardmasters Conflict.");
message.println("To activate your account, go to the following address:");

message.println("If you have not signed up for an account, please disregard this message"); 
message.println("If your email has broken, go to the following url:");
%>
Message ending..<%
client.closeServer();
}
catch (IOException e){ %>
	ERROR: <%=e%>
	<%
System.out.println("ERROR SENDING EMAIL:"+e);
}
%>
Complete!