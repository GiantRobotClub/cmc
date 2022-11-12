<%String name = session.getAttribute("loginname").toString();
boolean enable =  Integer.parseInt(request.getParameter("enable")) == 1;
boolean success = webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreCMail(name,enable);
if (success) { %> Success!  C-Mail Notification is now <%if (enable) { %>enabled<% } else { %> disabled <% }} %>