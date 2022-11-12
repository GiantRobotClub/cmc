<%@ page import="java.io.*" buffer="256kb"  %>

<%String name = session.getAttribute("loginname").toString();%>
<%
int deckto = Integer.parseInt(request.getParameter("to"));

webrunner.cardmaster.CardmasterDBStore[] stores = webrunner.cardmaster.CardmasterDBStoreSystem.loadEnabledStores();

if (stores != null)
for (int i=0;i<stores.length;i++) if (stores[i] != null){


%>
<%=stores[i].name%>'s store: <a
<a href="page.jsp?pageurl=viewStoredb.jsp&name=<%=stores[i].name%>&to=<%=deckto%>"><%=stores[i].storename%></a> 
[Update: : <%=stores[i].modmonth%>/<%=stores[i].modday%>/<%=stores[i].modyear%>]
[Bought: <%=stores[i].buymonth%>/<%=stores[i].buyday%>/<%=stores[i].buyyear%>] 
<% if (stores[i].name.equals(name)) { %> [YOUR STORE] <% } %>
<% 
	if (webrunner.cardmaster.CardmasterDBStoreSystem.TooOld(stores[i])) {
		webrunner.cardmaster.CardmasterDBStoreSystem.SetStoreEnabled(stores[i].name,false);

		%>[AUTO-DISABLED]<%
	}

%>
<br>

<%
	
}  %>


Store list complete