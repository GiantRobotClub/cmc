<% 
String toname =  request.getParameter("toname");
String fromname = session.getAttribute("loginname").toString();
String subject = request.getParameter("subject");
String message = request.getParameter("message");

//int attachcard = Integer.parseInt(request.getParameter("attachcard"));
int attachcard = 0;
int replyto = Integer.parseInt(request.getParameter("replyto"));

String returnstring = webrunner.cardmaster.CardmasterMessageSystem.SendMessage(toname,fromname,message,subject,attachcard);
if (returnstring.equals("Success")) {
%>
Message sent!

<%
	if (replyto != -1) webrunner.cardmaster.CardmasterMessageSystem.SetMessageReplied(replyto,true);
}else{%>
There was an error sending your message.  Please try again.<br>
ERROR: <%=returnstring%>
<% } %>

