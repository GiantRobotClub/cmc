<table>
<tr>
<td>ID</td>
<td>name</td>
<td>cardname</td>
<td>quantity</td>
<td>Status</td>
<td>Order Number</td>
</tr>
<% webrunner.cardmaster.CardmasterDonationEntry[] entries = webrunner.cardmaster.CardmasterDonationHandler.GetDonationEntries();

for (int i=0;i<entries.length;i++) {

%>
<tr>
<td><%=entries[i].id%></td>
<td><%=entries[i].accountname%></td>
<td><%=entries[i].deckname%></td>
<td><%=entries[i].quantity%></td>

<%String colorval = "";
if (!entries[i].filled) colorval = "bgcolor=#CDCDCD";
if (entries[i].errorcode != 0) colorval = "bgcolor=#FF0000";
%>
<td <%=colorval%>><%=entries[i].getStatus()%></td>
<td><a href="http://cmc.mrx.ca:8000/cubecart/admin/orders/order.php?cart_order_id=<%=entries[i].orderid%>"><%=entries[i].orderid%></a></td>
</tr>
<% } %>
</table>
