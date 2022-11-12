<jsp:useBean id="editor" scope="session" class="webrunner.cardmaster.CardmasterAvatarPartEditor"/>

<% if (editor.login) { 

int id = Integer.parseInt(request.getParameter("id"));
String partname = request.getParameter("partname");
String filename = request.getParameter("filename");
int price = Integer.parseInt(request.getParameter("price"));
String type = request.getParameter("type");
String gender = request.getParameter("gender");
boolean availableStore = false;
if (request.getParameter("availableStore").equals("true")) availableStore = true;

boolean availableBooster = false;
if (request.getParameter("availableBooster").equals("true")) availableBooster = true;
/*
editor.partdata[id].id = id;
editor.partdata[id].price = price;
editor.partdata[id].gender = gender;
editor.partdata[id].filename = filename;
editor.partdata[id].partname = partname;
editor.partdata[id].availableStore = availableStore;
editor.partdata[id].availableBooster = availableBooster;
editor.partdata[id].type = type;*/

boolean success = editor.CreateNewPart(id,partname,filename,type,price,availableStore,availableBooster,gender);
if (success) success= editor.SaveParts();
	
%> Create complete. <br> Success:<%=success%> <br>  If you wish, you can save this file: <a href="avatar.cmc">Avatar.cmc</a><br>
<a href="page.jsp?pageurl=parteditor.jsp">Return to Part Editor</a>
<% } else { %> Error: editor login lost <% } %> 
	