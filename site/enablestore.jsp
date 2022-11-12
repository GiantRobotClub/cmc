<%String name = session.getAttribute("loginname").toString();
boolean enable =  Integer.parseInt(request.getParameter("enable")) == 1;
boolean success = webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreEnabled(name,enable);
if (success) { %> Success!  Store is now <%if (enable) { %>enabled<% } else { %> disabled <% }} %>