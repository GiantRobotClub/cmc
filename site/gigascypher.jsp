<html><body>
<%
String[] text = new String[5];
text[0] = request.getParameter("text1");
 text[1] = request.getParameter("text2");
text[2] = request.getParameter("text3");
 text[3] = request.getParameter("text4");
 text[4] = request.getParameter("text5");

%>

<% 
for (int i=0;i<5;i++) {	
	String texta = text[i];
	texta = texta.replace('a','\u0126');
	%><%=texta%><%


}

%>


</body></html>