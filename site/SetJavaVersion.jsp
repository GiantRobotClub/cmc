<% 
String name = session.getAttribute("loginname") + "";
String javaver = request.getParameter("ver");

webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,"ver%" + javaver + "%" + javaver + "%");
%>
Set java version to <%=javaver%>
