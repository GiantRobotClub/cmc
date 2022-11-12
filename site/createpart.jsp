<jsp:useBean id="editor" scope="session" class="webrunner.cardmaster.CardmasterAvatarPartEditor"/>
<%
if (editor != null) { %>

	<% if (editor.login) { %>

		<%int id = Integer.parseInt(request.getParameter("id")); %>
		Part number <%=id%><br>
		<FORM METHOD=POST ACTION="page.jsp?pageurl=makepartcreate.jsp"><br>
		<input type="hidden" name="id" value="<%=id%>">
		Part name:  <input type=text name="partname" value="Blank" size=100><br>
		Filename:  <input type=text name="filename" value="blank" size=100><br>
		Type:  <input type=text name="type" value="xx" size=100><br>
		Price:  <input type=text name="price" value="0" size=100><br>
		Store:  <input type=text name="availableStore" value="false" size=100><br>
		Booster:  <input type=text name="availableBooster" value="false" size=100><br>
		Artist:  <input type=text name="gender" value="b" size=100><br>
		<input type=SUBMIT value="Create Part (And Save File)">
		</FORM>
	<% } else { %> Error: editor login lost <% } %> 
	
<% } else { %> Error: editor object lost <% } %> 