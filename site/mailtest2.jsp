<%@ page import="sun.net.smtp.SmtpClient, java.io.*" %>
<%

String from="webrunner@cmc.redleaf.de";
String to="webrunner@eastlink.ca";
%>
TESTING
<%
try{
Process ls_proc = Runtime.getRuntime().exec("/usr/sbin/sendmail");
PrintWriter message = new PrintWriter( ls_proc.getOutputStream() );
message.println("To: " +to);
message.println("From: " + from);
message.println("Cc: " + from);
message.println("Subject: TEST");
message.println("This email has been sent from Cardmasters Conflict.");
message.println("To activate your account, go to the following address:");

message.println("If you have not signed up for an account, please disregard this message"); 
message.println("If your email has broken, go to the following url:");
message.println(".");
message.println(from);
String ls_str;
message.close();
ls_proc.getInputStream().close(); 
ls_proc.getOutputStream().close(); 
ls_proc.getErrorStream().close(); 

%>
Message ending..<%
}
catch (Exception e){ %>
	ERROR: <%=e%>
	<%
System.out.println("ERROR SENDING EMAIL:"+e);
}
%>
Complete!