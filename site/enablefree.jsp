<%String name = session.getAttribute("loginname").toString();
boolean enable =  Integer.parseInt(request.getParameter("enable")) == 1;
boolean success = webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreSellFree(name,enable);
if (success) { %> Success!  Selling of 0-price cards is now <%if (enable) { %>enabled.  BE CAREFUL!  Only set this on if you REALLY want it on.<% } else { %> disabled <% }} %>