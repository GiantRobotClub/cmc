<jsp:useBean id="editor" scope="session" class="webrunner.cardmaster.CardmasterAvatarPartEditor"/>
<%
if (editor != null) { %>

	<% if (editor.login) { %>

		<%int id = Integer.parseInt(request.getParameter("id"));
		webrunner.cardmaster.CardmasterAvatarPart part = editor.partdata[id];%>
		Part number <%=id%><br>
		<FORM METHOD=POST ACTION="page.jsp?pageurl=makepartedit.jsp"><br>
		<input type="hidden" name="id" value="<%=id%>">
		Part name:  <input type=text name="partname" value="<%=part.partname%>" size=100><br>
		Filename:  <input type=text name="filename" value="<%=part.filename%>" size=100><br>
		Type:  <input type=text name="type" value="<%=part.type%>" size=100><br>
		Price:  <input type=text name="price" value="<%=part.price%>" size=100><br>
		Store:  <input type=text name="availableStore" value="<%=part.availableStore%>" size=100><br>
		Booster:  <input type=text name="availableBooster" value="<%=part.availableBooster%>" size=100><br>
		Artist:  <input type=text name="gender" value="<%=part.gender%>" size=100><br>
		<input type=SUBMIT value="Edit Part (And Save File)">
		</FORM>
	<% } else { %> Error: editor login lost <% } %> 
	
<% } else { %> Error: editor object lost <% } %> 