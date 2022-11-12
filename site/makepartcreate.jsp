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
boolean success = true;
success = (type.length() > 0) && (gender.length() >0) && (partname.length() > 0) && (filename.length() >0);
success = success && (type.indexOf("#") == -1) && (partname.indexOf("#") == -1) &&(filename.indexOf("#") == -1) &&(gender.indexOf("#") == -1);
if (success) {
	editor.partdata = webrunner.cardmaster.CardmasterData.loadAvatarParts();
	success = editor.CreateNewPart(id,partname,filename,type,price,availableStore,availableBooster,gender);
	if (success) success= editor.SaveParts();
	if (success) editor.partdata = webrunner.cardmaster.CardmasterData.loadAvatarParts();
} else { %> NO TEXT <% } 

webrunner.cardmaster.CardmasterLogManager.WriteLog(session.getAttribute("loginname") + " creates avatar part data");
%> Create complete. <br> Success:<%=success%> <br>  If you wish, you can save this file: <a href="avatar.cmc">Avatar.cmc</a><br>
<a href="page.jsp?pageurl=parteditor.jsp">Return to Part Editor</a>
<% } else { %> Error: editor login lost <% } %> 
	