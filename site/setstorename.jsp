<tr><td>

<%String name = session.getAttribute("loginname").toString();%>
<% 
	String storename = request.getParameter("storename");
	String enablestore = request.getParameter("enable");

	String text = "store";
	String dataa = enablestore;
	String datab = storename.replace('%','-');
	datab = datab.replace('#','-');
	datab = datab.replace(':','-');
	if (datab.length() == 0) datab = " ";
	if (datab.equals("")) datab = " ";

	if (datab.indexOf('%') == -1) {
	if (text != null && dataa != null && datab != null) {

	webrunner.cardmaster.CardmasterData.userpatch(name,"extr",0,text + "%" + dataa + "%" + datab + "%");

	if (enablestore.equals("yes")) webrunner.cardmaster.CardmasterData.enableStore(name);
	else webrunner.cardmaster.CardmasterData.disableStore(name);
webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " set store name to [" + storename + "] enable: " + enablestore);

		%> Store Name set to <%=storename%><br>Store enabled:<%=enablestore%>
 <% } else { %> ERROR <% } } else {%> ERROR: Cannot put % in store name <% } %> 
	


</td></tr>