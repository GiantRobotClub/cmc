<HTML>
<HEAD>
</HEAD>
<BODY>
TESTING
<% webrunner.cardmaster.CardmasterAvatarManager manager = new webrunner.cardmaster.CardmasterAvatarManager();
boolean bool = manager.BuildAvatar("webrunner");
%>
DONE!
<%=bool%>
<%if (bool) { %>
<img src="avatars/webrunner.png">

<% } else {%>
TRY AGAIN, it might be building your basic avatar right now
<% } %>
</BODY>
</HTML>