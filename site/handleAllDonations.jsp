Doing donations....<br>
<%
int[] returnvals = webrunner.cardmaster.CardmasterDonationHandler.FillAllEntries();
%>
Success code: <%=returnvals[0]%><br>
Filled: <%=returnvals[1]%><br>
Left unfilled: <%=returnvals[2]%><br>
