
<% String name = session.getAttribute("loginname").toString(); %>
<% webrunner.cardmaster.CardmasterAvatarPart[] parts =	 webrunner.cardmaster.CardmasterData.loadAvatarParts(); %>
<% String overtype = request.getParameter("type"); 
int totalparts = 0;%>
Available parts for <%=overtype%>
<%
for (int i=0;i<webrunner.cardmaster.CardmasterData.NUMBER_OF_PARTS;i++) {
	boolean no = true;%>
<% if (parts[i] != null) { if ( parts[i].availableStore&& (parts[i].type.equals(overtype) || overtype.equals("all")) ) { %>
<%
		totalparts++;
request.setAttribute("thePart",parts[i]);
	String type = overtype;
	if (overtype.equals("all")) type = parts[i].type;
String artistname = "?";
	if (! (parts[i].gender.equals("b") || parts[i].gender.equals("m") || parts[i].gender.equals("f") ))
			artistname = parts[i].gender;


	request.setAttribute("theUnderText","Type:"+type+"<br>Artist:"+ artistname+"<br><a href=page.jsp?pageurl=BuyPart.jsp&type="+type+"&id="+i+">Buy This!</a>");
	
	%>

<jsp:include page="singlepart.jsp"/>
<% } else { no = false; } } else {no=false;} if (!no) { %><%} } %>
<div style="clear:left"> Total parts: <%=totalparts%></div>