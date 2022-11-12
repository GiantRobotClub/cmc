
<%@ page import="java.util.StringTokenizer" %>

<div class = "avatarpart">
<%

webrunner.cardmaster.CardmasterAvatarPart part = (webrunner.cardmaster.CardmasterAvatarPart)(request.getAttribute("thePart"));

String under = (String)(request.getAttribute("theUnderText"));
%>

<div class=part>
<img src="avatarparts/<%=part.filename%>.png">
</div>
<div class=partname>
<%=part.partname%>
</div>
<div class=price>
Price: <%=part.price%> points
</div>

<%if (under != null) {%>
<div class=under>
<%=under%>
</div>
<%}%>
</div>