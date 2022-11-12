<%String name = session.getAttribute("loginname").toString();
String storename=  request.getParameter("storename");
storename = storename.replaceAll("<","&lt;");
boolean success = webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreName(name,storename);
if (success) { %> Success!  Store is now called <%=storename%> <% } %>