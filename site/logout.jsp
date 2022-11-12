<% 
String yourname = session.getAttribute("loginname") + "";
webrunner.cardmaster.CardmasterLogManager.WriteLog(yourname + " has logged out");

session.invalidate(); 


%>
You have been logged out of Cardmaster Conflict.