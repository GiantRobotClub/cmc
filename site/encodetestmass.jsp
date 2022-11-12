<HTML>
<BODY>
100000 CODE TEST<br>
<% 
int deck =  Integer.parseInt(request.getParameter("d"));

for (int series=0;series<100000;series++){

String code = webrunner.cardmaster.CardmasterVouchers.GenerateCode(deck,series);

if (code != null) {

%>
<%=code%><br>
<% } else { %> ERROR<br><% } %>
<% } %>
</BODY>
</HTML>