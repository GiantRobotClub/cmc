
<%@ page import="java.io.*" buffer="256kb"  %>
<%

File userdir = new File(webrunner.cardmaster.CardmasterData.SITEFOLDER + "avatars/");
File[] files = userdir.listFiles();
if (files != null)
for (int i=0;i<files.length;i++) {%>

<%String username = files[i].getName();%>
<% if (username.endsWith(".png")) { %>
<% String lastmodified = new java.util.Date(files[i].lastModified()).toString();%>
<img src="avatars/<%=username%>" alt="<%=username%> modified: <%=lastmodified%>" title="<%=username%> modified: <%=lastmodified%>">
<% } %>
<% } %>
