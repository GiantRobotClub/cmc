<%String name = session.getAttribute("loginname").toString();%>
<% if (name.equals("webrunner")) { 
String othername = request.getParameter("name");
webrunner.cardmaster.CardmasterData.disableStore(othername);
webrunner.cardmaster.CardmasterData.userpatch(othername,"extr",0,"store" + "%" + "no" + "%" + "Temporarily Disabled" + "%");


 webrunner.cardmaster.CardmasterData.unlockStore(othername); 
%>  DISABLED <% } else { %> NOT DISABLED <% }

%>