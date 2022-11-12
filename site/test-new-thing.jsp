<%
String get = request.getParameter("what");
String binary = webrunner.cardmaster.CardmasterData.securityConvertToBinary(get);

%>
ORIGINAL = <%=get%><br>
BINARY =  <%=binary%><br>
SECURITY CHECK = <%=webrunner.cardmaster.CardmasterData.securityCheckBinary(binary,get)%>
