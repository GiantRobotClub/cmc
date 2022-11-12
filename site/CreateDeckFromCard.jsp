
<jsp:useBean id="deckf" class="webrunner.cardmaster.CardmasterDeckFactory" scope="session"/>
<jsp:setProperty name="deckf" property="*"/>

<%String name = session.getAttribute("loginname").toString();%>
<%
if ( ((String)request.getAttribute("admin")).equals("yes")) {



int cardid = Integer.parseInt(request.getParameter("card"));
int quantity = Integer.parseInt(request.getParameter("no"));
String toname = request.getParameter("toname");
String deckname = request.getParameter("deckname");

toname = webrunner.cardmaster.CardmasterData.loadUser(toname).name.toString(); 
int deckno = deckf.createDeckFromCard(cardid,quantity,deckname);
boolean success = false;
if (deckno != -1) {
	success = webrunner.cardmaster.CardmasterData.userpatch(toname,"addd",deckno,"x");
}
%>

<% if (deckno != -1 && success) {
	webrunner.cardmaster.CardmasterLogManager.WriteLog(name + " gave card " + cardid + " to deck " + deckno + " of user " + toname);
	
	%>
<%=quantity%>x Card <%=cardid%> given to  <%=toname%> in deck no <%=deckno%>
<% } else { %>
Card could not be given
<%}

 }%>
