<HTML>
<BODY>
<% 
int deck =  Integer.parseInt(request.getParameter("d"));
int startcode = Integer.parseInt(request.getParameter("s"));
int endcode = Integer.parseInt(request.getParameter("e"));

%>

CODES GENERATED FROM <%=startcode%> to <%=endcode%> for DECK <%=deck%><br>
<%

for (int series=startcode;series<endcode;series++){

String code = webrunner.cardmaster.CardmasterVouchers.GenerateCode(deck,series);

if (code != null) {

%>
<%=code%><br>
<% } else { %> ERROR<br><% } %>
<% } %>
</BODY>
</HTML>